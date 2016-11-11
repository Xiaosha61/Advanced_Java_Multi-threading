package MTP11_Deadlock;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Runner {
	
	private Account acc1 = new Account();
	private Account acc2 = new Account();
	
	private Lock lock1 = new ReentrantLock(); // for acc1
	private Lock lock2 = new ReentrantLock(); // for acc2
	
	private void acquireLocks(Lock firstLock, Lock secondLock) throws InterruptedException{
		while(true){
			//Acquire locks
			
			boolean gotFirstLock = false;
			boolean gotSecondLock = false;
			
			try{
				gotFirstLock  = firstLock.tryLock();//it will return 'true' if got the lock
				gotSecondLock = secondLock.tryLock();// it returns false if not.
			}finally{
				//If successfully got 2 locks.
				if(gotFirstLock && gotSecondLock){
					return;
				}
				
				//if I didn't get both locks, I need to release the one I've got.
				if(gotFirstLock){
					firstLock.unlock();
				}
				
				if(gotSecondLock){
					secondLock.unlock();
				}
			}
			
			//if Locks not acquired, rest for a while and try again. :) 
			Thread.sleep(1);
		}
	}
	
	// acc1 gives to acc2
	public void firstThread() throws InterruptedException{
		
		Random random = new Random();
		
		for(int i=0; i<10000; i++){
			
			acquireLocks(lock1, lock2);
			
			try{
				Account.transfer(acc1, acc2, random.nextInt(100));

			}finally{
				lock1.unlock();
				lock2.unlock();
			}
		}
		
	}
	
	// acc2 gives to acc1
	public void secondThread() throws InterruptedException{

		Random random = new Random();
		
		for(int i=0; i<10000; i++){
			
			acquireLocks(lock2, lock1);
			
			try{
				Account.transfer(acc2, acc1, random.nextInt(100));

			}finally{
				lock2.unlock();
				lock1.unlock();
			}
		}
	}
	
	public void finished() {
		System.out.println("Account 1 balance: "+ acc1.getBalance());
		System.out.println("Account 2 balance: "+ acc2.getBalance());
		System.out.println("Total balance: "+ (acc1.getBalance() + acc2.getBalance()));
		
	}

}
