package Gaming;
import javax.swing.*;
public class TTTGame {
	
	public static void main(String[] args) {
	      // Run GUI codes in the Event-Dispatching thread for thread safety
	      SwingUtilities.invokeLater(new Runnable() {
	         @Override
	         public void run() {
	            new GUI(3, 3, 3);
	         }
	      });
	   }
}
	/*int board[][] = new int[3][3];
	int playerIDs[] = new int[2];
	int currentPlayer;
	int x, y;
	
	public static void main(String[] args) {
		boolean gameNotFinished = true;
		
		int gamesPlayed = 0;
		int gamesWon = 0;

		currentPlayer = getCurrentPlayer();
		playerIDs = getPlayerID();
		while (gameNotFinished) {
		ArrayList<Move> list = getLegalMoves();
		float winRate[] = new float[list.size()];
		
		for (int i = 0; i < list.size(); i++) {
			int updatedBoard[][] = playMove(list.get(i));
			simulate(updatedBoard, nextPlayer(), gamesPlayed, gamesWon);
			winRate[i] = gamesWon/gamesPlayed;
		}
		
		//Pick highest Win Rate
		
		//Play the Move
		
		//Check the Winning Condition
		
		}
		
	}
	
	public int[][] playMove(Move move) {
		int newBoard[][] = board;
		newBoard[move.x][move.y] = move.playerID;
		return newBoard;
	}
	
	public void simulate(int board[][], int player, int gamesPlayed, 
			int gamesWon) {
		int newBoard[][] = board;
		ArrayList<Move> list = getLegalMoves();
		for(int i = 0; i < list.size(); i++) {
			int updatedBoard[][] = playMove(list[i]);
			//Check the Winning Condition
			if () {
				gamesPlayed++;
				gamesWon++; //Only Increment for AI Player
				return; //Termination Condition
			}
			simulate(updatedBoard, nextPlayer(), gamesPlayed, gamesWon);
			
			//Play the Move
		}
	}
	
	public ArrayList<Move> getLegalMoves(int board[][], int playerID) {
		ArrayList<Move> moves;
		
		for (int i = 0; i<3; i++) {
			for (int j = 0; j<3; j++) {
				if (board[i][j] == 0) {
					moves.add(new Move(i, j, playerID));
				}
			}
		}
	}
	
*/

