package xdp.test.thread7.chapter3.semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

// ʹ��semaphore��������
public class TestSemaphore2 {
	private static final int THREAD_COUNT = 30;
	
	// 30���̻߳�ȡ����
	private static ExecutorService	pool = Executors.newFixedThreadPool(THREAD_COUNT);


	// ����ͬʱ��ȡ���ӵ�ֻ�ܴ������10���߳�,���Ʋ�����
	private static Semaphore semaphore = new Semaphore(10);

	public static void main(String[] args) {
		for(int i=0;i<THREAD_COUNT;i++){
			pool.execute(new Runnable() {
				
				@Override
				public void run() {
					try {
						System.out.printf("%s:���ڵȴ���ȡ���ݿ�������ӵ��߳���%d.\n",Thread.currentThread().getName(),semaphore.availablePermits());
						semaphore.acquire();
						System.out.printf("%s:�������������ݿ����\n",Thread.currentThread().getName());
						TimeUnit.SECONDS.sleep(5);
						System.out.printf("%s:���ݿ�������\n",Thread.currentThread().getName());
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
