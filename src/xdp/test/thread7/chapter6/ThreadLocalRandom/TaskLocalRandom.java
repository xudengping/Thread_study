package xdp.test.thread7.chapter6.ThreadLocalRandom;

import java.util.concurrent.ThreadLocalRandom;

public class TaskLocalRandom implements Runnable{
	
	public TaskLocalRandom(){
		// 给当前线程初始化随机数生成器,如果没有关联的ThreadLocalRandom对象
		ThreadLocalRandom current = ThreadLocalRandom.current();
	}

	@Override
	public void run() {
		String name=Thread.currentThread().getName();
		for (int i=0; i<10; i++){
			// 当前线程已经绑定过ThreadLocalRandom对象，这里不再创建
			System.out.printf("%s: %d\n",name,ThreadLocalRandom.current().nextInt(10));
		}
	}

}
