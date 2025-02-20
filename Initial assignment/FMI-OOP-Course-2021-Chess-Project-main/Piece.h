#pragma once
#include "String.h"

class Piece {
private:
	String name;
	bool isWhite;
public:
	Piece();
	Piece(const String name, const bool isWhite);
	void setName(const String name);
	void setIsWhite(const bool isWhite);
	bool isWhitePiece() const;
	String getName() const; 
};
