package xdp.test.thread7.chapter4.ThreadPoolExecutor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SingleThreadExecutorTest {

	public static void main(String[] args) {
		// ���������̵߳��̳߳�
		ExecutorService service = Executors.newSingleThreadExecutor();
		
		for(int i=0;i<10;i++){
			service.execute(new Runnable() {
				
				@Override
				public void run() {
					System.out.println(Thread.currentThread().getName()+"��running������");
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName()+"��end");
				}
			});
		}

	}

}
