package MTP09_ExampleWaitNotify;

import java.util.LinkedList;
import java.util.Random;


public class Processor {
	
	private LinkedList<Integer> list = new LinkedList<Integer>();
	private final int LIMIT = 10;
	private Object lock = new Object();
	
	public void produce() throws InterruptedException{
		
		int value = 0;
		
		while(true){
			synchronized(lock){
				
				while(list.size() == LIMIT){
					wait();// if list is full , I'll wait.
				}
				list.add(value++);  // the elements added increment from 0.
				lock.notify(); // I added sth just now ,there must be sth for consumer to take, so consumer should be waken up.
			}
		}
		
	}
	
	public void consume() throws InterruptedException{
		
		Random random = new Random();
		
		while(true){
			
			synchronized(lock){
				
				while(list.size() == 0){
					lock.wait(); // when there is no element in list , I'll wait.
				}
				
				System.out.print("List size is: "+ list.size());
				int value = list.removeFirst(); //FIFO
				System.out.println("; value is:" + value);
				lock.notify(); // when I take sth out , the list must be not full, producer should be reminded by me to wake up.
				
			}
			
			Thread.sleep(random.nextInt(1000)); // hopefully 500ms in average.
			
		}
		
	}

}
