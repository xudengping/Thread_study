package xdp.test.thread7.chapter5.forkJoin.RecursiveTask.async;

import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

public class TestFileProcessor {

	public static void main(String[] args) {
		ForkJoinPool pool = new ForkJoinPool();
		
		FolderProcessor system=new FolderProcessor("C:\\Windows","log");
		FolderProcessor apps=new FolderProcessor("C:\\Program Files","log");
		FolderProcessor documents=new FolderProcessor("C:\\Documents And Settings","log");
		
		
		pool.execute(system);
		pool.execute(apps);
		pool.execute(documents);
		
		do{
			System.out.printf("******************************************\n");
			System.out.printf("Main: Parallelism: %d\n",pool.
			getParallelism());
			System.out.printf("Main: Active Threads: %d\n",pool.
			getActiveThreadCount());
			System.out.printf("Main: Task Count: %d\n",pool.
			getQueuedTaskCount());
			System.out.printf("Main: Steal Count: %d\n",pool.
			getStealCount());
			System.out.printf("******************************************\n");

			try {
				TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
				e.printStackTrace();
				}
	
		}while(!system.isDone() || !apps.isDone() || !documents.isDone());

		pool.shutdown();
		
		/*try{
			// 等待程序结束
			System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
			pool.awaitTermination(1, TimeUnit.DAYS);
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		System.out.println("ssssssssssssssssssssssssssssss");
		
		// 任务十分异常结束
		if(system.isCompletedAbnormally()){
			System.out.printf("Main: An exception has ocurred\n");
			System.out.printf("Main: system %s\n",system.getException());
		}
		if(apps.isCompletedAbnormally())
		{
			System.out.printf("Main: An exception has ocurred\n");
			System.out.printf("Main: apps %s\n",apps.getException());
		}
		if(documents.isCompletedAbnormally()){
			System.out.printf("Main: An exception has ocurred\n");
			System.out.printf("Main: documents %s\n",documents.getException());
		}*/
		
		List<String> results;
		results=system.join();
		System.out.printf("System: %d files found.\n",results.size());
		results=apps.join();
		System.out.printf("Apps: %d files found.\n",results.size());
		results=documents.join();
		System.out.printf("Documents: %d files found.\n",results.
		size());

	}

}
