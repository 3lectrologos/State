import java.util.*;

public class Test {
	public static void printPath(Deque<State> path)
	{
		if (path != null)
			for (State step : path)
				System.out.println(step.toString());
		else
			System.out.println("No path!");
	}

	public static void main(String[] args)
	{
		printPath(new BFSSolver().solve(new GetToZeroState(3)));
		printPath(new BFSSolver().solve(new GetToZeroState(-9)));
		printPath(new BFSSolver().solve(new GetToZeroState(12)));
		printPath(new RandomSolver(1).solve(new GetToZeroState(2)));
	}
}