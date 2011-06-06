package pl1.lab2.statespace;

import java.util.Deque;

public class Util {
	public static void printPath(Deque<State> path)
	{
		if (path != null)
			for (State step : path)
				System.out.println(step.toString());
		else
			System.out.println("No path!");
	}
}
