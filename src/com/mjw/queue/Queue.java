package com.mjw.queue;

public class Queue {

	public static void main(String[] args) {
		//创建一个队列
		ArrayQueue queue=new ArrayQueue(5);
		queue.addQueue(10);
		queue.addQueue(11);
		queue.addQueue(12);
		queue.addQueue(13);
		queue.showQueue();
		System.out.println(queue.getQueue());
		queue.showQueue();
		System.out.println(queue.headQueue());
	}

}

//使用数组模拟一个队列，编写一个类
class ArrayQueue{
	private int maxSize;  //数组的最大容量
	private int front;  //队首
	private int rear;  //队尾
	private int[]arr;  //该数组用于存放数据，模拟队列
	
	//创建队列的构造器
	public ArrayQueue(int maxsize) {
		maxSize=maxsize;
		arr=new int[maxSize];
		front=-1;  //指向队列头部的前一个位置
		rear=-1;   //指向队列尾部的具体数据(即就是队列最后一个数据)
			
	}
	
	//判断队列是否满
	public boolean isFull() {
		return rear==maxSize-1;
	}
	//判断队列是否空
	public boolean isEmpty() {
		return front==rear;
	}
	
	//添加数据到队列
	public void addQueue(int n) {
		//判断队列是否满
		if(isFull()) {
			System.out.println("队列满，不能加入数据");
			return;
		}
		rear++;  //rear后移
		arr[rear]=n;
	}
	//获取队列中的数据
	public int getQueue() {
		//判断队列是否空
		if(isEmpty()) {
			throw new RuntimeException("队列为空，无法取出数据");
		}
		front++; //队首++
		return arr[front];
	}
	//显示队列的所有数据
	public void showQueue() {
		
		//判断队列是否为空
		if(isEmpty()) {
			throw new RuntimeException("队列为空，无法取出所有元素");
		}
		for (int i = 0; i < arr.length; i++) {
			System.out.println(i+","+arr[i]+" ");
		}
	}
	
	//显示队列的头数据，不是取数据
	public int  headQueue() {
		//判断是否是空
		if(isEmpty()) {
			throw new RuntimeException("队列为空，无法查看");
		}
		return arr[front+1];
		
	}
}
