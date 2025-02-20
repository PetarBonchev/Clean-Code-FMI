#pragma once
#include "Piece.h"

class King :public Piece {
public:
	King() :Piece() {}
	King(const String name, const bool isWhitePlayer) :Piece(name,  isWhitePlayer) {}
};