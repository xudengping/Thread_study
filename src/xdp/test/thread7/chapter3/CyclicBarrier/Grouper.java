package xdp.test.thread7.chapter3.CyclicBarrier;

// ͳ���ܴ���
public class Grouper implements Runnable{
	
	private Results results;
	
	public Grouper(Results results){
		this.results = results;
	}

	@Override
	public void run() {
		int finalResult = 0;
		System.out.println("Grouper:�����ܽ��...\n");
		int[] data = results.getData();
		for(int number:data){
			finalResult += number;
		}
		
		System.out.printf("Grouper:�ܽ��Ϊ:%d",finalResult);
		
	}

}
