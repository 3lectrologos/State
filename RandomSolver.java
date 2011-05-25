import java.util.*;

public class RandomSolver implements Solver {
	private Random random;

	public RandomSolver(long seed)
	{
		random = new Random(seed);
	}

	public int solve(State initialState)
	{
		State currState = initialState;
		int moves = 0;
		while (!currState.isWinning()) {
			Set<State> nextStates = currState.nextStates();
			int size = nextStates.size();
			if (size < 1)
				return -1;
			int toRemove = random.nextInt(size);
			Iterator<State> iter = nextStates.iterator();
			for (int i = 0; i < toRemove; i++)
				iter.next();
			currState = iter.next();
			moves++;
		}
		return moves;
	}
}
