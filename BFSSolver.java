import java.util.*;

public class BFSSolver implements Solver {
	public BFSSolver() {}

	public int solve(State initialState)
	{
		Set<State> visited = new HashSet<State>();
		visited.add(initialState);
		Queue<State> fringe = new LinkedList<State>();
		fringe.add(initialState);
		Queue<State> nextFringe = new LinkedList<State>();
		int moves = 0;

		while (true) {
			for (State currState : fringe) {
				if (currState.isWinning())
					return moves;
				Set<State> nextStates = currState.nextStates();
				for (State s : nextStates)
					if (!visited.contains(s)) {
						nextFringe.add(s);
						visited.add(s);
					}
			}
			if (nextFringe.isEmpty())
				return -1;
			fringe = nextFringe;
			nextFringe = new LinkedList<State>();
			moves++;
		}
	}
}
