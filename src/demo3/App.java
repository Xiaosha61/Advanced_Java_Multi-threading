
package demo3;
//A new way of running threads
// a technique using an enormous comes in handys 
public class App {
	public static void main(String[] args){
		Thread t1 = new Thread(new Runnable(){

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
		});
		
		t1.start();
	}
}

