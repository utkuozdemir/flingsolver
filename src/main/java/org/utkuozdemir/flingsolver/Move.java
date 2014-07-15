package org.utkuozdemir.flingsolver;

/**
 * Created by Utku on 15.7.2014...
 */
public class Move {
	private final int row;
	private final int column;
	private final Direction direction;

	public Move(int row, int column, Direction direction) {
		this.row = row;
		this.column = column;
		this.direction = direction;
	}

	public int getRow() {
		return row;
	}

	public int getColumn() {
		return column;
	}

	public Direction getDirection() {
		return direction;
	}
}
