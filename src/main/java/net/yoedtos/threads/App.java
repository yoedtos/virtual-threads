package net.yoedtos.threads;

public class App {
	public static void main(String[] args) {
		System.out.println();
		System.out.println("\tExperience with Virtual Threads\n");
		
		var times = new int[] {100, 10_000, 100_000, 1_000_000};
		/*
		 * for use with logger debug mode 
		 * var times = new int[] {100};
		 *
		 */
		
		System.out.println("\tSleeper");
		for (int i = 0; i < times.length; i++) {
			runSleeper(times[i]);
		}
		
		System.out.println("\tLine Counter");
		for (int i = 0; i < times.length; i++) {
			runLineCounter(times[i]);
		}
		System.out.println("\tConcluded\n");
	}
	
	private static void runSleeper(int times) {
		System.out.println("\tStart with " + times); 
		if(times <= 100_000) {
			System.out.println("\tRegular Thread: " + Sleeper.runThread(times) + " ms");
		}
		System.out.println("\tVirtual Thread: " + Sleeper.runVThread(times) + " ms");
		
		System.out.println();
	}
	
	private static void runLineCounter(int times) {
		System.out.println("\tStart with " + times);
		if(times <= 100_000) {
			System.out.println("\tRegular Thread: " + LineCounter.runThread(times) + " ms");
		}
		System.out.println("\tVirtual Thread: " + LineCounter.runVThread(times) + " ms");
		
		System.out.println();
	}
}
