package MTP07_ProducerConsumer;


import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class App {
	
	private static BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(10);
	// max capacity = 10, 10 Integers. ; FIFO. add an remove.
	

	public static void main(String[] args) throws InterruptedException {
		
		
		Thread t1 = new Thread(new Runnable(){
			public void run() {
				try {
					producer();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		Thread t2 = new Thread(new Runnable(){
			public void run() {
				try {
					consumer();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		t1.start();
		t2.start();
		
		t1.join();
		t2.join();
		

	}

	
	private static void producer() throws InterruptedException{
		Random random = new Random();
		
		while(true){
			queue.put(random.nextInt(100)); 
			// inherently add() and check_and_wait()(some one takes the food, plate is not full).
						
		}
	}
	
	private static void consumer() throws InterruptedException{
		Random random = new Random(); //used to decide whether to consume now or later.
		
		while(true){
			Thread.sleep(100);
			
			if(random.nextInt(10) == 0){
				Integer value = queue.take();
				// inherently remove() and check_and_wait()(until queue is not empty).
				
				System.out.println("Take value: " + value + "; Queue size is: " + queue.size());
			}	
		}
	}

}
