package xdp.test.thread7.chapter5.forkJoin.RecursiveTask.cancelTask;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

public class TestSearchNumberTask {

	public static void main(String[] args) throws InterruptedException {
		ArrayGenerator generator = new ArrayGenerator();
		int array[] = generator.generateArray(1000);
		TaskManager manager = new TaskManager();
		SearchNumberTask task = new SearchNumberTask(array, 0, 1000, 5, manager);

		ForkJoinPool pool = new ForkJoinPool();
		pool.execute(task);
		
		 while (!task.isDone()) {
			             showLog(pool);
			             TimeUnit.SECONDS.sleep(1);
			         }

		pool.shutdown();

		try {
			pool.awaitTermination(1, TimeUnit.DAYS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		showLog(pool);

		System.out.printf("Main: The program has finished\n");
	}

	private static void showLog(ForkJoinPool pool) {
		System.out.printf("**********************\n");
		System.out.printf("Main: Fork/Join Pool log\n");
		System.out.printf("Main: Fork/Join Pool: Parallelism:%d\n",pool.getParallelism());// 计算机处理器数量
		System.out.printf("Main: Fork/Join Pool: Pool Size:%d\n",pool.getPoolSize());
		System.out.printf("Main: Fork/Join Pool: Active Thread Count:%d\n",pool.getActiveThreadCount());
		System.out.printf("Main: Fork/Join Pool: Running Thread Count:%d\n",pool.getRunningThreadCount());
		System.out.printf("Main: Fork/Join Pool: Queued Submission:%d\n",pool.getQueuedSubmissionCount());
		System.out.printf("Main: Fork/Join Pool: Queued Tasks:%d\n",pool.getQueuedTaskCount());
		System.out.printf("Main: Fork/Join Pool: Queued Submissions:%s\n",pool.hasQueuedSubmissions());
		System.out.printf("Main: Fork/Join Pool: Steal Count:%d\n",pool.getStealCount());
		System.out.printf("Main: Fork/Join Pool: Terminated :%s\n",pool.isTerminated());
		System.out.printf("**********************\n");
	}

}
