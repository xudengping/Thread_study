package xdp.test.thread7.chapter4.ThreadPoolExecutor.ScheduledThreadPoolExecutor;

import java.util.Date;
import java.util.concurrent.Callable;

public class Task2 implements Callable<String>{

	@Override
	public String call() throws Exception {
		while (true){
			System.out.printf("Task: Test\n");
			Thread.sleep(100);
			}
	}

}
