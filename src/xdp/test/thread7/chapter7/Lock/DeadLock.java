package xdp.test.thread7.chapter7.Lock;

import java.util.concurrent.TimeUnit;

public class DeadLock {
	
	private Object obj = new Object();
	
	private int num = 0;
	
	public int getNum(){
		return num;
	}
	
	public synchronized void inc(){//this
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		synchronized(obj){// obj
			num++;
		}
	}
	
	public void del(){
		synchronized(obj){//obj
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			dell();// this
		}
	}
	
	public synchronized void dell(){//this
		num--;
	}
	
	
	public synchronized void testInterrupt(){//this
		System.out.println("start watiing");
		try {
			obj.wait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("end watiing");
		
	}
	
	/*public static void  main(String[] args){
		final DeadLock lock = new DeadLock();
		
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				lock.inc();
			}
		});
		
        Thread t2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				lock.del();
				
			}
		});
        
        t1.start();
        t2.start();
        
        t1.interrupt();
        t2.interrupt();
        
        try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println(lock.getNum());
	}*/
	
	public static void  main(String[] args) throws InterruptedException{
		final DeadLock lock = new DeadLock();
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				lock.testInterrupt();
			}
		});
		
        
        t1.start();
        t1.interrupt();
        t1.join();
        
       
        System.out.println("main end");
        
	}
	

}
