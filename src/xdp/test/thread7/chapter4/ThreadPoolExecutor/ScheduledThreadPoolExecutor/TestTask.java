package xdp.test.thread7.chapter4.ThreadPoolExecutor.ScheduledThreadPoolExecutor;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TestTask {

	public static void main(String[] args) {
		ScheduledThreadPoolExecutor executor = (ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(1);
		System.out.printf("Main: Starting at: %s\n",new Date());
		for (int i=0; i<5; i++) {
			Task task=new Task("Task "+i);
			executor.schedule(task,1 , TimeUnit.SECONDS);
		}
		executor.setExecuteExistingDelayedTasksAfterShutdownPolicy(false);
		executor.shutdown();// 默认行为是等待任务执行完成再关闭线程池
//		executor.shutdownNow();// 直接关闭线程池
		
		// 等待所有任务执行完
		try {
			executor.awaitTermination(1, TimeUnit.DAYS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.printf("Main: Ends at: %s\n",new Date());


	}

}
