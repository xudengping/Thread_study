package xdp.test.thread7.chapter3.semaphore.test2;

public class TestBoundedSemaphore {
	
	private static int num = 0;// ¹²Ïí×ÊÔ´
	
	public static void main(String[] args) throws InterruptedException {
		for(int i=0;i<10;i++){
			invoke();
		}
	}

	private static void invoke() throws InterruptedException {
		final BoundedSemaphore sp = new BoundedSemaphore(1);
		
		Thread[] tarr = new Thread[10];
		for(int i=0;i<10;i++){
			tarr[i] = new Thread(new Runnable() {
				
				@Override
				public void run() {
					try {
						sp.take();
						num++;
					} catch (InterruptedException e) {
						e.printStackTrace();
					}finally {
						try {
							sp.release();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					
				}
			});
		}
		
		for(int i=0;i<10;i++){
			tarr[i].start();
		}
		
		for(int i=0;i<10;i++){
			tarr[i].join();
		}
		
		System.out.printf("num=%d.\n",num);

	}

}
