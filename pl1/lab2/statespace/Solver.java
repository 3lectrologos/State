package pl1.lab2.statespace;
import java.util.Deque;


// Solver: Implementation of a graph search algorithm in the state space of some
// game.
public interface Solver {
	// Runs the solver, starting from initialState. Returns a queue of
	// States that represents the path from initialState to a winning state,
	// if it can find one. Else, returns null.
	public Deque<State> solve(State initialState);
}

// Also required:
// a constructor:
//     Used to create a Solver instance. The format of this constructor will
//     depend on the particular solver.
