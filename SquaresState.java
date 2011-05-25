import java.util.*;


public class SquaresState implements State {
	private int[][] board;
	private int size;
	
	public SquaresState(int[][] board) {
		this.board = board;
		this.size = board.length;
	}
	
	public Point whereis(int block) {
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				if(get(i, j) == block)
					return new Point(i, j);
			}
		}
		return null;
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
		return whereis(0).getX() > 0;
	}
	
	public boolean canGoDown() {
		return whereis(0).getX() < size - 1;
	}
	
	public boolean canGoLeft() {
		return whereis(0).getY() > 0;
	}
	
	public boolean canGoRight() {
		return whereis(0).getY() < size - 1;
	}
	
	public SquaresState goUp() throws CloneNotSupportedException {
		SquaresState newstate = (SquaresState)this.clone();
		Point zero = whereis(0);
		newstate.swap(zero, new Point(zero.getX() - 1, zero.getY()));
		
		return newstate;
	}
	
	public SquaresState goDown() throws CloneNotSupportedException {
		SquaresState newstate = (SquaresState)this.clone();
		Point zero = whereis(0);
		newstate.swap(zero, new Point(zero.getX() + 1, zero.getY()));
		
		return newstate;
	}
	
	public SquaresState goLeft() throws CloneNotSupportedException {
		SquaresState newstate = (SquaresState)this.clone();
		Point zero = whereis(0);
		newstate.swap(zero, new Point(zero.getX(), zero.getY() - 1));
		
		return newstate;
	}
	
	public SquaresState goRight() throws CloneNotSupportedException {
		SquaresState newstate = (SquaresState)this.clone();
		Point zero = whereis(0);
		newstate.swap(zero, new Point(zero.getX(), zero.getY() + 1));
		
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
	
	public void swap(Point p1, Point p2) {
		int tmp = board[p1.getX()][p1.getY()];
		board[p1.getX()][p1.getY()] = board[p2.getX()][p2.getY()];
		board[p2.getX()][p2.getY()] = tmp;
	}
	
	public boolean isWinning() {
		int[][] winning  = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
		return Arrays.deepEquals(board, winning);
	}
	
	public boolean equals(Object o) {
		return o.getClass().equals(this.getClass()) &&
		       Arrays.deepEquals(((SquaresState)o).getBoard(), board);
	}
	
	public int hashCode() {
		return 42;
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
