
package demo2;//implements a runnable and pass it to the thread constructor

class Runner implements Runnable{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		//simulate some useful work.
				for(int i =0; i<10;i++){
					System.out.println("Hello" + i);
					
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
	}
	
}

public class App {
	public static 	void main(String[] args){
		Thread t1 = new Thread(new Runner());
		Thread t2 = new Thread(new Runner());
		
		t1.start();
		t2.start();
		
	}

}