#pragma once
#include "Piece.h"

class Knight :public Piece {
public:
	Knight() :Piece() {}
	Knight(const String name,  const bool isWhite) :Piece(name, isWhite) {}
};