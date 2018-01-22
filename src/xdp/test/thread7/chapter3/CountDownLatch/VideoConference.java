package xdp.test.thread7.chapter3.CountDownLatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

// 视频会议类
public class VideoConference implements Runnable{
	
	// 声明一个控制器用于同步其他线程
	private final CountDownLatch controller;
	
	public VideoConference(int number){
		// 初始化需等待多少个线程执行完成
		controller = new CountDownLatch(number);
	}
	
	public void arrive(String name){
		System.out.printf("%s has arrived.\n",name);
		// 计数器减1
		controller.countDown();
		// 打印还需等待多少个参与者
		System.out.printf("VideoConference:Waiting for %d participants.\n",controller.getCount());
	}

	@Override
	public void run() {
		System.out.printf("VideoConference:Initializatio: %d participants.\n",controller.getCount());
		try {
			// 等待所有的参与者到来才开始时会议
			// 当controller计数器值为0唤醒这里
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
