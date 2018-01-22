package xdp.test.thread7.chapter6.SynchronousQueue;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

public class Test {

	public static void main(String[] args) {
		final SynchronousQueue<String> queue = new SynchronousQueue<String>(true);
		
		Thread[] producer = new Thread[3];
		for(int i=0;i<3;i++){
			final int j = i;
			Thread t = new Thread(new Runnable() {
				
				@Override
				public void run() {
					try {
						queue.put(String.valueOf(j));
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}					
				}
			});
			producer[i] = t;
		}
		
		Thread[] consumer = new Thread[3];
		for(int i=0;i<3;i++){
			Thread t = new Thread(new Runnable() {
				
				@Override
				public void run() {
					try {
						System.out.println(Thread.currentThread().getName()+":take");
						String take = queue.peek();
						System.out.println(Thread.currentThread().getName()+":take:"+take);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}			
				}
			});
			consumer[i] = t;
		}
		
		for(int i=0;i<3;i++){
			producer[i].start();
		}
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i=0;i<3;i++){
			consumer[i].start();
		}
		

	}

}
