package xdp.test.thread7.chapter6.DelayedQueue;
import java.util.Date;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;
public class Event implements Delayed{
	private Date startDate;// 事件的激活日期
	public Event (Date startDate) {this.startDate=startDate;}
	public long getTime(){
		return startDate.getTime();
	}
	@Override
	public int compareTo(Delayed o) {
		long result = this.getDelay(TimeUnit.NANOSECONDS)-o.getDelay(TimeUnit.NANOSECONDS);
		if(result>0){
			return 1;
		}else if(result<0){
			return -1;
		}
		return 0;
	}
	@Override
	public long getDelay(TimeUnit unit) {
		Date now=new Date();
		// 激活日期-实际日期
		long diff=startDate.getTime()-now.getTime();

		return unit.convert(diff, TimeUnit.NANOSECONDS);
	}
}
