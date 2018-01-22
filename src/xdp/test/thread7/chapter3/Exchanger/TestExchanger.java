package xdp.test.thread7.chapter3.Exchanger;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class TestExchanger {

	private static final Exchanger<String> exchanger = new Exchanger<String>();

	private static ExecutorService executor = Executors.newFixedThreadPool(2);

	public static void main(String[] args) {
		executor.execute(new Runnable() {

			@Override
			public void run() {
				String A = "银行流水A";
				try {
					TimeUnit.SECONDS.sleep(5);
					exchanger.exchange(A);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});

		executor.execute(new Runnable() {

			@Override
			public void run() {
				String B = "银行流水A";
				try {
//					String A = exchanger.exchange(B);
					String A;
					A = exchanger.exchange(B, 1, TimeUnit.SECONDS);
					System.out.println("A和B数据是否一致：" + A.equals(B) + ",A录入的是：" + A + ",B录入是：" + B);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		executor.shutdown();
	}

}
