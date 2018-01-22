package xdp.test.thread7.chapter4.ThreadPoolExecutor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class TestTask {

	public static void main(String[] args) {
		ExecutorService executor = Executors.newCachedThreadPool();
		
		List<Task> taskList=new ArrayList<Task>();
		for (int i=0; i<3; i++){
		Task task=new Task(""+i);
		taskList.add(task);
		}
		
		List<Future<Result>> resultList =  null;
		
		try {
			resultList = executor.invokeAll(taskList, 10, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		executor.shutdown();
		
		System.out.println("Main: Printing the results");
		for (Future<Result> future : resultList) {
			try {
				Result result = future.get();
				System.out.println(result.getName()+": "+result.getValue());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

	}

}
