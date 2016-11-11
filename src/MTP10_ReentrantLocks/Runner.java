package MTP10_ReentrantLocks;

import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Runner {
	
	private int count = 0;
	private Lock lock = new ReentrantLock();
	private Condition cond = lock.newCondition();// get the lock's condition.
	// you can only call await() and signal().
	
	private void increment(){
		for(int i =0; i<10000; i++){
			count++;
		}
	}
	
	public void firstThread() throws InterruptedException{
		lock.lock();

		System.out.println("t1: Waiting...");
		cond.await();
		//after this another thread can get this lock.
		//in this case after 1s t2 wakes up he will get the lock.
		
		System.out.println("t1: woke up!");

		
		try{
			increment();	
		}finally{
			lock.unlock();
		}
		
	}
	
	public void secondThread() throws InterruptedException{
		
		Thread.sleep(1000);
		//in this case after 1s t2 wakes up he will get the lock because t1 enters await();

		lock.lock();
		
		System.out.println("t2: Press the return key!");
		new Scanner(System.in).nextLine();
		System.out.println("t2: Got return key!");
		
		cond.signal(); // wake up all awaiting threads --> who has called await() .

		try{
			increment();	
		}finally{
			lock.unlock();// note : must needed to release the lock. signal() can wake others up but doesn't means giving up the lock
		}

	}


	public void finished(){
		System.out.println("Count is : "+ count);
	}

}
