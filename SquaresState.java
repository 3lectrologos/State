import java.util.*;


public class SquaresState implements State {
	private int[][] board;
	private int size;
	private int zerox, zeroy;
	private int[][] winning;
	
	public SquaresState(int[][] board) {
		this.board = board;
		this.size = board.length;
		winning = new int[size][size];
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				winning[i][j] = 1 + j + i*size;
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
		newstate.swap_with(zerox - 1, zeroy);
		
		return newstate;
	}
	
	public SquaresState goDown() throws CloneNotSupportedException {
		SquaresState newstate = (SquaresState)this.clone();
		newstate.swap_with(zerox + 1, zeroy);
		
		return newstate;
	}
	
	public SquaresState goLeft() throws CloneNotSupportedException {
		SquaresState newstate = (SquaresState)this.clone();
		newstate.swap_with(zerox, zeroy - 1);
		
		return newstate;
	}
	
	public SquaresState goRight() throws CloneNotSupportedException {
		SquaresState newstate = (SquaresState)this.clone();
		newstate.swap_with(zerox, zeroy + 1);
		
		return newstate;
	}
	
	public SquaresState clone() {
		int[][] newboard = new int[size][size];
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				newboard[i][j] = board[i][j];
			}
		}
		return new SquaresState(newboard);
	}

	private int get(int x, int y) {
		return board[x][y];
	}
	
	public void swap_with(int px, int py) {
		int tmp = board[px][py];
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

	public int[][] getBoard() {
		return board;
	}
	
	public int getSize() {
		return size;
	}
	
	public String toString() {
		String str = "";
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				str += board[i][j];
			}
			str += "\n";
		}
		return str;
	}
  
}
