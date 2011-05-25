import java.util.*;


public class SquaresState implements State {
	private byte[][] board;
	private byte size;
	private byte zerox, zeroy;
	private byte[][] winning;
	
	public SquaresState(byte[][] board) {
		this.board = board;
		this.size = (byte) board.length;
		winning = new byte[size][size];
		for(byte i = 0; i < size; i++) {
			for(byte j = 0; j < size; j++) {
				winning[i][j] = (byte) (1 + j + i*size);
				if(get(i, j) == 0) {
					zerox = i;
					zeroy = j;
				}
			}
		}
		winning[size - 1][size - 1] = 0;
		Arrays.deepToString(winning);
	}
	
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
	
	public boolean canGoUp() {
		return zerox > 0;
	}
	
	public boolean canGoDown() {
		return zerox < size - 1;
	}
	
	public boolean canGoLeft() {
		return zeroy > 0;
	}
	
	public boolean canGoRight() {
		return zeroy < size - 1;
	}
	
	public SquaresState goUp() throws CloneNotSupportedException {
		SquaresState newstate = (SquaresState)this.clone();
		newstate.swap_with((byte) (zerox - 1), zeroy);
		
		return newstate;
	}
	
	public SquaresState goDown() throws CloneNotSupportedException {
		SquaresState newstate = (SquaresState)this.clone();
		newstate.swap_with((byte) (zerox + 1), zeroy);
		
		return newstate;
	}
	
	public SquaresState goLeft() throws CloneNotSupportedException {
		SquaresState newstate = (SquaresState)this.clone();
		newstate.swap_with(zerox, (byte) (zeroy - 1));
		
		return newstate;
	}
	
	public SquaresState goRight() throws CloneNotSupportedException {
		SquaresState newstate = (SquaresState)this.clone();
		newstate.swap_with(zerox, (byte) (zeroy + 1));
		
		return newstate;
	}
	
	public SquaresState clone() {
		byte[][] newboard = new byte[size][size];
		for(byte i = 0; i < size; i++) {
			for(byte j = 0; j < size; j++) {
				newboard[i][j] = board[i][j];
			}
		}
		return new SquaresState(newboard);
	}

	private byte get(byte x, byte y) {
		return board[x][y];
	}
	
	public void swap_with(byte px, byte py) {
		byte tmp = board[px][py];
		board[px][py] = 0;
		board[zerox][zeroy] = tmp;
		zerox = px;
		zeroy = py;
	}
	
	public boolean isWinning() {
		return Arrays.deepEquals(board, winning);
	}
	
	public boolean equals(Object o) {
		return o.getClass().equals(this.getClass()) &&
		       Arrays.deepEquals(((SquaresState)o).getBoard(), board);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.deepHashCode(board);
		return result;
	}

	public byte[][] getBoard() {
		return board;
	}
	
	public byte getSize() {
		return size;
	}
	
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
