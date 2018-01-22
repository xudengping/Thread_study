package xdp.test.thread7.chapter5.forkJoin.RecursiveAction;

import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

public class TestTask {

	public static void main(String[] args) {
		ProductListGenerator generator=new ProductListGenerator();
		List<Product> products=generator.generate(10000);
		Task task = new Task(products, 0, products.size(), 0.20);
		
		// 使用默认的无参构造器：创建一个线程数等于计算机处理器数的池
		ForkJoinPool pool = new ForkJoinPool();
		// 异步执行
		pool.execute(task);
		
		do {
			System.out.printf("Main: Thread Count: %d\n",pool.getActiveThreadCount());
			System.out.printf("Main: Thread Steal: %d\n",pool.getStealCount());
			System.out.printf("Main: Parallelism: %d\n",pool.getParallelism());

			try{
			TimeUnit.MILLISECONDS.sleep(5);
			} catch (InterruptedException e) {
			e.printStackTrace();
			}

		} while (!task.isDone());
		
		pool.shutdown();
		
		// 任务正常完成
		if(task.isCompletedNormally()){
			System.out.printf("Main: The process has completed normally.\n");

		}
		
		for (int i=0; i<products.size(); i++){
			Product product=products.get(i);
			if (product.getPrice()!=12) {
			System.out.printf("Product %s: %f\n",product.getName(),product.getPrice());
			}
			}
		
		System.out.println("Main: End of the program.\n");


	}

}
