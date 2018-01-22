package xdp.test.thread7.chapter3.CountDownLatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

// ��Ƶ������
public class VideoConference implements Runnable{
	
	// ����һ������������ͬ�������߳�
	private final CountDownLatch controller;
	
	public VideoConference(int number){
		// ��ʼ����ȴ����ٸ��߳�ִ�����
		controller = new CountDownLatch(number);
	}
	
	public void arrive(String name){
		System.out.printf("%s has arrived.\n",name);
		// ��������1
		controller.countDown();
		// ��ӡ����ȴ����ٸ�������
		System.out.printf("VideoConference:Waiting for %d participants.\n",controller.getCount());
	}

	@Override
	public void run() {
		System.out.printf("VideoConference:Initializatio: %d participants.\n",controller.getCount());
		try {
			// �ȴ����еĲ����ߵ����ſ�ʼʱ����
			// ��controller������ֵΪ0��������
			controller.await(1,TimeUnit.SECONDS);
			// do something
			System.out.printf("VideoConference:All the participants have come.\n");
			System.out.printf("VideoConference:Let's start.\n");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
