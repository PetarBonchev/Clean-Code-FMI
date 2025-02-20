#include "Gameplay.h"
#include <iostream>

void ChessGameLogic::makeLowerCase(char& letter) {
    if ('A' <= letter && letter <= 'Z') {
        letter = letter - 'A' + 'a';
    }
}

bool ChessGameLogic::isColumnNameValid(char columnName) const {
    return FIRST_COLUMN_NAME <= columnName && columnName <= LAST_COLUMN_NAME;
}

bool ChessGameLogic::isRowNameValid(char rowName) const {
    return FIRST_ROW_NAME <= rowName && rowName <= LAST_ROW_NAME;
}

bool ChessGameLogic::isMoveKeywordValid(const String& moveCommand) const {
    if (moveCommand.getLen() != MOVE_COMMAND_LENGTH) {
        return false;
    }

    int keywordLength = strnlen_s(COMMAND_FOR_MOVE_KEYWORD, 10);
    for (int i = 0; i < keywordLength; i++) {
        if (moveCommand[i] != COMMAND_FOR_MOVE_KEYWORD[i]) {
            return false;
        }
    }
    return true;
}

bool ChessGameLogic::isMoveCoordinatesValid(const String& moveCommand) const {
    return isColumnNameValid(moveCommand[START_SQUARE_COLUMN_POSITION_IN_COMMAND]) &&
        isRowNameValid(moveCommand[START_SQUARE_ROW_POSITION_IN_COMMAND]) &&
        isColumnNameValid(moveCommand[END_SQUARE_COLUMN_POSITION_IN_COMMAND]) &&
        isRowNameValid(moveCommand[END_SQUARE_ROW_POSITION_IN_COMMAND]);
}

bool ChessGameLogic::isMoveCommandValid(const String& moveCommand) const {
    if (!isMoveKeywordValid(moveCommand) || !isMoveCoordinatesValid(moveCommand)) {
        std::cout << "Invalid move format!" << std::endl;
        return false;
    }
    return true;
}

int ChessGameLogic::getColumnIndexByName(char columnName) const {
    return columnName - FIRST_COLUMN_NAME;
}

int ChessGameLogic::getRowIndexByName(char rowName) const {
    return rowName - FIRST_ROW_NAME;
}

bool ChessGameLogic::validatePieceToMoveColor(SquareOfBoard board[CHESS_BOARD_SIZE][CHESS_BOARD_SIZE], bool isWhiteToMove, int startSquareColumnIndex,
    int startSquareRowIndex, int endSquareColumnIndex, int endSquareRowIndex) const {
    if (board[startSquareRowIndex][startSquareColumnIndex].piece == nullptr) {
        std::cout << "There's no piece at that location." << std::endl << std::endl;
        return false;
    }

    if (board[startSquareRowIndex][startSquareColumnIndex].piece != nullptr &&
        board[startSquareRowIndex][startSquareColumnIndex].piece->isWhitePiece() != isWhiteToMove) {
        std::cout << "That's not your piece!" << std::endl << std::endl;
        return false;
    }

    if (board[endSquareRowIndex][endSquareColumnIndex].piece != nullptr &&
        board[endSquareRowIndex][endSquareColumnIndex].piece->isWhitePiece() == isWhiteToMove) {
        std::cout << "You already have a piece there." << std::endl;
        return false;
    }
    return true;
}

bool ChessGameLogic::isPathClear(int startRow, int startColumn, int endRow, int endColumn, SquareOfBoard board[CHESS_BOARD_SIZE][CHESS_BOARD_SIZE]) {
    int rowStep = (endRow > startRow) ? 1 : (endRow < startRow) ? -1 : 0;
    int columnStep = (endColumn > startColumn) ? 1 : (endColumn < startColumn) ? -1 : 0;

    int currentRow = startRow + rowStep;
    int currentColumn = startColumn + columnStep;

    while (currentRow != endRow || currentColumn != endColumn) {
        if (board[currentRow][currentColumn].piece != nullptr) {
            return false;
        }
        currentRow += rowStep;
        currentColumn += columnStep;
    }
    return true;
}

