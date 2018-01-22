package xdp.test.thread7.chapter3.Phaser;

import java.util.concurrent.Phaser;

public class MyPhaser extends Phaser{

	// 每一次Phaser.arriveAndAwaitAdvance 都完成阻塞时调用
	// 每一阶段改变
	@Override
	protected boolean onAdvance(int phase, int registeredParties) {
		switch(phase){
		case 0:
			System.out.println("MyPhaser状态:0");
			return studentArrived();
		case 1:
			System.out.println("MyPhaser状态:1");
			return finishedFirstExercise();
		case 2:
			System.out.println("MyPhaser状态:2");
			return finishedSecondExercise();
		case 3:
			System.out.println("MyPhaser状态:3");
			return finishExam();
			default:return true;
		}
		
	}
	
	private boolean studentArrived() {
		System.out.printf("学生全部到齐考试开始，学生数%d\n",this.getRegisteredParties());
		return false;
	}

	
	private boolean finishedFirstExercise() {
		System.out.printf("所有学生都完成第一道测试题，进行下一道题\n");
		// 切换到下一个任务
		return false;
	}

	private boolean finishedSecondExercise() {
		System.out.printf("所有学生都完成第二道测试题，进行下一道题\n");
		// 切换到下一个任务
		return false;
	}

	private boolean finishExam() {
		System.out.printf("考试完成\n");
		return false;
	}

	

}
