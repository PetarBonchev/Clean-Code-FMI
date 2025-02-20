#pragma once
#include "Board.h"
#include "Pawn.h"
#include "Rook.h"
#include "Knight.h"
#include "Bishop.h"
#include "Queen.h"
#include "King.h"

class ChessGameLogic {

private:

	static constexpr int CHESS_BOARD_SIZE = 8;
	static constexpr int MOVE_COMMAND_LENGTH = 10;
	static constexpr char COMMAND_FOR_MOVE_KEYWORD[] = "move";
	static constexpr int START_SQUARE_COLUMN_POSITION_IN_COMMAND = 5;
	static constexpr int START_SQUARE_ROW_POSITION_IN_COMMAND = 6;
	static constexpr int END_SQUARE_COLUMN_POSITION_IN_COMMAND = 8;
	static constexpr int END_SQUARE_ROW_POSITION_IN_COMMAND = 9;
	static constexpr char FIRST_COLUMN_NAME = 'a';
	static constexpr char LAST_COLUMN_NAME = 'h';
	static constexpr char FIRST_ROW_NAME = '1';
	static constexpr char LAST_ROW_NAME = '8';

	void makeLowerCase(char& letter);
	bool isColumnNameValid(char columnName) const;
	bool isRowNameValid(char rowName) const;
	bool isMoveKeywordValid(const String& moveCommand) const;
	bool isMoveCoordinatesValid(const String& moveCommand) const;
	int getColumnIndexByName(char columnName) const;
	int getRowIndexByName(char rowName) const;
	bool validatePieceToMoveColor(SquareOfBoard board[CHESS_BOARD_SIZE][CHESS_BOARD_SIZE], bool isWhiteToMove, int startSquareColumnIndex,
		int startSquareRowIndex, int endSquareColumnIndex, int endSquareRowIndex) const;

	bool validateWhitePawnMove(int startRow, int startColumn, int endRow, int endColumn, SquareOfBoard board[CHESS_BOARD_SIZE][CHESS_BOARD_SIZE], bool& isKingDead);
	bool validateBlackPawnMove(int startRow, int startColumn, int endRow, int endColumn, SquareOfBoard board[CHESS_BOARD_SIZE][CHESS_BOARD_SIZE], bool& isKingDead);
	bool validateRookMove(int startRow, int startCol, int endRow, int endCol, bool isWhiteToMove, SquareOfBoard board[CHESS_BOARD_SIZE][CHESS_BOARD_SIZE], bool& isKingDead);
	bool validateBishopMove(int startRow, int startCol, int endRow, int endCol, bool isWhiteToMove, SquareOfBoard board[CHESS_BOARD_SIZE][CHESS_BOARD_SIZE], bool& isKingDead);
	bool validateKnightMove(int startRow, int startCol, int endRow, int endCol, bool isWhiteToMove, SquareOfBoard board[CHESS_BOARD_SIZE][CHESS_BOARD_SIZE], bool& isKingDead);
	bool validateQueenMove(int startRow, int startCol, int endRow, int endCol, bool isWhiteToMove, SquareOfBoard board[CHESS_BOARD_SIZE][CHESS_BOARD_SIZE], bool& isKingDead);
	bool validateKingMove(int startRow, int startCol, int endRow, int endCol, bool isWhiteToMove, SquareOfBoard board[CHESS_BOARD_SIZE][CHESS_BOARD_SIZE], bool& isKingDead);
	bool validatePawnMove(int startRow, int startCol, int endRow, int endCol, bool isWhiteToMove, SquareOfBoard board[CHESS_BOARD_SIZE][CHESS_BOARD_SIZE], bool& isKingDead);
	bool isPathClear(int startRow, int startCol, int endRow, int endCol, SquareOfBoard board[CHESS_BOARD_SIZE][CHESS_BOARD_SIZE]);

public:
	SquareOfBoard board[CHESS_BOARD_SIZE][CHESS_BOARD_SIZE];
	char drawScreenBoardData[28][65];
	bool isKingDead = false;
	void playGame();
	void printHelpText() const;
	void spawnWhitePiecesOnBoard();
	void spawnBlackPiecesOnBoard();
	bool isMoveCommandValid(const String& moveCommand) const;
	bool isPieceAbleToMove(const String& moveCommand, SquareOfBoard board[CHESS_BOARD_SIZE][CHESS_BOARD_SIZE], bool isWhiteToMove);
	void fillBoard();
	void paintPatternOnDrawBoard();
	void paintBordersOnDrawBoard();
	void paintColumnAndRowNamesOnDrawBoard();
	void cleanSpaceForPiecesOnDrawBoard();
	void paintPiecesOnDrawBoard(SquareOfBoard board[CHESS_BOARD_SIZE][CHESS_BOARD_SIZE]);
	void printDrawBoardOnScreen(SquareOfBoard board[CHESS_BOARD_SIZE][CHESS_BOARD_SIZE]);
	void movePieceOnBoard(const String& moveCommand, SquareOfBoard board[CHESS_BOARD_SIZE][CHESS_BOARD_SIZE]);
	bool processCommandFromUser(SquareOfBoard board[CHESS_BOARD_SIZE][CHESS_BOARD_SIZE], bool isWhiteToMove);
};


