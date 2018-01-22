package xdp.test.thread7.chapter2.jmm;
/**
 * 懒汉式-延迟初始化问题:指令重排
 * @author dell
 *
 */
public class DoubleCheckLocking {
	
	private static Instance instance = null;
	
	private DoubleCheckLocking(){}
	
	public static Instance getInstance(){
		if(instance == null){
			synchronized (DoubleCheckLocking.class) {
				if(instance == null){
					instance = new Instance();
					// 会存在指令重排  memory = allocate();ctorInstance(memory);instance = memory;
				}
			}
		}
		return instance;
	}
	
	

}
