import java.util.HashSet;
import java.util.Set;

public class ZariaState implements State {
	
	static private int target;
	
	static private int dicen;
	static private int[] dice;
	
	static private boolean[][] adjMatrix;
	
	private int node, dicei;

	@Override
	public boolean isWinning() {
		return node == target-1;
	}

	@Override
	public Set<State> nextStates() {
		Set<State> next = new HashSet<State>();
		int dice = ZariaState.dice[this.dicei];
		Set<Integer> neighs = new HashSet<Integer>();
		neighs.add(this.node);
		while (dice>0) {
			dice--;
			neighs = getNeighbours(neighs);
		}
		int newdicei = (this.dicei+1)%ZariaState.dicen;
		for (int n: neighs)
			next.add(new ZariaState(n,newdicei));
		return next;
	}

	@Override
	public String toString() {
		return "ZariaState [dicei=" + dicei + ", node=" + node + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + dicei;
		result = prime * result + node;
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
		ZariaState other = (ZariaState) obj;
		if (dicei != other.dicei)
			return false;
		if (node != other.node)
			return false;
		return true;
	}

	private Set<Integer> getNeighbours(int node){
		Set<Integer> neighs = new HashSet<Integer>();
		for(int i=0;i<target;i++)
			if (ZariaState.adjMatrix[node][i]) neighs.add(i);
		return neighs;
	}
	
	private Set<Integer> getNeighbours(Set<Integer> nodes){
		Set<Integer> neighs = new HashSet<Integer>();
		for (int n : nodes)
			neighs.addAll(getNeighbours(n));
		return neighs;
	}
	
	public ZariaState(int nodes, int dicen, int[] dice, boolean[][] adjMatrix) {
		target = nodes;
		ZariaState.dicen = dicen;
		ZariaState.dice = dice;
		ZariaState.adjMatrix = adjMatrix;
		this.node = 0;
		this.dicei = 0;
	}

	public ZariaState(int node, int dicei) {
		this.node = node;
		this.dicei = dicei;
	}

}
