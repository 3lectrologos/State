import java.util.Set;

public interface State {
    public Set<State> nextStates();
    public boolean isWinning();
}
