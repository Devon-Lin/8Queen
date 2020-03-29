import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	private static final int MAX_THREADS_COUNT = 10;

	public static void main(String[] args) {
		ThreadGroup threadGroup = new ThreadGroup("ThreadGroup");
		int n = 0;

		try (Scanner s=new Scanner(System.in)) {
			while (true){
				System.out.println("Enter the number of Queens :");
				n = s.nextInt();
				if ( n == 2 || n ==3) {
					System.out.println("No Solution possible for "+ n +" Queens. Please enter another number");
				}
				else
					break;
			}
		}

		long timestamp1 = System.currentTimeMillis();

		System.out.println("Solution to "+ n +" queens using hill climbing search: ");

		List<HillClimbingSearch> hillClimbs = new ArrayList<>();
		List<Thread> threads = new ArrayList<>();

		for(int i = 0; i < MAX_THREADS_COUNT; i++) {
			var currHill = new HillClimbingSearch(n);
			hillClimbs.add(currHill);
			threads.add(new Thread(threadGroup, currHill, "Thread" + i));
		}

		threads.forEach(Thread::start);

		while(threadGroup.activeCount() == threads.size()){
			System.out.println("Finding solution (Active Threads: " + threadGroup.activeCount() + ")");
		}

		threadGroup.interrupt();
		/*
		hillClimbs.forEach(curr -> {
			if(curr.getFinalSolution() != null) {
				System.out.println("The solution found is: ");
				curr.printState(curr.getFinalSolution());
			}
		});
		*/

		for (HillClimbingSearch hillClimb : hillClimbs) {
			if (hillClimb.getFinalSolution() != null) {
				System.out.println("The solution found is: ");
				hillClimb.printState(hillClimb.getFinalSolution());
			}
		}


		//Printing the solution
		long timestamp2 = System.currentTimeMillis();

		long timeDiff = timestamp2 - timestamp1;
		System.out.println("Execution Time: "+timeDiff+" ms");
		System.exit(0);
	}
}
