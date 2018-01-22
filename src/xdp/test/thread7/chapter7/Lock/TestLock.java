package xdp.test.thread7.chapter7.Lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestLock {

	public static void main(String[] args) throws InterruptedException {
		final Lock lock = new ReentrantLock();
		lock.lock();
		
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try{
//					lock.lock();
					TimeUnit.SECONDS.sleep(10);
					lock.lockInterruptibly();
				}catch(InterruptedException e){
					e.printStackTrace();
				}
				
				System.out.println(Thread.currentThread().getName() + " interrupted.");
				
			}
		},"child thread -1");
		t1.start();
		 Thread.sleep(1000);
		 t1.interrupt();
		 Thread.sleep(1000000);
	}

}
