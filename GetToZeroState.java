import java.util.Set;
import java.util.HashSet;

public class GetToZeroState implements State {
	private int num;

	public GetToZeroState (int start)
	{
		num = start;
	}

	@Override
	public Set<State> nextStates()
	{
		Set<State> nextStates = new HashSet<State>();
		nextStates.add(new GetToZeroState(num-1));
		nextStates.add(new GetToZeroState(num+1));
		return nextStates;
	}

	@Override
	public boolean isWinning()
	{
		return num == 0;
	}

	public static void main(String[] args)
	{
		System.out.println(new BFSSolver().solve(new GetToZeroState(3)));
		System.out.println(new BFSSolver().solve(new GetToZeroState(-9)));
		System.out.println(new BFSSolver().solve(new GetToZeroState(12)));
	}
}
