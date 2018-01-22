package xdp.test.thread7.chapter6.LinkedBlockingDeque;

import java.util.Date;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		LinkedBlockingDeque<String> linkedBlockingDeque = new LinkedBlockingDeque<>(3);
		
		Client client = new Client(linkedBlockingDeque);
		Thread t = new Thread(client);
		t.start();
		
		for(int i=0;i<3;i++){
			for (int j=0; j<5; j++) {
				try {
					String request = linkedBlockingDeque.take();
					System.out.printf("Main: Request: %s at %s. Size:%d\n",request,new Date(),linkedBlockingDeque.size());
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				TimeUnit.MILLISECONDS.sleep(300);
			}
		}

	}

}
