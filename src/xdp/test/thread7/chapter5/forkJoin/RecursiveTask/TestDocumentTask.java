package xdp.test.thread7.chapter5.forkJoin.RecursiveTask;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

public class TestDocumentTask {

	public static void main(String[] args) {
		Document doc = new  Document();
		String[][] document = doc.generateDocument(100, 1000, "the");
		
		DocumentTask docTask = new DocumentTask(document, 0, 100, "the");
		
		ForkJoinPool pool = new ForkJoinPool(5);
//		ForkJoinPool pool = new ForkJoinPool(int parallelism);// 使用处理器数量初始化
		pool.execute(docTask);
		
		do{
			System.out.printf("******************************************\n");
			System.out.printf("Main:Parallelism:%d\n",pool.getParallelism());// 处理器数量
			System.out.printf("Main:Active Threads:%d\n",pool.getActiveThreadCount());
			System.out.printf("Main:Steal Count:%d\n",pool.getStealCount());
			System.out.printf("******************************************\n");
			
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}while(!docTask.isDone());

		pool.shutdown();
		
		try {
			pool.awaitTermination(1, TimeUnit.DAYS);
			System.out.printf("Main: The word appears %d in the document",docTask.get());
			} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
			}


	}

}
