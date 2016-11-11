package MTP05_ThreadPools;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Processor implements Runnable{
	
	private int id;
	
	public Processor(int id){
		this.id = id;
	}
	
	public void run(){
		System.out.println("Starting: "+id);
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Completed: "+id);

	}
}

public class App {

	public static void main(String[] args) {
		ExecutorService executor = Executors.newFixedThreadPool(2);
		// thread pool is like having a number-of-workers factory, in this case 2 workers.
		// This can realize that a worker can start a new task when finishing the old one.
		
		// submit tasks to executor
		for(int i=0 ;i<5;i++){
			executor.submit(new Processor(i));// give the executor 5 tasks.
		}
		
		executor.shutdown();// wait for all the threads complete , then terminate .
		
		
		try {
			executor.awaitTermination(5, TimeUnit.SECONDS); // wait for 1 day. timeout.
			//executor.awaitTermination(timeout, unit)
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("All tasks submitted.");
	}


}
