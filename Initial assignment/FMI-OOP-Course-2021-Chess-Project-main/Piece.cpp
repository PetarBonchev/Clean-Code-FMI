#include "Piece.h"

Piece::Piece()
{
	name = "";
	isWhite = 0;
}

Piece::Piece(const String name, const bool isWhite)
{
	this->name = name;
	this->isWhite = isWhite;
}

void Piece::setName(const String name)
{
	this->name = name;
}

void Piece::setIsWhite(const bool isWhite)
{
	this->isWhite = isWhite;
}

String Piece:: getName() const
{
	return name;
}

bool Piece::isWhitePiece() const
{
	return isWhite;
}
