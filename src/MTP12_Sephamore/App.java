package MTP12_Sephamore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class App {

	public static void main(String[] args) throws InterruptedException {
		
		Connection.getInstance().connect();
		
		//I have a cachedThreadPool , try to reuse thread?
		ExecutorService executor = Executors.newCachedThreadPool();
		// every time assign a new job to a new thread. 
		// we use 200 threads once.
		for(int i =0; i<200; i++){
			executor.submit(new Runnable(){
				public void run(){
					Connection.getInstance().connect();
				}
			});
		}
		
		executor.shutdown();
		
		executor.awaitTermination(1, TimeUnit.DAYS);
		
	}

}
