package xdp.test.thread7.chapter4.ThreadPoolExecutor.CompletionService;

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

// 结果执行线程
public class ReportProcessor implements Runnable {

	private CompletionService<String> service;

	private boolean end;

	public ReportProcessor(CompletionService<String> service) {
		this.service = service;
		end = false;
	}
	
	public void setEnd(boolean end) {
		this.end = end;
	}


	@Override
	public void run() {
		while (!end) {
			try {
				// 返回一个已经完成的任务Future对象
				Future<String> result = service.poll(20, TimeUnit.SECONDS);
				System.out.println(result);
				if (result != null) {
					String report = result.get();
					System.out.printf("ReportReceiver: Report Received:%s\n", report);
				}
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}
		System.out.printf("ReportSender: End\n");

	}

}
