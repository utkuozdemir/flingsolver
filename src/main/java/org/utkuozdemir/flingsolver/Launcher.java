package org.utkuozdemir.flingsolver;

/**
 * Created by Utku on 15.7.2014...
 */
public class Launcher {
	public static void main(String[] args) {
//		int[][] initialBoard = {{1, 0, 1}, {0, 0, 0}, {0, 1, 0}};

//		int[][] initialBoard = {
//				{0, 0, 0, 0, 0, 1, 0},
//				{0, 0, 0, 0, 0, 1, 0},
//				{0, 1, 0, 0, 0, 0, 1},
//				{0, 0, 0, 0, 0, 0, 1},
//				{0, 0, 0, 0, 0, 0, 1},
//				{0, 0, 0, 0, 0, 0, 0},
//				{1, 0, 0, 0, 1, 0, 0},
//				{0, 0, 0, 0, 0, 0, 0},
//		};

		int[][] initialBoard = {
				{0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 1, 0, 0},
				{0, 1, 1, 0, 0, 0, 0},
				{0, 1, 0, 1, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 1, 1},
				{0, 0, 1, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0},
		};

		Board board = new Board(initialBoard, null);
		new Solver(board).solve();
	}
}