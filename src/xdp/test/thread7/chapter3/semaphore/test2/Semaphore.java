package xdp.test.thread7.chapter3.semaphore.test2;

import java.util.concurrent.TimeUnit;

public class Semaphore {
	// 是否获取到这个信号标识
	private boolean signal = false;
	
	public synchronized void take(){
		this.signal = true;
		System.out.println("唤醒等待Semaphore锁线程");
		try {
			TimeUnit.SECONDS.sleep(1l);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.notify();
	}
	
	public synchronized void release() throws InterruptedException{
		while(!this.signal){
			System.out.println("等待Semaphore锁...");
			this.wait();
			System.out.println("被掐线程唤醒，获取到Semaphore锁");
		}
		try {
			TimeUnit.SECONDS.sleep(1l);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.signal = false;
	}

}
