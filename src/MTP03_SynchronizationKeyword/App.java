package MTP03_SynchronizationKeyword;

public class App {
	
	private  volatile int count = 0;
	
	public synchronized void increment(){
		count++;
	}
	
	public static void main(String[] args){
		App app = new App();
		app.dowork();
	}
	
	public void dowork(){
		Thread t1 = new Thread( new Runnable(){
			public void run(){
				
				for(int i=0 ; i<10000; i++){
					increment();
 //					System.out.println("T1's count = " + count );
				}	
			}
		});
		
		Thread t2 = new Thread( new Runnable(){
			public void run(){
				
				for(int i=0 ; i<10000; i++){
					increment();
					//this is more than one step
					//first we get the value
					//second we increment it
					//third we store it back
				
 //					System.out.println("T2's count = " + count );

				}
			}
		});
		
		
		t1.start();
		t2.start();
		try {
			t1.join();
			t2.join();//to wait for the thread to finish and die.
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Count is : " + count );
	}

}
