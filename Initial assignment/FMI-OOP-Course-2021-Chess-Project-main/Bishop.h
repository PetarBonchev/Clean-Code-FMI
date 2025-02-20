#pragma once
#include "Piece.h"

class Bishop :public Piece {
public:
	Bishop() :Piece() {}
	Bishop(const String name, const bool isWhite) :Piece(name, isWhite) {}
};
