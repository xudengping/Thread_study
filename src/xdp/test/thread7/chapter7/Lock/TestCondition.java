package xdp.test.thread7.chapter7.Lock;

import java.util.concurrent.TimeUnit;

public class TestCondition {

	public static void main(String[] args) throws InterruptedException {
		final ConditionUseCase condition = new ConditionUseCase();
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					System.out.println(Thread.currentThread().getName()+" 开始等待");
					condition.await();
					System.out.println(Thread.currentThread().getName()+" 等待被唤醒");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
        Thread t2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					System.out.println(Thread.currentThread().getName()+" 开始等待");
					condition.await();
					System.out.println(Thread.currentThread().getName()+" 等待被唤醒");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
        
        t2.start();
        TimeUnit.SECONDS.sleep(1);
        t1.start();
        TimeUnit.SECONDS.sleep(1);
        
        Thread t3 = new Thread(new Runnable() {
			
			@Override
			public void run() {
					condition.signal();
			}
		});
        
 Thread t4 = new Thread(new Runnable() {
			
			@Override
			public void run() {
					condition.signal();
			}
		});
        
        t3.start();
//        t4.start();
	}

}
