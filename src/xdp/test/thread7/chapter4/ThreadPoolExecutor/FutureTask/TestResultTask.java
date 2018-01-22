package xdp.test.thread7.chapter4.ThreadPoolExecutor.FutureTask;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TestResultTask {

	public static void main(String[] args) {
		ExecutorService executor = (ExecutorService) Executors.newCachedThreadPool();
		ResultTask resultTasks[] = new ResultTask[5];
		for (int i = 0; i < 5; i++) {
			ExecutableTask executableTask = new ExecutableTask("Task " + i);
			resultTasks[i] = new ResultTask(executableTask);
			executor.submit(resultTasks[i]);
		}

		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		for (int i = 0; i < resultTasks.length; i++) {
			resultTasks[i].cancel(true);// true 任务正在执行也会被取消，取消之前执行done方法
		}

		for (int i = 0; i < resultTasks.length; i++) {
			try {
				if (!resultTasks[i].isCancelled()) {
					System.out.printf("%s\n", resultTasks[i].get());
				}
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}

		executor.shutdown();

	}

}
