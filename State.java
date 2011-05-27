import java.util.Set;

// State: The representation of a game's state, and common operations on that
// representation.
public interface State {
	// Produces all the states which can be reached with a single move from
	// this one.
	public Set<State> nextStates();
	// Tests if this state is a winning state.
	public boolean isWinning();
}

// Also required:
// a constructor:
//     Used to create the initial state. The format of this constructor will
//     depend on the particular game.
// boolean equals(Object anotherObject):
//     Used when deciding if a state has already been visited. This method is
//     inherited from the Object class, but the default comparison is not
//     sufficient for our needs, so we will have to rewrite it. The Java
//     language, then, requires that we also rewrite hashCode() (see
//     http://download.oracle.com/javase/6/docs/api/java/lang/Object.html
//     for more information). Eclipse can do this automatically (Source >
//     Generate equals and hashCode).
// String toString():
//     We can overwrite the default toString method inherited from Object to
//     output a more user-friendly representation of the game's state
//     (optional operation).
