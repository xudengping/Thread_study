package xdp.test.thread7.chapter4.ThreadPoolExecutor.ScheduledThreadPoolExecutor;

import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TestTask2 {

	public static void main(String[] args) {
		ThreadPoolExecutor executor=(ThreadPoolExecutor)Executors.newCachedThreadPool();
		Task2 task=new Task2();
		System.out.printf("Main: Executing the Task\n");

		Future<String> result = executor.submit(task);

		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
		e.printStackTrace();
		}
		System.out.printf("Main: Canceling the Task\n");
		result.cancel(true);// mayInterruptIfRunning = true
		System.out.printf("Main: Canceled: %s\n",result.isCancelled());
		System.out.printf("Main: Done: %s\n",result.isDone());
		
		executor.shutdown();
		
		try {
			result.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.printf("Main: The executor has finished\n");



	}

}
