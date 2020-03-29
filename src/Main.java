import java.util.Scanner;

 
public class Main {

	public static void main(String[] args) {

		ThreadGroup tg1 = new ThreadGroup("ThreadGroup");
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
	        
	        System.out.println("Solution to "+ n +" queens using hill climbing search:");
	        
	        HillClimbingSearch hcs = new HillClimbingSearch(n);
	        HillClimbingSearch hcs2 = new HillClimbingSearch(n);
	        HillClimbingSearch hcs3 = new HillClimbingSearch(n);


			Thread t1 = new Thread(tg1, hcs, "Name1");
			Thread t2 = new Thread(tg1, hcs2, "Name2");
			Thread t3 = new Thread(tg1, hcs3, "Name3");
			t1.start();
			t2.start();
			t3.start();

			//hcs.run();
			System.out.println(tg1.activeCount());

			while(tg1.activeCount() == 3){
				System.out.println(tg1.activeCount());
				System.out.println("Finding solution");
			}

			tg1.interrupt();

	        if (hcs.getFinalSolution() != null)
	        	hcs.printState(hcs.getFinalSolution());
			if (hcs2.getFinalSolution() != null)
				hcs2.printState(hcs2.getFinalSolution());
			if (hcs3.getFinalSolution() != null)
				hcs3.printState(hcs3.getFinalSolution());

	        //Printing the solution
	        long timestamp2 = System.currentTimeMillis();
			
			long timeDiff = timestamp2 - timestamp1;
			System.out.println("Execution Time: "+timeDiff+" ms");

	    }
}