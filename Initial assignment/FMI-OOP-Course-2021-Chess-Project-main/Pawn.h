#pragma once
#include "Piece.h"

class Pawn :public Piece {
public:
	Pawn() :Piece(){}
	Pawn(const String name, const bool isWhite) :Piece(name, isWhite) {}
};
