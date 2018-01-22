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
				String A = "������ˮA";
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
				String B = "������ˮA";
				try {
//					String A = exchanger.exchange(B);
					String A;
					A = exchanger.exchange(B, 1, TimeUnit.SECONDS);
					System.out.println("A��B�����Ƿ�һ�£�" + A.equals(B) + ",A¼����ǣ�" + A + ",B¼���ǣ�" + B);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		executor.shutdown();
	}

}
