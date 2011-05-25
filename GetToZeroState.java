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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + num;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GetToZeroState other = (GetToZeroState) obj;
		if (num != other.num)
			return false;
		return true;
	}
}
