package xdp.test.thread7.chapter5.forkJoin.RecursiveTask;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.RecursiveTask;

public class DocumentTask extends RecursiveTask<Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2410971537752354447L;

	private String document[][];// 列
	private int start, end;
	private String word;

	public DocumentTask(String document[][], int start, int end, String word) {
		this.document = document;
		this.start = start;
		this.end = end;
		this.word = word;
	}

	@Override
	protected Integer compute() {
		int result=0;
		if (end - start < 10) {// 小于10行
			result = processLines(document, start, end, word);
		} else {
			int mid = (end + start) / 2;
			DocumentTask task1 = new DocumentTask(document, start, mid, word);
			DocumentTask task2 = new DocumentTask(document, mid, end, word);
			invokeAll(task1, task2);//  同步执行
			try {
				result = groupResults(task1.get(), task2.get());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

	private int groupResults(Integer number1, Integer number2) {
		Integer result;
		result = number1 + number2;
		return result;
	}

	private int processLines(String[][] document2, int start2, int end2, String word2) {
		List<LineTask> tasks = new ArrayList<LineTask>();
		for (int i = start2; i < end2; i++) {
			LineTask task = new LineTask(document[i], 0, document[i].length, word2);
			tasks.add(task);
		}
		invokeAll(tasks);
		int result = 0;
		for (int i = 0; i < tasks.size(); i++) {
			LineTask task = tasks.get(i);
			try {
				result = result + task.get();
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}
		return new Integer(result);
	}

}