bool ChessGameLogic::validateWhitePawnMove(int startRow, int startColumn, int endRow, int endColumn, SquareOfBoard board[CHESS_BOARD_SIZE][CHESS_BOARD_SIZE], bool& isKingDead) {
    int rowDifference = startRow - endRow;
    int columnDifference = startColumn - endColumn;

    if (startRow != 6 && rowDifference >= 2) {
        std::cout << "Pawns can move 2 squares forward only in their first move" << std::endl;
        return false;
    }
    if (startRow == 6 && rowDifference > 2) {
        std::cout << "Pawns can't move more than 2 squares forward on their first move." << std::endl;
        return false;
    }
    if (rowDifference <= 0) {
        std::cout << "Pawns can only move forward" << std::endl;
        return false;
    }
    if (abs(columnDifference) > 1) {
        std::cout << "Pawns can't move more than one column forward" << std::endl;
        return false;
    }
    if (abs(columnDifference) == 1 && rowDifference == 1 && board[endRow][endColumn].piece == nullptr) {
        std::cout << "There must be an enemy piece on that position for you to move there." << std::endl;
        return false;
    }
    if (rowDifference == 1 && columnDifference == 0 && board[endRow][endColumn].piece != nullptr && !board[endRow][endColumn].piece->isWhitePiece()) {
        std::cout << "An enemy piece is blocking your move!" << std::endl;
        return false;
    }
    if (rowDifference == 1 && abs(columnDifference) == 1 && !board[endRow][endColumn].piece->isWhitePiece()) {
        std::cout << "You have taken the enemy's " << board[endRow][endColumn].piece->getName() << "!" << std::endl;
        if (board[endRow][endColumn].piece->getName() == "King") {
            isKingDead = true;
        }
        return true;
    }
    std::cout << "Move successful!" << std::endl;
    return true;
}

bool ChessGameLogic::validateBlackPawnMove(int startRow, int startColumn, int endRow, int endColumn, SquareOfBoard board[CHESS_BOARD_SIZE][CHESS_BOARD_SIZE], bool& isKingDead) {
    int rowDifference = startRow - endRow;
    int columnDifference = startColumn - endColumn;

    if (startRow != 1 && rowDifference <= -2) {
        std::cout << "Pawns can move 2 squares forward only in their first move" << std::endl;
        return false;
    }
    if (startRow == 1 && rowDifference < -2) {
        std::cout << "Pawns can't move more than 2 squares forward on their first move." << std::endl;
        return false;
    }
    if (rowDifference >= 0) {
        std::cout << "Pawns can only move forward" << std::endl;
        return false;
    }
    if (abs(columnDifference) > 1) {
        std::cout << "Pawns can't move more than one column forward" << std::endl;
        return false;
    }
    if (abs(columnDifference) == 1 && rowDifference == -1 && board[endRow][endColumn].piece == nullptr) {
        std::cout << "There must be an enemy piece on that position for you to move there." << std::endl;
        return false;
    }
    if (rowDifference == -1 && columnDifference == 0 && board[endRow][endColumn].piece != nullptr && board[endRow][endColumn].piece->isWhitePiece()) {
        std::cout << "An enemy piece is blocking your move!" << std::endl;
        return false;
    }
    if (rowDifference == -1 && abs(columnDifference) == 1 && board[endRow][endColumn].piece->isWhitePiece()) {
        std::cout << "You have taken the enemy's " << board[endRow][endColumn].piece->getName() << "!" << std::endl;
        if (board[endRow][endColumn].piece->getName() == "King") {
            isKingDead = true;
        }
        return true;
    }
    std::cout << "Move successful!" << std::endl;
    return true;
}

bool ChessGameLogic::validatePawnMove(int startRow, int startColumn, int endRow, int endColumn, bool isWhiteToMove, SquareOfBoard board[CHESS_BOARD_SIZE][CHESS_BOARD_SIZE], bool& isKingDead) {
    if (isWhiteToMove) {
        return validateWhitePawnMove(startRow, startColumn, endRow, endColumn, board, isKingDead);
    }
    else {
        return validateBlackPawnMove(startRow, startColumn, endRow, endColumn, board, isKingDead);
    }
}

