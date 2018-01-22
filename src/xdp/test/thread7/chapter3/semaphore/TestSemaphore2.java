package xdp.test.thread7.chapter3.semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

// 使用semaphore控制流量
public class TestSemaphore2 {
	private static final int THREAD_COUNT = 30;
	
	// 30个线程获取连接
	private static ExecutorService	pool = Executors.newFixedThreadPool(THREAD_COUNT);


	// 控制同时获取连接的只能存在最多10个线程,控制并发量
	private static Semaphore semaphore = new Semaphore(10);

	public static void main(String[] args) {
		for(int i=0;i<THREAD_COUNT;i++){
			pool.execute(new Runnable() {
				
				@Override
				public void run() {
					try {
						System.out.printf("%s:正在等待获取数据库操作连接的线程数%d.\n",Thread.currentThread().getName(),semaphore.availablePermits());
						semaphore.acquire();
						System.out.printf("%s:正在做插入数据库操作\n",Thread.currentThread().getName());
						TimeUnit.SECONDS.sleep(5);
						System.out.printf("%s:数据库操作完成\n",Thread.currentThread().getName());
						semaphore.release();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			});
		}
		pool.shutdown();
	}

}
