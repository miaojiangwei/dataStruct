package com.mjw.queue;

public class CircleQueue {

	/**
	 * 环形队列
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

	}

}

class CircleArray {
	private int maxSize;
	// front初始值=0
	private int front;
	// 初始值等于maxSize
	private int rear;
	private int[] arr;

	public CircleArray(int maxsize) {
		maxSize = maxsize;
		front = 0;
		rear = maxSize;
		arr = new int[maxSize];
	}

	// 判断队列是否满
	public boolean isFull() {
		return (rear + 1) % maxSize == front;
	}

	// 判断队列是否空
	public boolean isEmpty() {
		return front == rear;
	}

	// 添加数据到队列
	public void addQueue(int n) {
		// 判断队列是否满
		if (isFull()) {
			System.out.println("队列满，不能加入数据");
			return;
		}
		//直接将数据加入
		arr[rear]=n;
		//将rear后移，必须考虑取模
		rear=(rear+1)%maxSize;
	}

	// 获取队列中的数据
	public int getQueue() {
		// 判断队列是否空
		if (isEmpty()) {
			throw new RuntimeException("队列为空，无法取出数据");
		}
		//这里需要分析front是指向队列的第一个元素
		//1.先把front对应的变量保存到一个临时变量中，
		//2.front后移
		//3.将临时变量返回
		int temp=arr[front];
		front=(front+1)%maxSize;
		return temp;
	}

	// 显示队列的所有数据
	public void showQueue() {

		// 判断队列是否为空
		if (isEmpty()) {
			throw new RuntimeException("队列为空，无法取出所有元素");
		}
		//从front开始遍历，遍历多少个元素
		for (int i = front; i < front+size(); i++) {
			System.out.println(i%maxSize + "," + arr[i%maxSize] + " ");
		}
	}
	//求出当前数组的有效个数
	public int size() {
		return (rear+maxSize-front)% maxSize;
	}
	// 显示队列的头数据，不是取数据
	public int headQueue() {
		// 判断是否是空
		if (isEmpty()) {
			throw new RuntimeException("队列为空，无法查看");
		}
		return arr[front];

	}
}