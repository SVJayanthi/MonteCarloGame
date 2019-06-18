package Gaming;
import java.util.*;
import Gaming.GUI.Seed;

public class Move {	   
	private Seed[][] cells;
	private int win;
	
	public Move (Seed[][] game, int win) {
		cells = game;
		this.win = win;
	}
	
	public ArrayList<Integer> legalMoves() {
		ArrayList<Integer> moves = new ArrayList<Integer>();		
		if (win < 4) {
			for (int row = 0; row < cells.length; row++) {
				for (int col = 0; col < cells[0].length; col++) {
					if (cells[row][col] == Seed.EMPTY) {
						moves.add(row);
						moves.add(col);
					}
				}
			}
		}
		if (win > 3) {
			for (int col = 0; col < cells[0].length; col++) {
				for (int row = cells.length - 1; row >= 0; row--) {
					if (cells[row][col] == Seed.EMPTY) {
						moves.add(row);
						moves.add(col);
						break;
					}
				}
			}
			
		}
		return(moves);
	}
}