bool ChessGameLogic::validateKingMove(int startRow, int startColumn, int endRow, int endColumn, bool isWhiteToMove, SquareOfBoard board[CHESS_BOARD_SIZE][CHESS_BOARD_SIZE], bool& isKingDead) {
    int rowDifference = abs(startRow - endRow);
    int columnDifference = abs(startColumn - endColumn);

    if (rowDifference > 1 || columnDifference > 1) {
        std::cout << "Kings may only move one square" << std::endl;
        return false;
    }
    if (board[endRow][endColumn].piece != nullptr && board[endRow][endColumn].piece->isWhitePiece() != isWhiteToMove) {
        std::cout << "You have taken the enemy's " << board[endRow][endColumn].piece->getName() << "!" << std::endl;
        if (board[endRow][endColumn].piece->getName() == "King") {
            isKingDead = true;
        }
        return true;
    }
    std::cout << "Move successful!" << std::endl;
    return true;
}

bool ChessGameLogic::validateQueenMove(int startRow, int startColumn, int endRow, int endColumn, bool isWhiteToMove, SquareOfBoard board[CHESS_BOARD_SIZE][CHESS_BOARD_SIZE], bool& isKingDead) {
    int rowDifference = abs(startRow - endRow);
    int columnDifference = abs(startColumn - endColumn);

    if (rowDifference != columnDifference && rowDifference != 0 && columnDifference != 0) {
        std::cout << "Queens can move like rooks and bishops only" << std::endl;
        return false;
    }
    if (!isPathClear(startRow, startColumn, endRow, endColumn, board)) {
        std::cout << "There's a piece blocking your move!" << std::endl;
        return false;
    }
    if (board[endRow][endColumn].piece != nullptr && board[endRow][endColumn].piece->isWhitePiece() != isWhiteToMove) {
        std::cout << "You have taken the enemy's " << board[endRow][endColumn].piece->getName() << "!" << std::endl;
        if (board[endRow][endColumn].piece->getName() == "King") {
            isKingDead = true;
        }
        return true;
    }
    std::cout << "Move successful!" << std::endl;
    return true;
}

bool ChessGameLogic::validateKnightMove(int startRow, int startColumn, int endRow, int endColumn, bool isWhiteToMove, SquareOfBoard board[CHESS_BOARD_SIZE][CHESS_BOARD_SIZE], bool& isKingDead) {
    int rowDifference = abs(startRow - endRow);
    int columnDifference = abs(startColumn - endColumn);

    if (rowDifference * columnDifference != 2) {
        std::cout << "Knights can move only L-shape" << std::endl;
        return false;
    }
    if (board[endRow][endColumn].piece != nullptr && board[endRow][endColumn].piece->isWhitePiece() != isWhiteToMove) {
        std::cout << "You have taken the enemy's " << board[endRow][endColumn].piece->getName() << "!" << std::endl;
        if (board[endRow][endColumn].piece->getName() == "King") {
            isKingDead = true;
        }
        return true;
    }
    std::cout << "Move successful!" << std::endl;
    return true;
}

bool ChessGameLogic::validateBishopMove(int startRow, int startColumn, int endRow, int endColumn, bool isWhiteToMove, SquareOfBoard board[CHESS_BOARD_SIZE][CHESS_BOARD_SIZE], bool& isKingDead) {
    int rowDifference = abs(startRow - endRow);
    int columnDifference = abs(startColumn - endColumn);

    if (rowDifference != columnDifference) {
        std::cout << "Bishops can move only diagonally" << std::endl;
        return false;
    }
    if (!isPathClear(startRow, startColumn, endRow, endColumn, board)) {
        std::cout << "There's a piece blocking your move!" << std::endl;
        return false;
    }
    if (board[endRow][endColumn].piece != nullptr && board[endRow][endColumn].piece->isWhitePiece() != isWhiteToMove) {
        std::cout << "You have taken the enemy's " << board[endRow][endColumn].piece->getName() << "!" << std::endl;
        if (board[endRow][endColumn].piece->getName() == "King") {
            isKingDead = true;
        }
        return true;
    }
    std::cout << "Move successful!" << std::endl;
    return true;
}

