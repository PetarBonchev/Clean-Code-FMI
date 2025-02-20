#pragma once
#include "Piece.h"

class Rook :public Piece {
public:
	Rook() :Piece() {}
	Rook(const String name, const bool isWhite) :Piece(name, isWhite) {}
};
