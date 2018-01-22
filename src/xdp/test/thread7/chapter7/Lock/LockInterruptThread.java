package xdp.test.thread7.chapter7.Lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockInterruptThread implements Runnable{
	
	private static Lock lock = new ReentrantLock();

	@Override
	public void run() {
		try{
			lock.lock();
//			lock.lockInterruptibly();
			System.out.println(Thread.currentThread().getName() + " running"); 
			TimeUnit.SECONDS.sleep(10);
			System.out.println(Thread.currentThread().getName() + " finished"); 
		}catch(InterruptedException e){
			System.out.println(Thread.currentThread().getName() + " interrupted");  
		}
		
	}
	
	public static void main(String[] args){
		Thread t1 = new Thread(new LockInterruptThread());
//		Thread t2 = new Thread(new LockInterruptThread());
		
		t1.start();
//		t2.start();
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		t1.interrupt();
	}

}
