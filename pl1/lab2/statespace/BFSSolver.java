package pl1.lab2.statespace;
import java.util.*;


// A solver that implements a Breadth-First Search on the state space.
// Guaranteed to return a path of minimum length.
public class BFSSolver implements Solver {
	public BFSSolver() {} // This solver requires no initialization.

	public Deque<State> solve(State initialState)
	{
		// visited: a data structure that maps states ('keys') to their
		// parent state in the path ('values'). Duplicate keys are not
		// allowed. HashMap supports fast addition and lookup.
		Map<State,State> visited = new HashMap<State,State>();
		// fringe: a data structure that holds all the states at a
		// certain length from the initial state.
		List<State> fringe = new LinkedList<State>();
		// nextFringe: an intermediate store for the states that will be
		// processed at the next iteration.
		List<State> nextFringe = new LinkedList<State>();

		// Initialize the 'visited' map with the initial state. We use
		// null to signify that the initial state has no parent.
		visited.put(initialState, null);
		// Initialize 'fringe' with the initial state.
		fringe.add(initialState);

		while (true) {
			// For each state in the current fringe...
			for (State currState : fringe) {
				// If it is a winning state...
				if (currState.isWinning()) {
					// Calculate the path from the initial
					// state to the winning state (based on
					// the parent pointers) and return it.
					State s = currState;
					Deque<State> path = new LinkedList<State>();
					while (s != null) {
						path.addFirst(s);
						s = visited.get(s);
					}
					return path;
				}
				// Else, produce all the states that can be
				// reached in one move from the current state.
				Set<State> nextStates = currState.nextStates();
				// For each one of those states...
				for (State s : nextStates)
					// If it hasn't been visited already...
					if (!visited.containsKey(s)) {
						// Store it for the next iteration.
						nextFringe.add(s);
						// Mark it as visited.
						visited.put(s, currState);
					}
			}
			// If there are no states for the next iteration, it
			// means the game has no solution, so we return null.
			if (nextFringe.isEmpty())
				return null;
			// Else, swap the fringes and start over (with length+1).
			fringe = nextFringe;
			nextFringe = new LinkedList<State>();
		}
	}
}
