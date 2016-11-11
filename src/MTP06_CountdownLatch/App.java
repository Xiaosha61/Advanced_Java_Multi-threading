package MTP06_CountdownLatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
// using countdown class to avoid synchronization problem

class Processor implements Runnable{
	private CountDownLatch latch;
	
	public Processor(CountDownLatch latch){
		this.latch = latch;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("Started.");
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		latch.countDown();// when the thread finishes I will count it down.倒计时
	}
}

public class App {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CountDownLatch latch = new 	CountDownLatch(3);
		
		ExecutorService executor = Executors.newFixedThreadPool(3);
		
		for(int i=0;i<3;i++){
			executor.submit(new Processor(latch)); //each thread will get a processor(task)
		}
		
		try {
			latch.await();
			// it waits until countdown latch is equal to 0. 倒计时到0.
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Completed.");
		
	}
	

}
