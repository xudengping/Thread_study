package xdp.test.thread7.chapter2.jmm;
/**
 * ����ʽ-�ӳٳ�ʼ������:ָ������
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
					// �����ָ������  memory = allocate();ctorInstance(memory);instance = memory;
				}
			}
		}
		return instance;
	}
	
	

}
