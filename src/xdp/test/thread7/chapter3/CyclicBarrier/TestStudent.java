package xdp.test.thread7.chapter3.CyclicBarrier;

import java.util.concurrent.CyclicBarrier;

public class TestStudent {

	public static void main(String[] args) {
		CyclicBarrier barrier = new CyclicBarrier(3, new Runnable() {
			
			@Override
			public void run() {
//				System.out.println("sss");
				
			}
		});
		
		for(int i=0;i<3;i++){
			Thread ti = new Thread(new Student(barrier),"Ñ§Éú"+i);
			ti.start();
		}
		
		System.out.println("main£ºend\n");

	}

}
