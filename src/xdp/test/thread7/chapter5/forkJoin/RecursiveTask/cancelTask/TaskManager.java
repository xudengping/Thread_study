package xdp.test.thread7.chapter5.forkJoin.RecursiveTask.cancelTask;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinTask;

public class TaskManager {
	
	private List<ForkJoinTask<Integer>> tasks;
	
	public TaskManager(){
		tasks=new ArrayList<>();
		}
	
	public void addTask(ForkJoinTask<Integer> task){
		tasks.add(task);
		}
	
	public void cancelTasks(ForkJoinTask<Integer> cancelTask){
		for (ForkJoinTask<Integer> task : tasks) {
			if(task != cancelTask){
				task.cancel(true);// 如果任务未执行则取消任务,已经执行的任务不受影响
				((SearchNumberTask)task).writeCancelMessage();
			}
		}
	}




}