bool ChessGameLogic::validateRookMove(int startRow, int startColumn, int endRow, int endColumn, bool isWhiteToMove, SquareOfBoard board[CHESS_BOARD_SIZE][CHESS_BOARD_SIZE], bool& isKingDead) {
    int rowDifference = abs(startRow - endRow);
    int columnDifference = abs(startColumn - endColumn);

    if (rowDifference != 0 && columnDifference != 0) {
        std::cout << "Rooks can move only horizontal or vertical" << std::endl;
        return false;
    }
    if (!isPathClear(startRow, startColumn, endRow, endColumn, board)) {
        std::cout << "There's a piece blocking your move!" << std::endl;
        return false;
    }
    if (board[endRow][endColumn].piece != nullptr && board[endRow][endColumn].piece->isWhitePiece() != isWhiteToMove) {
        std::cout << "You have taken the enemy's " << board[endRow][endColumn].piece->getName() << "!" << std::endl;
        if (board[endRow][endColumn].piece->getName() == "King") {
            isKingDead = true;
        }
        return true;
    }
    std::cout << "Move successful!" << std::endl;
    return true;
}

bool ChessGameLogic::isPieceAbleToMove(const String& moveCommand, SquareOfBoard board[CHESS_BOARD_SIZE][CHESS_BOARD_SIZE], bool isWhiteToMove) {
    int startSquareColumnIndex = getColumnIndexByName(moveCommand[START_SQUARE_COLUMN_POSITION_IN_COMMAND]);
    int startSquareRowIndex = getRowIndexByName(moveCommand[START_SQUARE_ROW_POSITION_IN_COMMAND]);
    int endSquareColumnIndex = getColumnIndexByName(moveCommand[END_SQUARE_COLUMN_POSITION_IN_COMMAND]);
    int endSquareRowIndex = getRowIndexByName(moveCommand[END_SQUARE_ROW_POSITION_IN_COMMAND]);

    if (!validatePieceToMoveColor(board, isWhiteToMove, startSquareColumnIndex, startSquareRowIndex, endSquareColumnIndex, endSquareRowIndex)) {
        return false;
    }

    String pieceName = board[startSquareRowIndex][startSquareColumnIndex].piece->getName();
    bool isKingDead = false;

    if (pieceName == "Pawn") {
        return validatePawnMove(startSquareRowIndex, startSquareColumnIndex, endSquareRowIndex, endSquareColumnIndex, isWhiteToMove, board, isKingDead);
    }
    else if (pieceName == "King") {
        return validateKingMove(startSquareRowIndex, startSquareColumnIndex, endSquareRowIndex, endSquareColumnIndex, isWhiteToMove, board, isKingDead);
    }
    else if (pieceName == "Queen") {
        return validateQueenMove(startSquareRowIndex, startSquareColumnIndex, endSquareRowIndex, endSquareColumnIndex, isWhiteToMove, board, isKingDead);
    }
    else if (pieceName == "Knight") {
        return validateKnightMove(startSquareRowIndex, startSquareColumnIndex, endSquareRowIndex, endSquareColumnIndex, isWhiteToMove, board, isKingDead);
    }
    else if (pieceName == "Bishop") {
        return validateBishopMove(startSquareRowIndex, startSquareColumnIndex, endSquareRowIndex, endSquareColumnIndex, isWhiteToMove, board, isKingDead);
    }
    else if (pieceName == "Rook") {
        return validateRookMove(startSquareRowIndex, startSquareColumnIndex, endSquareRowIndex, endSquareColumnIndex, isWhiteToMove, board, isKingDead);
    }
    return false;
}

void ChessGameLogic::fillBoard() {
    for (int i = 0; i < 28; i++) {
        for (int j = 0; j < 65; j++) {
            drawScreenBoardData[i][j] = ' ';
        }
    }
}

void ChessGameLogic::paintPatternOnDrawBoard() {
    const int rowRanges[] = { 3, 6, 8, 12, 14, 18, 20, 24, 23, 27 };
    const int columnRanges[] = { 7, 14, 20, 28, 34, 42, 48, 56, 55, 63 };

    for (int i = 3; i < 27; i++) {
        for (int j = 7; j < 63; j++) {
            bool isDarkSquare = false;
            for (int r = 0; r < 8; r += 2) {
                if ((i > rowRanges[r] && i < rowRanges[r + 1]) &&
                    (j > columnRanges[r] && j < columnRanges[r + 1])) {
                    isDarkSquare = true;
                    break;
                }
            }
            if (isDarkSquare) {
                drawScreenBoardData[i][j] = 219;
            }
        }
    }
}

