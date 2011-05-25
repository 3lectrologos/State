import java.util.Deque;

public interface Solver {
	public Deque<State> solve(State initialState);
}
