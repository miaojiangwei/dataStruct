package com.mjw.stack;

public class ArrayStackDemo {

	public static void main(String[] args) {

		ArrayStack arrayStack = new ArrayStack(5);
		System.out.println("入栈");
		arrayStack.push(1);
		arrayStack.push(2);
		arrayStack.push(3);
		arrayStack.show();

	}

}

//1.定义一个类,表示栈结构
class ArrayStack {
	private int maxSize; // 数组的最大容量
	private int[] stack;
	private int top = -1; // 1表示栈顶，初始化-1

	// 构造器
	public ArrayStack(int size) {
		maxSize = size;
		stack = new int[maxSize];
	}

	// 栈满
	public boolean isFull() {
		return top == maxSize - 1;
	}

	// 栈空
	public boolean isEmpty() {
		return top == -1;
	}

	// 入栈
	public void push(int value) {
		// 栈是否满
		if (isFull()) {
			System.out.println("栈满了，");
			return;
		}
		top += 1;
		stack[top] = value;
	}

	// 出栈
	public int pop() {
		if (isEmpty()) {
			throw new RuntimeException("栈空了");
		}
		int value = stack[top];
		top--;
		return value;
	}

	// 遍历栈
	public void show() {
		if (isEmpty()) {
			throw new RuntimeException("栈空了");
		}
		for (int i = top; i >= 0; i--) {
			System.out.print(stack[i] + "  ");
		}
		System.err.println();
	}
}