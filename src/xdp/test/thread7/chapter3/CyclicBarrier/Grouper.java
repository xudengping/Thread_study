package xdp.test.thread7.chapter3.CyclicBarrier;

// 统计总次数
public class Grouper implements Runnable{
	
	private Results results;
	
	public Grouper(Results results){
		this.results = results;
	}

	@Override
	public void run() {
		int finalResult = 0;
		System.out.println("Grouper:技术总结果...\n");
		int[] data = results.getData();
		for(int number:data){
			finalResult += number;
		}
		
		System.out.printf("Grouper:总结果为:%d",finalResult);
		
	}

}
