package xdp.test.thread7.chapter6.DelayedQueue;

import java.util.Date;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.TimeUnit;

public class Main {

	public static void main(String[] args) {
		DelayQueue<Event> queue = new DelayQueue<Event>();
		Thread threads[]=new Thread[5];

		
		for(int i=0;i<1;i++){
			Task task = new Task(i, queue);
			threads[i] = new Thread(task);
			threads[i].start();
		}
		
		for(int i=0;i<1;i++){
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("size="+queue.size());
		
		do {
			int counter = 0;
			Event event;
			do{
				event = queue.poll();
				if (event!=null) {
					counter++;
					System.out.println(event.getTime());
				}
			}while(event!=null);
			System.out.printf("At %s you have read %d events\n",new Date(),counter);
			try {
				TimeUnit.MILLISECONDS.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}while(queue.size()>0);


	}

}
