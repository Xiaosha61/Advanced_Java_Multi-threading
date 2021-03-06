package MTP14_InterruptingThreads;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class App {
	public static void main(String[] args) throws InterruptedException{
		System.out.println("Starting...");
		
		ExecutorService exec = Executors.newCachedThreadPool();
		
		Future<?> fu = exec.submit(new Callable<Void>(){

			@Override
			public Void call() throws Exception {
				
				Random ran = new Random();
				
				for(int i = 0; i<1E8; i++){
					
					if(Thread.currentThread().isInterrupted()){
						System.out.println("Interrupted in the " + i + "th loop.");
						break;
					}
					Math.sin(ran.nextDouble());
				}
				
				return null;
			}
			
		});
		
		exec.shutdown();
		
		Thread.sleep(500);
		
		exec.shutdownNow();
				
		exec.awaitTermination(1, TimeUnit.DAYS);// wait for the thread to finish, wait at most 1 day.
		
		System.out.println("Finished.");
		
	}

}
