import junit.framework.TestCase;

// This class is a JUnit test-case container. Eclipse knows how to run these without a main function.
// Use File->New->JUnit testcase to create one, just like a class!
// These are the tests from the description of the Zaria exercise.
public class ZariaStateTest extends TestCase {

	public void testSolve_1() {
		State initState = new ZariaState(3, 2, new int[] {3,5}, new boolean[][] {{false,true,false},{true,false,true},{false,true,false}});
		System.out.println("Test1");
		Test.printPath(new BFSSolver().solve(initState));
		System.out.println();
	}

	public void testSolve_2() {
		State initState = new ZariaState(4, 1, new int[] {1}, new boolean[][] {{false,true,false,false},{false,false,true,false},{false,false,false,true},{false,false,false,false}});
		System.out.println("Test2");
		Test.printPath(new BFSSolver().solve(initState));
		System.out.println();
	}

	public void testSolve_3() {
		State initState = new ZariaState(3, 3, new int[] {4,2,6}, new boolean[][] {{false,true,false},{false,false,true},{false,false,false}});
		System.out.println("Test3");
		Test.printPath(new BFSSolver().solve(initState));
		System.out.println();
	}
}
