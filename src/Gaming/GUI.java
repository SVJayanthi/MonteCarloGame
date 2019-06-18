package Gaming;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class GUI extends JFrame {
   public int rows, cols, win;

 
   public static final int CELL_SIZE = 50;
   public final int CANVAS_WIDTH = CELL_SIZE * cols;
   public final int CANVAS_HEIGHT = CELL_SIZE * rows;
   public static final int GRID_WIDTH = 4;
   public static final int GRID_WIDHT_HALF = GRID_WIDTH / 2;
   public static final int CELL_PADDING = CELL_SIZE / 6;
   public static final int SYMBOL_SIZE = CELL_SIZE - CELL_PADDING * 2;
   public static final int SYMBOL_STROKE_WIDTH = 4;
 
   public enum GameState {
      PLAYING, DRAW, CROSS_WON, NOUGHT_WON
   }
   private GameState currentState;
 
   public enum Seed {
      EMPTY, CROSS, NOUGHT
   }
   private Seed currentPlayer;
 
   private Seed[][] board;
   private DrawCanvas canvas;
   private JLabel statusBar;
 
   /** Constructor to setup the game and the GUI components */
   public GUI(int rows, int cols, int win) {
	   this.rows = rows;
	   this.cols = cols;
	   this.win = win;
      canvas = new DrawCanvas();
      canvas.setPreferredSize(new Dimension(CELL_SIZE*cols, CELL_SIZE*rows));
 
      
      canvas.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseClicked(MouseEvent e) {
            int mouseX = e.getX();
            int mouseY = e.getY();
            
            int rowSelected = mouseY / CELL_SIZE;
            int colSelected = mouseX / CELL_SIZE;
 
            if (currentState == GameState.PLAYING) {
            	if (win > 3) {
            	if (colSelected >= 0 && colSelected < cols) {
            		for (int row = rows -1; row >= 0; row--) {
            		      if (board[row][colSelected] == Seed.EMPTY) {
            		         board[row][colSelected] = currentPlayer;
            		         Simulation winCheck1 = new Simulation(board, win);
            		         currentState = winCheck1.updateGame(board, currentPlayer, row, colSelected);   
            		         currentPlayer = (currentPlayer == Seed.CROSS) ? Seed.NOUGHT : Seed.CROSS;
            		         break;
            		      }
            		   }
            	}
            	}
            	if (win < 4) {
            		if (rowSelected >= 0 && rowSelected < rows && colSelected >= 0
                            && colSelected < cols && board[rowSelected][colSelected] == Seed.EMPTY) {
                         board[rowSelected][colSelected] = currentPlayer;
        		         Simulation winCheck2 = new Simulation(board, win);
        		         currentState = winCheck2.updateGame(board, currentPlayer, rowSelected, colSelected);
        		         currentPlayer = (currentPlayer == Seed.CROSS) ? Seed.NOUGHT : Seed.CROSS;
                      }
            	}

                repaint();
                
                if (currentState == GameState.PLAYING) {
                	AIMove();
                }
                
            } else {
               initGame();
            }
            
            repaint();
         }
      });
   
 
      
      statusBar = new JLabel("  ");
      statusBar.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 15));
      statusBar.setBorder(BorderFactory.createEmptyBorder(2, 5, 4, 5));
 
      Container cp = getContentPane();
      cp.setLayout(new BorderLayout());
      cp.add(canvas, BorderLayout.CENTER);
      cp.add(statusBar, BorderLayout.PAGE_END);
 
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      pack();
      setTitle("Tic Tac Toe");
      setVisible(true);
 
      board = new Seed[rows][cols];
      initGame();
   }
 
   
   public void initGame() {
      for (int row = 0; row < rows; ++row) {
         for (int col = 0; col < cols; ++col) {
            board[row][col] = Seed.EMPTY;
         }
      }
      currentState = GameState.PLAYING;
      currentPlayer = Seed.CROSS;
   }
   
   public void AIMove() {
       Simulation sim = new Simulation(board, win);
       int[] AIMove = sim.simulate(currentPlayer);
       board[AIMove[0]][AIMove[1]] = currentPlayer;
       currentState = sim.updateGame(board, currentPlayer, AIMove[0], AIMove[1]);         
       currentPlayer = (currentPlayer == Seed.CROSS) ? Seed.NOUGHT : Seed.CROSS;
   }
 
   class DrawCanvas extends JPanel {
      @Override
      public void paintComponent(Graphics g) {
         super.paintComponent(g); 
         setBackground(Color.WHITE); 
 
         
         g.setColor(Color.BLACK);
         for (int row = 1; row < rows; ++row) {
            g.fillRoundRect(0, CELL_SIZE * row - GRID_WIDHT_HALF,
                  (CELL_SIZE*rows)-1, GRID_WIDTH, GRID_WIDTH, GRID_WIDTH);
         }
         for (int col = 1; col < cols; ++col) {
            g.fillRoundRect(CELL_SIZE * col - GRID_WIDHT_HALF, 0,
                  GRID_WIDTH, (CELL_SIZE*cols)-1, GRID_WIDTH, GRID_WIDTH);
         }
 
         
         Graphics2D g2d = (Graphics2D)g;
         g2d.setStroke(new BasicStroke(SYMBOL_STROKE_WIDTH, BasicStroke.CAP_ROUND,
               BasicStroke.JOIN_ROUND));
         for (int row = 0; row < rows; ++row) {
            for (int col = 0; col < cols; ++col) {
               int x1 = col * CELL_SIZE + CELL_PADDING;
               int y1 = row * CELL_SIZE + CELL_PADDING;
               if (board[row][col] == Seed.CROSS) {
                  g2d.setColor(Color.RED);
                  int x2 = (col + 1) * CELL_SIZE - CELL_PADDING;
                  int y2 = (row + 1) * CELL_SIZE - CELL_PADDING;
                  if (win > 3) {
                	  g2d.setColor(Color.RED);
                	  g2d.drawOval(x1, y1, SYMBOL_SIZE, SYMBOL_SIZE);
                	  g2d.fillOval(x1, y1, SYMBOL_SIZE, SYMBOL_SIZE);
                  } else {
                  g2d.drawLine(x1, y1, x2, y2);
                  g2d.drawLine(x2, y1, x1, y2);
                  }
               }
               if (board[row][col] == Seed.NOUGHT) {
                  g2d.setColor(Color.BLUE);
                  g2d.drawOval(x1, y1, SYMBOL_SIZE, SYMBOL_SIZE);
                  if (win > 3) {
                	  g2d.fillOval(x1, y1, SYMBOL_SIZE, SYMBOL_SIZE);
                  }
                  
               }
            }
         }
 
         
         if (currentState == GameState.PLAYING) {
            statusBar.setForeground(Color.BLACK);
            if (currentPlayer == Seed.CROSS) {
               statusBar.setText("Your Turn");
            } else {
               statusBar.setText("Computer's Turn");
            }
         } else if (currentState == GameState.DRAW) {
            statusBar.setForeground(Color.RED);
            statusBar.setText("It's a Draw! Click to play again.");
         } else if (currentState == GameState.CROSS_WON) {
            statusBar.setForeground(Color.RED);
            statusBar.setText("'X' Won! Click to play again.");
         } else if (currentState == GameState.NOUGHT_WON) {
            statusBar.setForeground(Color.RED);
            statusBar.setText("'O' Won! Click to play again.");
         }
      }
   }
}