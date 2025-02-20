#pragma once
#include "Board.h"
#include "Pawn.h"
#include "Rook.h"
#include "Knight.h"
#include "Bishop.h"
#include "Queen.h"
#include "King.h"

class Gameplay {
public:
	Board board[8][8];
	char gameField[28][65];
	bool isKingDead = false;
	Gameplay();
	~Gameplay();
	void PlayGame();
	bool isMoveCommandValid(String move);
	bool canMove(String move, Board board[8][8], int currentPlayer);
	void FillBoard();
	void PaintBoard();
	void PrintBorders();
	void PrintNumbering();
	void FigureSpaceCleaning();
	void PlacePieces(Board board[8][8]);
	void PrintBoard(Board board[8][8]);
	//void undo(String move, Board board[8][8]);

};


