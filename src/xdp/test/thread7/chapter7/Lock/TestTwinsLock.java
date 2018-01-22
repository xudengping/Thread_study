package xdp.test.thread7.chapter7.Lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

public class TestTwinsLock {

	public static void main(String[] args) {
		final Lock lock = new TwinsLock();
		
		class Worker extends Thread{
			private String name;
			
			Worker(String name){
				this.name = name;
			}
			
			@Override
			public void run() {
//				while(true){
					lock.lock();
					try{
						System.out.println(Thread.currentThread().getName()+":"+name);
						
						try {
							TimeUnit.SECONDS.sleep(2);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}finally{
						lock.unlock();
					}
//				}
			}
		}
	
		Worker[] warr = new Worker[10];
		for(int i=0;i<10;i++){
			Worker worker = new Worker(String.valueOf(i));
			worker.start();
			warr[i] = worker;
		}
		
		for(int i=0;i<10;i++){
			try {
				warr[i].join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	
}
