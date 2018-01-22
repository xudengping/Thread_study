package xdp.test.thread7.chapter6.DelayedQueue;

import java.util.Date;
import java.util.concurrent.DelayQueue;

public class Task implements Runnable{
	private int id;
	
	private  DelayQueue<Event> queue;
	
	public Task(int id,DelayQueue<Event> queue){
		this.id = id;
		this.queue = queue;
	}

	@Override
	public void run() {
		Date now = new Date();
		Date delay = new Date();
		delay.setTime(now.getTime()+(id*10000));
		System.out.printf("Thread %s: %s\n",id,delay);
		
		for(int i=0;i<100;i++){
			Event e = new Event(delay);
			queue.add(e);
		}

		
	}

}
