package Gaming;
import javax.swing.*;

public class Connect4Game {
	public static void main(String[] args) {
	      // Run GUI codes in the Event-Dispatching thread for thread safety
	      SwingUtilities.invokeLater(new Runnable() {
	         @Override
	         public void run() {
	            new GUI(7, 7, 4); // Let the constructor do the job
	         }
	      });
	   }
}
