package pl1.lab2.squares;
import java.util.*;

import pl1.lab2.statespace.State;

/*
 * State representation for sliding squares puzzle of arbitrary size
 * (more info on the problem at http://en.wikipedia.org/wiki/Fifteen_puzzle).
 */
public class SquaresState implements State {
	private byte[][] board;		// Current board configuration
	private byte size;			// Board size
	private byte zerox, zeroy;  // Position of the empty square
	private byte[][] winning;	// Target board configuration
	
	/*
	 * Constructor receives a board matrix of the initial configuration
	 * (the empty square is represented by the number zero).
	 */
	public SquaresState(byte[][] board) {
		this.board = board;
		this.size = (byte) board.length;
		winning = new byte[size][size];
		/*
		 * The operation of this loop is threefold:
		 *   - Copy the board to the instance field
		 *   - Create the winning configuration
		 *   - Find the initial position of the empty square
		 */
		for(byte i = 0; i < size; i++) {
			for(byte j = 0; j < size; j++) {
				winning[i][j] = (byte) (1 + j + i*size);
				if(board[i][j] == 0) {
					zerox = i;
					zeroy = j;
				}
			}
		}
		winning[size - 1][size - 1] = 0;
	}
	
	/*
	 * Possible next states are created by moving the empty
	 * square up, down, left or right, and inserted into a set.
	 */
	public Set<State> nextStates() {
		Set<State> next = new HashSet<State>();
		
		try {
			if(canGoUp()) {
				next.add(goUp());
			}
			if(canGoDown()) {
				next.add(goDown());
			}
			if(canGoLeft()) {
				next.add(goLeft());
			}
			if(canGoRight()) {
				next.add(goRight());
			}
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return next;
	}
	
	/*
	 * Methods canGo[...] check if the empty square can be moved
	 * in the corresponding direction.
	 */
	private boolean canGoUp() {
		return zerox > 0;
	}
	
	private boolean canGoDown() {
		return zerox < size - 1;
	}
	
	private boolean canGoLeft() {
		return zeroy > 0;
	}
	
	private boolean canGoRight() {
		return zeroy < size - 1;
	}
	
	/*
	 * Methods go[...] create a new state that represents the result
	 * of moving the empty square in the corresponding direction.
	 * This is accomplished by first cloning this state and subsequently
	 * moving the empty square in the new state (using swap_with).
	 */
	private SquaresState goUp() throws CloneNotSupportedException {
		SquaresState newstate = (SquaresState)this.clone();
		newstate.swap_with((byte) (zerox - 1), zeroy);
		
		return newstate;
	}
	
	private SquaresState goDown() throws CloneNotSupportedException {
		SquaresState newstate = (SquaresState)this.clone();
		newstate.swap_with((byte) (zerox + 1), zeroy);
		
		return newstate;
	}
	
	private SquaresState goLeft() throws CloneNotSupportedException {
		SquaresState newstate = (SquaresState)this.clone();
		newstate.swap_with(zerox, (byte) (zeroy - 1));
		
		return newstate;
	}
	
	private SquaresState goRight() throws CloneNotSupportedException {
		SquaresState newstate = (SquaresState)this.clone();
		newstate.swap_with(zerox, (byte) (zeroy + 1));
		
		return newstate;
	}
	
	/*
	 * Attention! This method has to be overridden in order
	 * to work as expected. In particular, the board has to be
	 * "cloned" manually.
	 */
	public SquaresState clone() {
		byte[][] newboard = new byte[size][size];
		for(byte i = 0; i < size; i++) {
			for(byte j = 0; j < size; j++) {
				newboard[i][j] = board[i][j];
			}
		}
		return new SquaresState(newboard);
	}

	/*
	 * Effectively moves the empty square into
	 * position with coordinates px, py.
	 */
	private void swap_with(byte px, byte py) {
		byte tmp = board[px][py];
		board[px][py] = 0;
		board[zerox][zeroy] = tmp;
		zerox = px;
		zeroy = py;
	}
	
	// Note the use of deepEquals to check if this state is winning.
	public boolean isWinning() {
		return Arrays.deepEquals(board, winning);
	}
	
	/*
	 * Attention! Apart from explicit comparison, i.e. StateOne.equals(StateTwo),
	 * also needs to be overridden together with hashCode() for any "hashing"
	 * data structure (e.g. HashSet, HashMap, etc.) to work correctly.
	 */
	public boolean equals(Object o) {
		return o.getClass().equals(this.getClass()) &&
		       Arrays.deepEquals(((SquaresState)o).getBoard(), board);
	}

	/*
	 * Returning just an integer (e.g. public int hashCode() { return 42; })
	 * would suffice. The rest is just used to make "hashing" data structures
	 * more efficient.
	 */
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.deepHashCode(board);
		return result;
	}

	public byte[][] getBoard() {
		return board;
	}
	
	// Only needed for printing the state.
	public String toString() {
		String str = "";
		for(byte i = 0; i < size; i++) {
			for(byte j = 0; j < size; j++) {
				str += String.format("%2d ", board[i][j]);
			}
			str += "\n";
		}
		return str;
	}
  
}
