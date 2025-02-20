#pragma once
#include "Piece.h"


class Queen : public Piece {
public:
	Queen() :Piece() {}
	Queen(const String name, const bool isWhite) :Piece(name, isWhite) {}
};