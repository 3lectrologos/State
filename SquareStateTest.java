import junit.framework.TestCase;
import java.util.*;

public class SquareStateTest extends TestCase {
	public void testFinal() {
		int[][] board = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
		State state = new SquaresState(board);
		
		assertTrue(state.isWinning());
	}
	
	public void testSize() {
		SquaresState state1 = new SquaresState(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 0}});

		assertEquals(3, state1.getSize());
	}
	
	public void testStateEquality() {
		State state1 = new SquaresState(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 0}});
		State state2 = new SquaresState(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 0}});
		State state3 = new SquaresState(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 0, 8}});
		
		assertTrue(state1.equals(state2));
		assertFalse(state1.equals(state3));
		assertFalse(state2.equals(state3));
	}
	
	public void testCanGoUp() {
		SquaresState state1 = new SquaresState(new int[][] {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}});
		SquaresState state2 = new SquaresState(new int[][] {{1, 2, 0}, {3, 4, 5}, {6, 7, 8}});
		
		assertTrue(state1.canGoUp());
		assertFalse(state2.canGoUp());
	}
	
	public void testGoUp() {
		SquaresState state1 = new SquaresState(new int[][] {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}});
		SquaresState state2 = new SquaresState(new int[][] {{1, 2, 3}, {4, 5, 0}, {7, 8, 6}});
		
		try {
			assertTrue(state2.equals(state1.goUp()));
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
	}
	
	public void testGoLeft() {
		SquaresState state1 = new SquaresState(new int[][] {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}});
		SquaresState state2 = new SquaresState(new int[][] {{1, 2, 3}, {4, 5, 6}, {7, 0, 8}});
		
		try {
			assertTrue(state2.equals(state1.goLeft()));
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
	}
	
	public void testNext() {
		SquaresState state1 = new SquaresState(new int[][] {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}});
		SquaresState state2 = new SquaresState(new int[][] {{1, 2, 3}, {4, 5, 0}, {7, 8, 6}});
		SquaresState state3 = new SquaresState(new int[][] {{1, 2, 3}, {4, 5, 6}, {7, 0, 8}});

		Set<State> set = state1.nextStates();
		set.remove(state2);
		set.remove(state3);
		assertTrue(set.isEmpty());
	}
	
	public void testNext2() {
		SquaresState state1 = new SquaresState(new int[][] {{3, 1, 4}, {7, 0, 8}, {2, 6, 5}});
		SquaresState state2 = new SquaresState(new int[][] {{3, 1, 4}, {0, 7, 8}, {2, 6, 5}});
		SquaresState state3 = new SquaresState(new int[][] {{3, 0, 4}, {7, 1, 8}, {2, 6, 5}});
		SquaresState state4 = new SquaresState(new int[][] {{3, 1, 4}, {7, 8, 0}, {2, 6, 5}});
		SquaresState state5 = new SquaresState(new int[][] {{3, 1, 4}, {7, 6, 8}, {2, 0, 5}});

		Set<State> set = state1.nextStates();
		set.remove(state2);
		set.remove(state3);
		set.remove(state4);
		set.remove(state5);
		assertTrue(set.isEmpty());
	}
}
