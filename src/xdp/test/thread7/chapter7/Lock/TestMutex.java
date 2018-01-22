package xdp.test.thread7.chapter7.Lock;

public class TestMutex {

	public static void main(String[] args) throws InterruptedException {
		int num = 1000;
		
		final Mutex lock = new Mutex();
		
		final TestMutex.Count countObj = new TestMutex.Count();
		
		Thread[] arr = new Thread[num];
		
		for(int i=0;i<num;i++){
			Thread t = new Thread(new Runnable() {
				
				@Override
				public void run() {
					lock.lock();
					try{
						System.out.println("============================");
						System.out.println("存在队列线程："+lock.hasQueuedThreads());
						System.out.println("增加前值："+countObj.getCount());
						countObj.inc();
						System.out.println("增加后值："+countObj.getCount());
						System.out.println("============================");
					}catch(Exception e){
						e.printStackTrace();
					}finally{
						lock.unlock();
					}
					
				}
			});
			arr[i] = t;
		}
		
		for(int i=0;i<num;i++){
			arr[i].start();
		}
		for(int i=0;i<num;i++){
			arr[i].join();
		}
        System.out.println(countObj.getCount());
	}
	
	public static  class Count{
		private int count;
		
		public int getCount(){
			return count;
		}
		
		public int inc(){
			return ++count;
		}
	}

}