void ChessGameLogic::paintBordersOnDrawBoard() {
    for (int i = 3; i < 27; i++) {
        drawScreenBoardData[i][5] = 186;
        drawScreenBoardData[i][64] = 186;
    }
    drawScreenBoardData[2][5] = 201;
    drawScreenBoardData[2][64] = 187;
    drawScreenBoardData[27][64] = 188;
    drawScreenBoardData[27][5] = 200;
    for (int j = 6; j < 64; j++) {
        drawScreenBoardData[2][j] = 205;
        drawScreenBoardData[27][j] = 205;
    }
}

void ChessGameLogic::paintColumnAndRowNamesOnDrawBoard() {
    int k = 0;

    for (int j = 10; j < 63; j += 7) {
        drawScreenBoardData[1][j] = 65 + k;
        k++;
    }
    for (int i = 4; i < 27; i += 3) {
        drawScreenBoardData[i][3] = 41 + k;
        k++;
    }
}

void ChessGameLogic::cleanSpaceForPiecesOnDrawBoard() {
    for (int i = 4; i < 27; i += 3) {
        for (int j = 10; j < 63; j += 7) {
            drawScreenBoardData[i][j - 1] = ' ';
            drawScreenBoardData[i][j] = ' ';
            drawScreenBoardData[i][j + 1] = ' ';
        }
    }
}

void ChessGameLogic::paintPiecesOnDrawBoard(SquareOfBoard board[CHESS_BOARD_SIZE][CHESS_BOARD_SIZE]) {
    const int rowIndexesOnScreen[] = { 4, 7, 10, 13, 16, 19, 22, 25 };
    const int columnIndexesOnScreen[] = { 10, 17, 24, 31, 38, 45, 52, 59 };

    for (int i = 0; i < CHESS_BOARD_SIZE; i++) {
        for (int j = 0; j < CHESS_BOARD_SIZE; j++) {
            char pieceNameOnScreen = ' ';
            if (board[i][j].piece) {
                pieceNameOnScreen = board[i][j].piece->getName()[0];
                if (board[i][j].piece->getName() == "Knight") {
                    pieceNameOnScreen = 'N';
                }
                if (!board[i][j].piece->isWhitePiece()) {
                    makeLowerCase(pieceNameOnScreen);
                }
            }
            drawScreenBoardData[rowIndexesOnScreen[i]][columnIndexesOnScreen[j]] = pieceNameOnScreen;
        }
    }
}

void ChessGameLogic::printDrawBoardOnScreen(SquareOfBoard board[CHESS_BOARD_SIZE][CHESS_BOARD_SIZE]) {
    fillBoard();
    paintColumnAndRowNamesOnDrawBoard();
    paintPatternOnDrawBoard();
    cleanSpaceForPiecesOnDrawBoard();
    paintBordersOnDrawBoard();
    paintPiecesOnDrawBoard(board);

    for (int i = 0; i < 28; i++) {
        for (int j = 0; j < 65; j++) {
            std::cout << drawScreenBoardData[i][j];
        }
        std::cout << std::endl;
    }
}

void ChessGameLogic::spawnWhitePiecesOnBoard() {
    Pawn* whitePawns[8] = { new Pawn("Pawn", 1), new Pawn("Pawn", 1), new Pawn("Pawn", 1), new Pawn("Pawn", 1),
                           new Pawn("Pawn", 1), new Pawn("Pawn", 1), new Pawn("Pawn", 1), new Pawn("Pawn", 1) };
    Rook* whiteRooks[2] = { new Rook("Rook", 1), new Rook("Rook", 1) };
    Knight* whiteKnights[2] = { new Knight("Knight", 1), new Knight("Knight", 1) };
    Bishop* whiteBishops[2] = { new Bishop("Bishop", 1), new Bishop("Bishop", 1) };
    Queen* whiteQueen = new Queen("Queen", 1);
    King* whiteKing = new King("King", 1);

    board[7][0].piece = whiteRooks[0];
    board[7][1].piece = whiteKnights[0];
    board[7][2].piece = whiteBishops[0];
    board[7][3].piece = whiteQueen;
    board[7][4].piece = whiteKing;
    board[7][5].piece = whiteBishops[1];
    board[7][6].piece = whiteKnights[1];
    board[7][7].piece = whiteRooks[1];

    for (int i = 0; i < 8; i++) {
        board[6][i].piece = whitePawns[i];
    }
}

