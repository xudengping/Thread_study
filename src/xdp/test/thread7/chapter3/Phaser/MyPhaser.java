package xdp.test.thread7.chapter3.Phaser;

import java.util.concurrent.Phaser;

public class MyPhaser extends Phaser{

	// ÿһ��Phaser.arriveAndAwaitAdvance ���������ʱ����
	// ÿһ�׶θı�
	@Override
	protected boolean onAdvance(int phase, int registeredParties) {
		switch(phase){
		case 0:
			System.out.println("MyPhaser״̬:0");
			return studentArrived();
		case 1:
			System.out.println("MyPhaser״̬:1");
			return finishedFirstExercise();
		case 2:
			System.out.println("MyPhaser״̬:2");
			return finishedSecondExercise();
		case 3:
			System.out.println("MyPhaser״̬:3");
			return finishExam();
			default:return true;
		}
		
	}
	
	private boolean studentArrived() {
		System.out.printf("ѧ��ȫ�����뿼�Կ�ʼ��ѧ����%d\n",this.getRegisteredParties());
		return false;
	}

	
	private boolean finishedFirstExercise() {
		System.out.printf("����ѧ������ɵ�һ�������⣬������һ����\n");
		// �л�����һ������
		return false;
	}

	private boolean finishedSecondExercise() {
		System.out.printf("����ѧ������ɵڶ��������⣬������һ����\n");
		// �л�����һ������
		return false;
	}

	private boolean finishExam() {
		System.out.printf("�������\n");
		return false;
	}

	

}
