import java.util.*;

public class BFSSolver implements Solver {
	public BFSSolver() {}

	public Deque<State> solve(State initialState)
	{
		Map<State,State> visited = new HashMap<State,State>();
		visited.put(initialState, null);
		List<State> fringe = new LinkedList<State>();
		fringe.add(initialState);
		List<State> nextFringe = new LinkedList<State>();

		while (true) {
			for (State currState : fringe) {
				if (currState.isWinning()) {
					State s = currState;
					Deque<State> path = new LinkedList<State>();
					while (s != null) {
						path.addFirst(s);
						s = visited.get(s);
					}
					return path;
				}
				Set<State> nextStates = currState.nextStates();
				for (State s : nextStates)
					if (!visited.containsKey(s)) {
						nextFringe.add(s);
						visited.put(s, currState);
					}
			}
			if (nextFringe.isEmpty())
				return null;
			fringe = nextFringe;
			nextFringe = new LinkedList<State>();
		}
	}
}