void ChessGameLogic::spawnBlackPiecesOnBoard() {
    Pawn* blackPawns[8] = { new Pawn("Pawn", 2), new Pawn("Pawn", 2), new Pawn("Pawn", 2), new Pawn("Pawn", 2),
                           new Pawn("Pawn", 2), new Pawn("Pawn", 2), new Pawn("Pawn", 2), new Pawn("Pawn", 2) };
    Rook* blackRooks[2] = { new Rook("Rook", 2), new Rook("Rook", 2) };
    Knight* blackKnights[2] = { new Knight("Knight", 2), new Knight("Knight", 2) };
    Bishop* blackBishops[2] = { new Bishop("Bishop", 2), new Bishop("Bishop", 2) };
    Queen* blackQueen = new Queen("Queen", 2);
    King* blackKing = new King("King", 2);

    board[0][0].piece = blackRooks[0];
    board[0][1].piece = blackKnights[0];
    board[0][2].piece = blackBishops[0];
    board[0][3].piece = blackQueen;
    board[0][4].piece = blackKing;
    board[0][5].piece = blackBishops[1];
    board[0][6].piece = blackKnights[1];
    board[0][7].piece = blackRooks[1];

    for (int i = 0; i < 8; i++) {
        board[1][i].piece = blackPawns[i];
    }
}

void ChessGameLogic::printHelpText() const {
    std::cout << "-----------------Help menu-----------------" << std::endl;
    std::cout << "Possible commands: " << std::endl;
    std::cout << "undo -> returns the game to the state before the last move." << std::endl;
    std::cout << "exit -> exits the game." << std::endl;
    std::cout << "move x1y1 x2y2 -> if possible, move the figure from position (x1, y1) to position (x2, y2)" << std::endl;
    std::cout << "example move command ---> move d2 d4" << std::endl;
}

void ChessGameLogic::movePieceOnBoard(const String& moveCommand, SquareOfBoard board[CHESS_BOARD_SIZE][CHESS_BOARD_SIZE]) {
    int startSquareColumnIndex = getColumnIndexByName(moveCommand[START_SQUARE_COLUMN_POSITION_IN_COMMAND]);
    int startSquareRowIndex = getRowIndexByName(moveCommand[START_SQUARE_ROW_POSITION_IN_COMMAND]);
    int endSquareColumnIndex = getColumnIndexByName(moveCommand[END_SQUARE_COLUMN_POSITION_IN_COMMAND]);
    int endSquareRowIndex = getRowIndexByName(moveCommand[END_SQUARE_ROW_POSITION_IN_COMMAND]);

    board[endSquareRowIndex][endSquareColumnIndex].piece = board[startSquareRowIndex][startSquareColumnIndex].piece;
    board[startSquareRowIndex][startSquareColumnIndex].piece = nullptr;
}

bool ChessGameLogic::processCommandFromUser(SquareOfBoard board[CHESS_BOARD_SIZE][CHESS_BOARD_SIZE], bool isWhiteToMove) {
    String command;
    std::cout << std::endl << "It is Player " << (isWhiteToMove ? 1 : 2) << " turn. Please enter your command:" << std::endl;
    std::cin >> command;

    if (command == "help") {
        printHelpText();
    }
    else if (command == "exit") {
        return false;
    }
    else {
        while (!isMoveCommandValid(command)) {
            std::cin >> command;
        }
        while (!isPieceAbleToMove(command, board, isWhiteToMove) || !isMoveCommandValid(command)) {
            std::cout << "Please enter your move again!" << std::endl;
            std::cin >> command;
        }
        movePieceOnBoard(command, board);
    }

    if (isKingDead) {
        std::cout << "Player " << (isWhiteToMove ? 1 : 2) << " wins!" << std::endl;
        return false;
    }
    return true;
}

void ChessGameLogic::playGame() {
    spawnWhitePiecesOnBoard();
    spawnBlackPiecesOnBoard();

    bool isWhiteToMove = true;
    bool foundWinner = false;

    while (!foundWinner) {
        printDrawBoardOnScreen(board);
        foundWinner = processCommandFromUser(board, isWhiteToMove);
        isWhiteToMove = !isWhiteToMove;
    }
}