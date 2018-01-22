package xdp.test.thread7.chapter4.ThreadPoolExecutor.ScheduledThreadPoolExecutor;

import java.util.Date;
import java.util.concurrent.Callable;

public class Task implements Callable<String>{
	private String name;
	
	public Task(String name) {
		this.name=name;
	}

	@Override
	public String call() throws Exception {
		System.out.printf("%s: Starting at : %s\n",name,new Date());
		return "Hello, world";
	}

}
