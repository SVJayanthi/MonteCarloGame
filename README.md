# MonteCarloGame
This repository contains the code utilized on the Eclipse platform to create the a computer board game player that plays Tic-tac-toe and Connect 4.

<img align="left" width="420" height="420" src="https://github.com/SVJayanthi/MonteCarloGame/blob/master/GUI/TTT.PNG">
<img align="right" width="420" height="420" src="https://github.com/SVJayanthi/MonteCarloGame/blob/master/GUI/Connect4.png">


## Monte Carlo
This is a Java program with a decision-making computer player that utilizes Monte Carlo simulation methods to predict and execute moves in Tic-tac-toe. This program allows for a user to play against the simulation iteration of the computer. It has both a graphical user interface.

### Goal
The application pertained to utilizing an Original Monte Carlo simulation with Monte Carlo Markov Chain theory to generate many random possible simulations of possible moves and determining the ideal move for the player. The Tic-Tac-Toe game has two players who alternate turns and fill in a space in the three by three board until they reach three in a row of their piece. The game often ends in a draw however, there are certain formidable formations that give players distinct advantages in winning the game. Similarly, Connect 4 has two players who instead seek to reach four pieces in a row and can only place the pieces at the open space in the bottom of each column. The goal for both would be for the computer player utilizing Original Monte Carlo simulations to beat an opponent.

### Game GUI
To run the program, open the GUI file from 
`MonteCarloGame\src\Gaming\TTT.java` for Tic-tac-toe
or
`MonteCarloGame\src\Gaming\Connect4.java` for Connect4
in a Java IDE and run the interface to play the game.

### Conclusions
The simulations are utilized to determine what move the computer will make when playing the game. The random element of the search allows for a broad mean to be determined for each possible move. The system is structured so that the quantity of random samples generated is sufficient enough to justify that further exploration would not significantly alter the results. Therefore, simulations through random sampling can be implemented for a decision-making system in order to determine the best move for the player. 

## License
Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:  
The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

[MIT](LICENSE)
