package xdp.test.thread7.chapter3.CyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class TestCyclicBarrier2 {
	
	// 屏障拦截的线程数为2
	static CyclicBarrier barrier = new CyclicBarrier(2);

	public static void main(String[] args) {
		Thread t = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					barrier.await();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (BrokenBarrierException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(1);
				
			}
		});
		t.start();
		t.interrupt();
		
		try {
			barrier.await();
		} catch (InterruptedException | BrokenBarrierException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(barrier.isBroken());
		}
		System.out.println(2);

	}

}
