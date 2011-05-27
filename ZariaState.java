import java.util.HashSet;
import java.util.Set;

// ZariaState is a state to solve the Zaria Exercise from 2009
// http://courses.softlab.ntua.gr/pl1/2009a/Exercises/exerc09-2.pdf
public class ZariaState implements State {
	
	// Parameters that do not belong to each specific state but range across all
	// instances are stored as static variables (belonging to the class itself)
	
	static private int target; // The target node of the graph. ASSUMED TO BE THE LAST.
	
	static private int dicen;  // The number of elements in the dice array
	static private int[] dice; // The elements of the dice array 
	
	static private boolean[][] adjMatrix; // The adjacency matrix of the graph
	
	// Parameters that tell each state apart from all others are private variables .
	
	private int node, dicei; // Which node the player is on and which dice he'll use next. 

	// Constructor for the initial state. This places the player on the first node
	// with the first dice, but initializes all the static variables!
	
	public ZariaState(int nodes, int dicen, int[] dice, boolean[][] adjMatrix) {
		target = nodes;
		ZariaState.dicen = dicen;
		ZariaState.dice = dice;
		ZariaState.adjMatrix = adjMatrix;
		this.node = 0;
		this.dicei = 0;
	}

	// Constructor that specifies the private variables of each new state.
	
	public ZariaState(int node, int dicei) {
		this.node = node;
		this.dicei = dicei;
	}

	// Automatically generated toString (from Source->generate toString)
	// Used for debugging
	public String toString() {
		return "ZariaState [dicei=" + dicei + ", node=" + node + "]";
	}

	// Automatically generated hashCode (from Source->generate hashCode and equals)
	// Used by Java classes that use hashing (let's leave it there. Read more on the net!)
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + dicei;
		result = prime * result + node;
		return result;
	}

	// Automatically generated equals (from Source->generate hashCode and equals)
	// Used to compare two states and find duplicates.
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

	// A state is winning if the node is the last one! (target-1)
	public boolean isWinning() {
		return node == target-1;
	}

	// To find the next states:
	public Set<State> nextStates() {
		// Initialize a set to return as a result
		Set<State> next = new HashSet<State>();
		// Find the dice we are going to use
		int dice = ZariaState.dice[this.dicei];
		// Initialize an integer set to store the nodes that are reachable using the dice
		// from the current one
		Set<Integer> neighs = new HashSet<Integer>();
		// Initialize the set with the current node
		neighs.add(this.node);
		// While we have not exhausted the dice:
		while (dice>0) {
			// subtract one
			dice--;
			// using the adjacency matrix find all the neighbors of the nodes in the neighs set
			// and use them as a new base to recalculate nodes!
			neighs = getNeighbours(neighs);
		}
		// The new dice index is dicei+1 mod (the number of dice)
		int newdicei = (this.dicei+1)%ZariaState.dicen;
		// Create a new state for each node from the integer set and add them to the result!
		for (int n: neighs)
			next.add(new ZariaState(n,newdicei));
		return next;
	}

	// Find all the neighbors for a set of nodes
	private Set<Integer> getNeighbours(Set<Integer> nodes){
		Set<Integer> neighs = new HashSet<Integer>();
		for (int n : nodes)
			neighs.addAll(getNeighbours(n));
		return neighs;
	}

	// Find all the neighbors for one node
	private Set<Integer> getNeighbours(int node){
		Set<Integer> neighs = new HashSet<Integer>();
		for(int i=0;i<target;i++)
			if (ZariaState.adjMatrix[node][i]) neighs.add(i);
		return neighs;
	}
	
}
