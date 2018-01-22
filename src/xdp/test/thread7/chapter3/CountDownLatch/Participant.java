package xdp.test.thread7.chapter3.CountDownLatch;

import java.util.concurrent.TimeUnit;

// ���������
public class Participant implements  Runnable{

	private VideoConference conference;// ������Ƶ�������
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
		
		// ������Ƶ���������name�ĵ���
		conference.arrive(name);
		
	}

}
