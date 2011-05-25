public class Test {
	public static void main(String[] args)
	{
		System.out.println(new BFSSolver().solve(new GetToZeroState(3)));
		System.out.println(new BFSSolver().solve(new GetToZeroState(-9)));
		System.out.println(new BFSSolver().solve(new GetToZeroState(12)));

		System.out.println(new RandomSolver(1).solve(new GetToZeroState(2)));
	}
}
