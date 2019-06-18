package Gaming;
import java.util.*;

import Gaming.GUI.GameState;
import Gaming.GUI.Seed;

public class Simulation{
	   private int win;
	   private Seed[][] gameBoard;
	   	   
	   public Simulation (Seed[][] boardSim, int win) {
		   gameBoard = copyBoard(boardSim);
		   this.win = win;
	   }
	   
	   private double gamesPlayed = 0;
	   private double gamesWon = 0;
	   
	   public int[] simulate(Seed seedSim) {
		   Seed[][] boardSimulation = gameBoard;
		   ArrayList<Integer> list = legalMoves(boardSimulation);
		   int count = list.size()/2;
		   
		   int loss[] = lossImminent(seedSim);
		   int lossTotal = loss[0] + loss[1];
		   if (lossTotal != -2) {
			   return loss;
		   } else {
			   double winRate[] = new double[count];
			   
			   for (int i = 0; i < winRate.length; i++) {
				   winRate[i] = runSim(boardSimulation, seedSim, list.get(i*2), list.get((i*2)+1));
			   }
			   
			   int max = 0;
			   for (int i = 0; i < winRate.length; i++) {
				   if (winRate[i] > winRate[max]) {
					   max = i; 
				   }
			   }
			   int[] finalMove = {list.get(max*2), list.get((max*2) + 1)};
			   return finalMove;
		   }
	   }

	   private double runSim(Seed[][] gaming, Seed theSeed, int x, int y) {
		   Seed[][] simBoard = copyBoard(gaming);
		   
		   if (Arrays.deepEquals(simBoard, gameBoard)) {
			   gamesPlayed = 0;
			   gamesWon = 0;
		   }
		   
		   Seed currentPlayer = theSeed;
		   simBoard[x][y] = currentPlayer;
		   
		   System.out.println("Theoretical Move: " + x + " " + y);
		   
		   ArrayList<Integer> list = legalMoves(simBoard);
		   int amount = list.size()/2;

		   GameState currentState;
		   
		   currentState = updateGame(simBoard, theSeed, x, y);
		   
		   if(currentState == GameState.NOUGHT_WON) {
			   gamesWon++;
			   gamesPlayed++;
		   } else if (currentState==GameState.CROSS_WON) {
			   gamesWon--;
			   gamesPlayed++;
		   } else if (currentState==GameState.DRAW) {
			   gamesPlayed++;
		   }
		   
		   int iteration = 0;
		   
		   if ((win > 3) && (currentState == GameState.PLAYING) && (amount != 0)) {
			   currentPlayer = (currentPlayer == Seed.CROSS) ? Seed.NOUGHT : Seed.CROSS;
			   
			   Random rand = new Random();
			   int item  = rand.nextInt(amount) * 2;
			   
			   runSim(simBoard, currentPlayer, list.get(item), list.get(item+1));
		   }
		   
		   if (win <4) {
			   while((iteration < amount) && (currentState == GameState.PLAYING)) {
				   currentPlayer = (currentPlayer == Seed.CROSS) ? Seed.NOUGHT : Seed.CROSS;
			   
				   Random rand = new Random();
				   int item  = rand.nextInt(amount) * 2;
			   
				   runSim(simBoard, currentPlayer, list.get(item), list.get(item+1));
			   
				   iteration++;
			   }
		   }
		   
		   return (gamesWon/gamesPlayed);
	   }
	   
	   public int[] lossImminent(Seed theSeed) {
		   int[] possibleMove = {-1, -1};
		   Seed[][] boardToCopy = copyBoard(gameBoard);
		   ArrayList<Integer> movesAvailable = legalMoves(boardToCopy);
		   Seed currentPlayer = (theSeed == Seed.CROSS) ? Seed.NOUGHT : Seed.CROSS;
		   GameState currentState;
		   int count = movesAvailable.size()/2;
		   outerloop:
		   for (int moves = 0; moves < count; moves++) {
				   if (boardToCopy[movesAvailable.get(moves*2)][movesAvailable.get((moves*2)+1)] == Seed.EMPTY) {
					   boardToCopy[movesAvailable.get(moves*2)][movesAvailable.get((moves*2)+1)] = currentPlayer;
				   }
				   currentState = updateGame(boardToCopy, currentPlayer, movesAvailable.get(moves*2), movesAvailable.get((moves*2)+1));
				   if (currentState==GameState.CROSS_WON) {
					   possibleMove[0] = movesAvailable.get(moves*2);
					   possibleMove[1] = movesAvailable.get((moves*2)+1);
					   break outerloop;
				   } else {
					   boardToCopy[movesAvailable.get(moves*2)][movesAvailable.get((moves*2)+1)] = Seed.EMPTY;
				   }
		   }
		   return possibleMove;
	   }
	   

