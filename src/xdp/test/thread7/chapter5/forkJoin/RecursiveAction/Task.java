package xdp.test.thread7.chapter5.forkJoin.RecursiveAction;

import java.util.List;
import java.util.concurrent.RecursiveAction;

// û�з��ؽ����
public class Task extends RecursiveAction {
	private static final long serialVersionUID = -7558853701261215285L;

	private List<Product> products;
	private int first;
	private int last;
	private double increment;

	public Task(List<Product> products, int first, int last, double increment) {
		this.products = products;
		this.first = first;
		this.last = last;
		this.increment = increment;
	}

	@Override
	protected void compute() {
		if (last - first < 1000) {
			updatePrices();
		} else {
			// ȡ�м������
			int middle = (last + first) / 2;
			System.out.printf("%s:middle:%d\n",Thread.currentThread().getName(),middle);
			System.out.printf("Task: Pending tasks:%s\n", getQueuedTaskCount());
			Task task1 = new Task(products, first, middle + 1, increment);
			Task task2 = new Task(products, middle + 1, last, increment);
			System.out.printf("%d start\n", middle);
			// ͬ��ִ��
			invokeAll(task1, task2);
			System.out.printf("%d end\n", middle);

		}

	}

	// ����first��������last
	private void updatePrices() {
		for (int i = first; i < last; i++) {
			Product product = products.get(i);
			product.setPrice(product.getPrice() * (1 + increment));
		}

	}

}
