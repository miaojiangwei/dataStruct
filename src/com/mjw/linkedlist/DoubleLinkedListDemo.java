package com.mjw.linkedlist;

/***
 * 
	1.遍历：和单链表的思路一致
	2.添加：
	          先找到双向链表的最后    temp.next=new HeroNode  newHeroNode.pre=temp;
	3.修改也是和单链表的思路一致
	4.删除  因为是双向链表，可以自我删除，直接找到要删除的结点，比如：temp
	    temp.pre.next=temp.next
	    temp.next.pre=temp.pre;	
 * @author 拾光
 *
 */
public class DoubleLinkedListDemo { 

	public static void main(String[] args) {
	
		HeroNode2 heroNode1=new HeroNode2(1, "faker", "大魔王");
		HeroNode2 heroNode2=new HeroNode2(2, "bang", "打狗棒");
		HeroNode2 heroNode3=new HeroNode2(3, "Wolf", "老科黑");
		HeroNode2 heroNode4=new HeroNode2(4, "Mlxg", "香菇妹");
		HeroNode2 heroNode5=new HeroNode2(5, "Ming", "交际花");
		
		//2.创建一个链表，把结点添加到链表中
		DoubleLinkedList doubleLinkedList=new DoubleLinkedList();

		//3.添加结点
		doubleLinkedList.addHeroNode(heroNode1);
		doubleLinkedList.addHeroNode(heroNode2);
		doubleLinkedList.addHeroNode(heroNode3);
		doubleLinkedList.addHeroNode(heroNode4);
		doubleLinkedList.addHeroNode(heroNode5);
		//3.显示链表中数据
		doubleLinkedList.show();
		System.out.println("-----------------------------------------");
		System.out.println("测试删除·····");
		doubleLinkedList.delete(4);
		doubleLinkedList.show();
		
	}

}
//4.定义SingleLinkedList来管理英雄结点
class DoubleLinkedList{
	
	//初始化一个头节点,头结点不可以动，不存放具体的数据
	private HeroNode2 head=new HeroNode2(0, "", "");
	
	//返回头结点
	public HeroNode2 getHead() {
		return head;
	}
	
	
	/**
	 * 	添加一个结点到双向链表
	 * @param heroNode
	 */
	public void addHeroNode(HeroNode2 heroNode ) {
		
		//1.不考虑编号顺序时，找到当前链表的最后结点，将最后这个结点的next域指向新节点
		//2.因为头结点不可以动，因此我们需要一个辅助结点指针temp
		HeroNode2 temp=head;
		//3.遍历链表，找到最后
		while(true) {
			//4.到达链表的最后
			if(temp.next==null) {
				break;
			}
			//5没有找到最后，就将temp后移
			temp=temp.next;
		}
		//6.当退出循环，temp就指向了链表的最后,形成一个双向链表
		temp.next=heroNode;
		heroNode.pre=temp;
	}
	
	
	
	
	/**
	 *	 显示双向链表信息
	 */
	public void show() {
		
		//1.先判断链表是否是空
		if(head.next==null) {
			return;
		}
		//2.不为空，头结点不动，需要辅助变量temp来遍历
		HeroNode2 temp=head.next;
		while(true) {
			//3.判断是否到达链表的最后
			if(temp==null) {
				break;
			}
			//输出temp信息
			System.out.println(temp);
			//temp后移
			temp=temp.next;
		}
	}
	
	/**
	 * 	修改结点的信息，根据no来改，所以保证no不变，如果改变就意味添加新节点了
	 *  @param newheroNode
	 */
	public void update(HeroNode2 newheroNode) {
		
		//0.根据新节点的no来修改即可
		//1.判断链表是否为空
		if(head.next==null) {
			return;
		}
		//2.找到需要修改的结点，根据no来改
		//3.先定义辅助指针
		HeroNode2 temp=head.next;
		boolean flag=false;
		while(true) {
			if(temp.next.no==newheroNode.no) {
				//4.找到
				flag=true;
				break;
			}
			temp=temp.next;
		}
		//5.根据flag判断是否找到修改的结点
		if(flag) {
			temp.name=newheroNode.name;
			temp.nickName=newheroNode.nickName;
		}else {
			System.out.println("没有找到要修改的结点");
		}
	}
	
	
	/**
	 * 删除双向链表结点，直接找到删除的结点，自我删除
	 * @param no
	 */
	public void delete(int no) {
		
		//0.判断当前链表是否为空
		
		if(head.next==null) {
			//1.链表是空的
			System.out.println("当前链表为空");
			return;
		}
		//2.定义辅助结点方便遍历
		HeroNode2 temp=head;
		boolean flag=false;  //3.标志是否删除
		while(true) {
			if(temp==null) {
				//4.已到链表的最后
				break;
			}
			if(temp.no==no) {
				//5.找到了
				flag=true;
				break;
			}
			temp=temp.next; //6.后移
		}
		if(flag) {
			//7.删除核心逻辑
			temp.pre.next=temp.next;
			//8.注意，如果删除的结点是最后一个结点，不需要这个了，否则会空指针异常
			if(temp.next!=null) {
				temp.next.pre=temp.pre;	
			}
		}else {
			System.out.println("没有找到要删除的结点"+temp.next.no);
		}
	}
	
	
	//统计单链表的有效节点个数，如果有头结点，排除掉
	public int getLength(HeroNode2 heroNode) {
		if(head.next==null) { //空链表
			return 0;
		}
		int length=0;
		//定义一个辅助变量temp，用于遍历链表,这里我们没有统计头结点
		HeroNode2 temp=head.next;
		while(temp!=null) {
			
			length++;
			temp=temp.next;
		}
		return length;
	}
}



//1.定义一个heroNode结点，每个heronode对象就是一个结点
class HeroNode2{
	public int no;
	public String name;
	public String nickName;
	public HeroNode2 next;  //指向下一个结点的数据域 默认为null
	public HeroNode2 pre;  //指向前一个结点的数据域，默认为null
	
	//2.构造器
	public HeroNode2(int no,String name,String nickName ) {
		this.no=no;
		this.name=name;
		this.nickName=nickName;
		
	}

	//3.重写toString,显示方便
	@Override
	public String toString() {
		return "heroNode [no=" + no + ", name=" + name + ", nickName=" + nickName +"]";
	}
}