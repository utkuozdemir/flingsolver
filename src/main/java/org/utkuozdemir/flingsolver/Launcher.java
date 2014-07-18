package org.utkuozdemir.flingsolver;

/**
 * Created by Utku on 15.7.2014...
 */
public class Launcher {
	public static void main(String[] args) {
		// PATTERN TO USE
//		int[][] initialBoard = {
//				{0, 0, 0, 0, 0, 0, 0},
//				{0, 0, 0, 0, 0, 0, 0},
//				{0, 0, 0, 0, 0, 0, 0},
//				{0, 0, 0, 0, 0, 0, 0},
//				{0, 0, 0, 0, 0, 0, 0},
//				{0, 0, 0, 0, 0, 0, 0},
//				{0, 0, 0, 0, 0, 0, 0},
//				{0, 0, 0, 0, 0, 0, 0},
//		};


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

//		int[][] initialBoard = {
//				{0, 0, 0, 0, 0, 0, 0},
//				{0, 0, 0, 0, 1, 0, 0},
//				{0, 1, 1, 0, 0, 0, 0},
//				{0, 1, 0, 1, 0, 0, 0},
//				{0, 0, 0, 0, 0, 0, 0},
//				{0, 0, 0, 0, 0, 1, 1},
//				{0, 0, 1, 0, 0, 0, 0},
//				{0, 0, 0, 0, 0, 0, 0},
//		};


		int[][] initialBoard = {
				{0, 0, 1, 1, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 1, 0},
				{0, 0, 0, 0, 1, 0, 0},
				{0, 1, 0, 0, 0, 0, 1},
				{0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0},
				{0, 0, 1, 0, 0, 0, 0},
		};

		long start = System.currentTimeMillis();
		Board board = new Board(initialBoard);
		new Solver(board).solve();
		long end = System.currentTimeMillis();

		System.out.println("ELAPSED TIME: " + (end - start) + " ms");
	}
}