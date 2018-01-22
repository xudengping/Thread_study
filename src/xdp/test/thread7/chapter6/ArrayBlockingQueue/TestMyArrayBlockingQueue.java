package xdp.test.thread7.chapter6.ArrayBlockingQueue;

public class TestMyArrayBlockingQueue {

	public static void main(String[] args) {
		final MyArrayBlockingQueue<String> arrayQueue = new MyArrayBlockingQueue<String>(10);
		
		Thread p = new Thread(new Runnable() {
			
			@Override
			public void run() {
				int i =0;
				while(true){
					try {
						++i;
						arrayQueue.put(i+"ÏûÏ¢"+i);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
			}
		});
		
        Thread c = new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(true){
					try {
						System.out.println(arrayQueue.take());
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
			}
		});
        
        p.start();
    	try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        c.start();

	}

}
