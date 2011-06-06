package pl1.lab2.squares;
import junit.framework.TestCase;

import pl1.lab2.statespace.*;

/*
 * Two nine-puzzle tests and one easy fifteen-puzzle test.
 */
public class SquaresStateTest extends TestCase {
	public void testSolve_9_simple() {
		State initState = new SquaresState(new byte[][] {{1, 6, 2}, {4, 0, 3}, {7, 5, 8}});
		System.out.println("Path:");
		Util.printPath(new BFSSolver().solve(initState));
	}
	
	public void testSolve_9_hard() {
		State initState = new SquaresState(new byte[][] {{1, 6, 2}, {4, 5, 7}, {0, 8, 3}});
		System.out.println("Path:");
		Util.printPath(new BFSSolver().solve(initState));
	}
	
	public void testSolve_15_simple() {
		State initState = new SquaresState(new byte[][] {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 0, 14, 15}});
		System.out.println("Path:");
		Util.printPath(new BFSSolver().solve(initState));
	}
}
