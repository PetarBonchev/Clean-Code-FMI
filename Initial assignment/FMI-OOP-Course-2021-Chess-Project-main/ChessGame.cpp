#include <iostream>
#include "Board.h"
#include "Gameplay.h"
#include <string>
#include <cmath>
#include <Windows.h>
#include <fstream>

const int WINDOW_WIDTH = 800;
const int WINDOW_HEIGHT = 700;

int main() {
	HWND consoleWindow = GetConsoleWindow();
	RECT windowRect;
	GetWindowRect(consoleWindow, &windowRect);
	MoveWindow(consoleWindow, windowRect.left, windowRect.top, WINDOW_WIDTH, WINDOW_HEIGHT, TRUE);
	ChessGameLogic gameLogic;
	gameLogic.playGame();
}