	   public Seed[][] copyBoard(Seed[][] gamingBoard) {
		   Seed[][] boardToCopy = new Seed[gamingBoard.length][gamingBoard[0].length];
		   for (int row = 0; row < gamingBoard.length; row++) {
			   for (int col = 0; col < gamingBoard[0].length; col++) {
				   boardToCopy[row][col] = gamingBoard[row][col];
			   }
		   }
		   return boardToCopy;
	   }
	   
	   
	   public GameState updateGame(Seed[][] game, Seed theSeed, int rowSelected, int colSelected) {
		  GameState stateEnd = GameState.PLAYING;
		  if (hasWon(game, theSeed, rowSelected, colSelected)) {  
	         stateEnd = (theSeed == Seed.CROSS) ? GameState.CROSS_WON : GameState.NOUGHT_WON;
	      } else if (isDraw(game)) { 
	         stateEnd = GameState.DRAW;
	      }
		  return stateEnd;
	   }
	   
	   public boolean isDraw(Seed[][] drawBoard) {
	      for (int row = 0; row < drawBoard.length; ++row) {
	         for (int col = 0; col < drawBoard[0].length; ++col) {
	            if (drawBoard[row][col] == Seed.EMPTY) {
	               return false;
	            }
	         }
	      }
	      return true;
	   }


	   public boolean hasWon(Seed[][] winBoard, Seed seedEnd, int rowSelected, int colSelected) {
		   boolean winPotential = false;
		   int count = 0;
		   for (int col = 0; col < winBoard[0].length; ++col) {
		      if (winBoard[rowSelected][col] == seedEnd) {
		         ++count;
		         if (count == win) winPotential=true;
		      } else {
		    	  count = 0;
		      }
		   }
		   
		   count = 0;
		   for (int row = 0; row < winBoard.length; ++row) {
		      if (winBoard[row][colSelected] == seedEnd) {
		         ++count;
		         if (count == win) winPotential=true;
		      } else {
		    	  count = 0;
		      }
		   }
		   
		   int countL = 0;
		   int countR = 0;
		   for (int row = 0; row < winBoard.length; ++row) {
			   for (int coll = 0; coll < winBoard[0].length; ++coll) {
				   if ((row-rowSelected) == (coll - colSelected)) {
				       if (winBoard[row][coll] == seedEnd) {
					   ++countR;
				         if (countR == win) winPotential=true;
				       } else {
				    	   countR = 0;
				       }
				   }

				   if ((row-rowSelected) == (-1)*(coll - colSelected)) {
				       if (winBoard[row][coll] == seedEnd) {
					   ++countL;
				         if (countL == win) winPotential=true;
				       } else {
				    	   countL = 0;
				       }
				   } 
			   }
		   }
		      
		   return winPotential;
	   }
	   
	   public ArrayList<Integer> legalMoves(Seed[][] game) {
			ArrayList<Integer> moves = new ArrayList<Integer>();
			moves.ensureCapacity(18);
			if (win < 4) {
				for (int row = 0; row < game.length; row++) {
					for (int col = 0; col < game[0].length; col++) {
						if (game[row][col] == Seed.EMPTY) {
							moves.add(row);
							moves.add(col);
						}
					}
				}
			}
			if (win > 3) {
				for (int col = 0; col < game[0].length; col++) {
					for (int row = game.length - 1; row >= 0; row--) {
						if (game[row][col] == Seed.EMPTY) {
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
