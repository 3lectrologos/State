import java.util.*;

// A very simple (and inefficient) solver that just tries moves at random.
public class RandomSolver implements Solver {
	private Random random;

	public RandomSolver(long seed)
	{
		// Initialize the random number generator with a provided seed.
		random = new Random(seed);
	}

	public Deque<State> solve(State initialState)
	{
		State currState = initialState;
		// Initialize an empty path.
		Deque<State> path = new LinkedList<State>();
		// While the current state is not a winning one...
		while (!currState.isWinning()) {
			// Append the current state to the path.
			path.addLast(currState);
			// Get all the states which can be reached with a single
			// move from the current state.
			Set<State> nextStates = currState.nextStates();
			int size = nextStates.size();
			// If there are no moves from this state, return null.
			if (size < 1)
				return null;
			// Else, pick one of them at random.
			int toRemove = random.nextInt(size);
			Iterator<State> iter = nextStates.iterator();
			for (int i = 0; i < toRemove; i++)
				iter.next();
			currState = iter.next();
		}
		// Append the winning state to the path and return it.
		path.addLast(currState);
		return path;
	}
}
