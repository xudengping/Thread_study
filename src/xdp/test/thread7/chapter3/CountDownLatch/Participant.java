package xdp.test.thread7.chapter3.CountDownLatch;

import java.util.concurrent.TimeUnit;

// 会议参与者
public class Participant implements  Runnable{

	private VideoConference conference;// 持有视频会议对象
	private String name;
	
	public Participant(VideoConference conference,String name){
		this.conference = conference;
		this.name = name;
	}
	
	@Override
	public void run() {
		long duration = (long) (Math.random()*10);
		try {
			TimeUnit.SECONDS.sleep(duration);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// 设置视频会议参与者name的到来
		conference.arrive(name);
		
	}

}
