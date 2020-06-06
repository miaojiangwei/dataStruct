package com.mjw.linkedlist;

/**
 * 约瑟夫问题
 * @author miaojiangwei
 *
 */
/**
 * @author miaojiangwei
 *
 */
public class Josepfu {

	public static void main(String[] args) {
		
		CircleLinkedList circleLinkedList=new CircleLinkedList();
		circleLinkedList.addBoy(5);

		
		System.out.println("显示链表中的数据");
		circleLinkedList.show();
		
		//测试出圈
		circleLinkedList.countBoy(1, 2, 5);
	}

}

//创建环形链表
class CircleLinkedList{
	
	//创建一个first结点，当前没有编号
	private Boy first=null;
	
	//添加结点，构建环形链表
	public void addBoy(int nums) {
		//num做数据校验
		if(nums<1) {
			System.out.println("数据不正确");
			//数据不正确
			return;
		}
		Boy curBoy=null;  //辅助指针，帮助构建
		//循环创建
		for(int i=1;i<=nums;i++) {
			//根据编号创建小孩结点
			Boy boy=new Boy(i);
			//第一个小孩注意
			if(i==1) {
				first=boy;
				first.setNext(first); //构成环
				curBoy=first;
			}else {
				curBoy.setNext(boy);
				boy.setNext(first);
				curBoy=curBoy.getNext();
			}			
		}
	}
	
	/**
	 * 根据用户的输入，出圈
	 * @param  startNo表示从第几个开始，
	 * @param  countNum表示数几下
	 * @param  nums表示有几个小孩在内
	 */
	
	public void countBoy(int startNo,int countNum,int nums) {
		//先对数据进行校验
		if(first==null||startNo<1||startNo>nums){
			System.out.println("参数输入不合理，请重新输入.....");
		}
		//创建辅助指针，帮助出圈
		Boy helper=first;
		//需求创建一个辅助指针helper，事先应该指向链表的最后这个结点
		while(true) {
			if(helper.getNext()==first) {  //helper指向最后一个结点
				break;
			}
			helper=helper.getNext();
		}
		//报数之前，先让helper和first移动k-1次
		for(int j=0;j<startNo-1;j++) {
			first=first.getNext();
			helper=helper.getNext();
		}
		//报数之前，先让helper和first移动m-1次，然后出圈
		//循环操作，直到圈中z只有一个结点
		while(true) {
			if(helper==first) {  //圈中只有一人
			break;
			}
			//helper和first移动countNum-1次
			for(int j=0;j<countNum-1;j++) {
				first=first.getNext();
				helper=helper.getNext();
			}
			//这时first指向的就是出圈的结点
			System.out.println("小孩no是："+first.getNo()+"出圈");
			//出圈
			first=first.getNext();
			helper.setNext(first);
		}
		System.out.println("留在圈中的小孩编号："+first.getNo());
	}
	
	
	/**
	 * 遍历环形链表
	 */
	public void show() {
		//先判断链表是否空
		if(first==null) {
			System.out.println("链表为空");
			return;
		}
		//因为first不可以动，我们使用辅助指针完成遍历
		Boy curBoy=first;
		while(true) {
			System.out.println("小孩的编号是："+curBoy.getNo());
			if(curBoy.getNext()==first) {  //遍历完毕
				break;
			}
			//后移
			curBoy=curBoy.getNext();
		}
	}
}

//创建结点
class Boy{
	
	private int no;
	private Boy next;  //指向下一个对象
	
	public Boy(int no) {
		this.no=no;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public Boy getNext() {
		return next;
	}

	public void setNext(Boy next) {
		this.next = next;
	}
	
	
}