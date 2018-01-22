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
		executor.shutdown();// Ĭ����Ϊ�ǵȴ�����ִ������ٹر��̳߳�
//		executor.shutdownNow();// ֱ�ӹر��̳߳�
		
		// �ȴ���������ִ����
		try {
			executor.awaitTermination(1, TimeUnit.DAYS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.printf("Main: Ends at: %s\n",new Date());


	}

}
