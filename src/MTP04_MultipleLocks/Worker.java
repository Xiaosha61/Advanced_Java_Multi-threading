package MTP04_MultipleLocks;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;


public class Worker {
	
	//Worker's instance variables:
	//有两个list放整数。。。
	private Random random = new Random();
	private List<Integer> list1 = new ArrayList<Integer>();
	private List<Integer> list2 = new ArrayList<Integer>();
	
	//create lock objects for different methods
	private Object lockForStageOne = new Object();
	private Object lockForStageTwo = new Object();
	
	//do sth on list1
	//每次add给list1一个随即数
	public void stageOne(){
		
		synchronized(list1){
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			list1.add(random.nextInt(100));
			// 给定一个参数n，nextInt(n)将返回一个大于等于0小于n的随机搜索数，即：0 <= nextInt(n) < n。

		}
	}
	
	public void stageTwo(){
		
		synchronized(list2){
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			list2.add(random.nextInt(100));

		}
	}
	
	public  void process(){
		for(int i =0; i<1000;i++){
			stageOne();
			stageTwo();
		}
	}
	
	public void main(){
		System.out.println("Starting...");
		//too see how long does the process(); take ..
		long start = System.currentTimeMillis();
		 
//		process();	//theoratically it should be a little bit more than 2000ms.
		//but now we let another thread instead of main thread to run it .
		Thread t1 = new Thread(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				process();
			}
		});
		
		Thread t2 = new Thread(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				process();
			}
		});
		
		t1.start();
		t2.start();
		
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		long end = System.currentTimeMillis();

		System.out.println("Time take: " + (end - start));
		System.out.println("List1: "+list1.size() + "; List2: " + list2.size());
	}
}
