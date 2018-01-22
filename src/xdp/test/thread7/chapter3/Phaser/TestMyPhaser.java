package xdp.test.thread7.chapter3.Phaser;

public class TestMyPhaser {

	public static void main(String[] args) {
		MyPhaser phaser = new MyPhaser();
		Student[] st = new Student[5];
		for(int i=0;i<st.length;i++){
			st[i] = new Student(phaser);
			phaser.register();
		}
		
		Thread[] t = new Thread[st.length];
		for(int i=0;i<st.length;i++){
			t[i] = new Thread(st[i],"Student"+i);
			t[i].start();
		}
		
		for(int i=0;i<st.length;i++){
			try {
				t[i].join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		System.out.printf("Main:phaser 已经完成:%s\n",phaser.isTerminated());
		

	}

}
