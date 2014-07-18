package org.utkuozdemir.flingsolver;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Utku on 15.7.2014...
 */
public class Board {

	private int rows, columns;
	private int[][] board;
	private int ballCount;
	private Set<Move> possibleMoves;
	private Set<Board> possibleNextBoards;

	private Board parent;
	private Move sourceMove;

	public Board(int[][] board) {
		this(board, null, null);
	}

	public Board(int[][] board, Board parent, Move sourceMove) {
		this.board = board;
		this.parent = parent;
		this.sourceMove = sourceMove;
		this.rows = board.length;
		this.columns = board[0].length;

		initBallCount();
		initPossibleMoves();
		initPossibleNextBoards();
	}

	private void initBallCount() {
		for (int[] rows : board) {
			for (int cell : rows) {
				if (cell > 0) ballCount++;
			}
		}
	}

	private void initPossibleMoves() {
		possibleMoves = new HashSet<>();
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				int cell = board[i][j];
				if (cell > 0) {
					for (Direction direction : Direction.values()) {
						if (canMove(i, j, direction)) possibleMoves.add(new Move(i, j, direction));
					}
				}
			}
		}
	}

	private boolean canMove(int row, int column, Direction direction) {
		int currentRow = row;
		int currentColumn = column;

		int nextBallRow = -1;
		int nextBallColumn = -1;

		switch (direction) {
			case LEFT:
				currentColumn--;
				while (currentColumn >= 0) {
					int cell = board[currentRow][currentColumn];
					if (cell > 0) {
						nextBallRow = currentRow;
						nextBallColumn = currentColumn;
						break;
					}
					currentColumn--;
				}
				break;
			case RIGHT:
				currentColumn++;
				while (currentColumn < columns) {
					int cell = board[currentRow][currentColumn];
					if (cell > 0) {
						nextBallRow = currentRow;
						nextBallColumn = currentColumn;
						break;
					}
					currentColumn++;
				}
				break;
			case UP:
				currentRow--;
				while (currentRow >= 0) {
					int cell = board[currentRow][currentColumn];
					if (cell > 0) {
						nextBallRow = currentRow;
						nextBallColumn = currentColumn;
						break;
					}
					currentRow--;
				}
				break;
			case DOWN:
				currentRow++;
				while (currentRow < rows) {
					int cell = board[currentRow][currentColumn];
					if (cell > 0) {
						nextBallRow = currentRow;
						nextBallColumn = currentColumn;
						break;
					}
					currentRow++;
				}
				break;
		}

		if (nextBallColumn != -1 && nextBallRow != -1) {
			// check if neighbor
			int difference = ((column - nextBallColumn) + (row - nextBallRow)) *
					((column - nextBallColumn) + (row - nextBallRow));
			return difference != 1;
		}
		return false;
	}

	private Board boardStateAfterMove(Move move) {
		int[][] newBoard = new int[rows][columns];
		for (int i = 0; i < rows; i++) {
			System.arraycopy(board[i], 0, newBoard[i], 0, columns);
		}

		newBoard[move.getRow()][move.getColumn()] = 0;

		switch (move.getDirection()) {
			case LEFT:
				for (int i = 0; i < move.getColumn(); i++) {
					int cell = newBoard[move.getRow()][i];
					if (cell > 0) {
						newBoard[move.getRow()][i]--;

						newBoard[move.getRow()][i + 1]++;
						if (newBoard[move.getRow()][i + 1] < 2) i++;
					}
				}
				break;
			case RIGHT:
				for (int i = columns - 1; i > move.getColumn(); i--) {
					int cell = newBoard[move.getRow()][i];
					if (cell > 0) {
						newBoard[move.getRow()][i]--;

						newBoard[move.getRow()][i - 1]++;
						if (newBoard[move.getRow()][i - 1] < 2) i--;
					}
				}
				break;
			case DOWN:
				for (int i = rows - 1; i > move.getRow(); i--) {
					int cell = newBoard[i][move.getColumn()];
					if (cell > 0) {
						newBoard[i][move.getColumn()]--;

						newBoard[i - 1][move.getColumn()]++;
						if (newBoard[i - 1][move.getColumn()] < 2) i--;
					}
				}
				break;
			case UP:
				for (int i = 0; i < move.getRow(); i++) {
					int cell = newBoard[i][move.getColumn()];
					if (cell > 0) {
						newBoard[i][move.getColumn()]--;

						newBoard[i + 1][move.getColumn()]++;
						if (newBoard[i + 1][move.getColumn()] < 2) i++;
					}
				}
				break;
		}
		return new Board(newBoard, this, move);

	}

	public boolean isFinished() {
		return ballCount == 1;
	}


	private void initPossibleNextBoards() {
		possibleNextBoards = new HashSet<>();

		for (Move possibleMove : possibleMoves) {
			possibleNextBoards.add(boardStateAfterMove(possibleMove));
		}
	}

	public Set<Board> getPossibleNextBoards() {
		return possibleNextBoards;
	}

	public Board getParent() {
		return parent;
	}

	public Move getSourceMove() {
		return sourceMove;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				sb.append(board[i][j]);
			}
			sb.append(System.lineSeparator());
		}
		return sb.toString();
	}

	public int[][] getBoard() {
		return board;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Board board = (Board) o;

		return columns == board.columns &&
				rows == board.rows &&
				new EqualsBuilder().append(this.board, board.getBoard()).isEquals();
	}


	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(this.board).hashCode();
	}
}
