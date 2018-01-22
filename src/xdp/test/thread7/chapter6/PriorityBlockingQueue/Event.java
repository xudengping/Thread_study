package xdp.test.thread7.chapter6.PriorityBlockingQueue;

public class Event implements Comparable<Event>{
	
	private int thread;
	
	private int priority;
	public Event(int thread, int priority){
		this.thread=thread;
		this.priority=priority;
		}

	public int getThread() {
		return thread;
	}


	public void setThread(int thread) {
		this.thread = thread;
	}


	public int getPriority() {
		return priority;
	}


	public void setPriority(int priority) {
		this.priority = priority;
	}


	// Ä¬ÈÏÉýÐò
//	@Override
//	public int compareTo(Event o) {
//		if(this.priority>o.getPriority()){
//			return -1;
//		}else if(this.priority<o.getPriority()){
//			return 1;
//		}
//		return 0;
//	}
	
	@Override
	public int compareTo(Event o) {
		if(this.priority>o.getPriority()){
			return 1;
		}else if(this.priority<o.getPriority()){
			return -1;
		}
		return 0;
	}

}
