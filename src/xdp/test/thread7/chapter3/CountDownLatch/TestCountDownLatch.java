package xdp.test.thread7.chapter3.CountDownLatch;

public class TestCountDownLatch {

	public static void main(String[] args) {
		// ������Ƶ������Ҫ10λ��Ա�ĵ������ܿ�ʼ����
		VideoConference conference = new VideoConference(10);
		Thread conferenceThread = new Thread(conference);
		conferenceThread.start();
		
		for(int i=0;i<10;i++){
			Participant p = new Participant(conference,"Participant"+i);
			Thread pThread = new Thread(p);
			pThread.start();
			
		}

	}

}
