
package demo1;//start a thread.

class Runner extends Thread{

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
	
	public static void main(String[] args){
		Runner runner1 = new Runner();
		runner1.start();
		//NOTE: you can't call "runner1.run()"; 
		//because it will certainly run your code, 
		//but it will be inside the main Thread instead of the special thread.
		
		Runner runner2 = new Runner();
		runner2.start();
	}
}

