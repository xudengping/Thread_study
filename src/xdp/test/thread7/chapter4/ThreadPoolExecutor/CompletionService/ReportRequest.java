package xdp.test.thread7.chapter4.ThreadPoolExecutor.CompletionService;

import java.util.concurrent.CompletionService;

// 任务的提交
public class ReportRequest implements Runnable{
	private String name;

	private CompletionService<String> service;
	
	public ReportRequest(String name, CompletionService<String> service){
		this.name=name;
		this.service=service;
		}

	@Override
	public void run() {
		ReportGenerator generator = new ReportGenerator(name, "Report");
		service.submit(generator);
		
	}


}
