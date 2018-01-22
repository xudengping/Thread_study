package xdp.test.thread7.chapter3.Phaser;

import java.util.concurrent.Phaser;

public class TestFileSearchPhaser {

	public static void main(String[] args) {
		// ָ������׶�ͬ�����̸߳���
		Phaser phaser = new Phaser(3);
		FileSearch system = new FileSearch("c:\\Windows", "log", phaser);
		FileSearch apps = new FileSearch("c:\\Program Files", "log", phaser);
		FileSearch documents = new FileSearch("d:\\project", "log", phaser);
		
		Thread systemThread = new Thread(system);
		systemThread.start();
		
		Thread appsThread = new Thread(apps);
		appsThread.start();
		
		Thread documentsThread = new Thread(documents);
		documentsThread.start();
		
		try{
			// �ȴ������߳̽�����Ȼ��������߳�
			systemThread.join();
			appsThread.join();
			documentsThread.join();
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		
		System.out.println("Terminated: "+phaser.isTerminated());
		

	}

}
