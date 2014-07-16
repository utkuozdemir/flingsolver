package org.utkuozdemir.flingsolver;

import java.util.*;

/**
 * Created by Utku on 15.7.2014...
 */
public class Solver {
	private Board board;

	public Solver(Board board) {
		this.board = board;
	}

	public void solve() {
		Set<Board> boards = new HashSet<>();
		boards.add(board);

		int oldSize = Integer.MIN_VALUE;
		int newSize = Integer.MAX_VALUE;
		while (oldSize < newSize) {
			oldSize = boards.size();
			for (Board current : new ArrayList<>(boards)) {
				boards.addAll(current.getPossibleNextBoards());
			}
			newSize = boards.size();
		}

		Set<Board> finishedBoards = new HashSet<>();
		for (Board current : boards) {
			if (current.isFinished()) {
				finishedBoards.add(current);
			}
		}

		if (finishedBoards.isEmpty()) System.out.println("NO SOLUTIONS FOUND!!");
		else {
			System.out.println("SOLUTION COUNT: " + finishedBoards.size());

			List<String> pattern = new ArrayList<>();
			Board b = finishedBoards.iterator().next();
			while (b != null) {
				pattern.add(b.toString());
				b = b.getParent();
			}
			Collections.reverse(pattern);
			for (String p : pattern) {
				System.out.println(p);
				System.out.println("-----------");
			}
		}
	}
}