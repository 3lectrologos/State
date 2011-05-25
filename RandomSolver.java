import java.util.*;

public class RandomSolver implements Solver {
	private Random random;

	public RandomSolver(long seed)
	{
		random = new Random(seed);
	}

	public Deque<State> solve(State initialState)
	{
		State currState = initialState;
		Deque<State> path = new LinkedList<State>();
		while (!currState.isWinning()) {
			path.addLast(currState);
			Set<State> nextStates = currState.nextStates();
			int size = nextStates.size();
			if (size < 1)
				return null;
			int toRemove = random.nextInt(size);
			Iterator<State> iter = nextStates.iterator();
			for (int i = 0; i < toRemove; i++)
				iter.next();
			currState = iter.next();
		}
		path.addLast(currState);
		return path;
	}
}
