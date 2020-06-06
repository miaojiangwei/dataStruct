package com.mjw.linkedlist;

public class SingleLinkedListDemo {

    public static void main(String[] args) {
        //测试
        HeroNode heroNode1=new HeroNode(1, "faker", "大魔王");
        HeroNode heroNode2=new HeroNode(2, "bang", "打狗棒");
        HeroNode heroNode3=new HeroNode(3, "wolf", "小狼仔");
        HeroNode heroNode4=new HeroNode(4, "uzi", "狂小狗");

        //2.创建一个链表，把结点添加到链表中
        SingleLinkedList singleLinkedList=new SingleLinkedList();
//		singleLinkedList.addHeroNode(heroNode1);
//		singleLinkedList.addHeroNode(heroNode2);
//		singleLinkedList.addHeroNode(heroNode3);
//		singleLinkedList.addHeroNode(heroNode4);
        singleLinkedList.addHeroNodeByNo(heroNode1);
        singleLinkedList.addHeroNodeByNo(heroNode3);
        singleLinkedList.addHeroNodeByNo(heroNode2);
        singleLinkedList.addHeroNodeByNo(heroNode4);

        //3.显示链表中数据
        singleLinkedList.show();
        System.out.println("----------------------");
        //4.删除一个结点
        singleLinkedList.delete(3);
        singleLinkedList.delete(2);
        singleLinkedList.delete(1);
        singleLinkedList.delete(4);
        singleLinkedList.show();
    }

}
//4.定义SingleLinkedList来管理英雄结点
class SingleLinkedList{

    //初始化一个头节点,头结点不可以动，不存放具体的数据
    private HeroNode head=new HeroNode(0, "", "");

    //第二种添加元素的方式，根据排名有序添加
    public void addHeroNodeByNo(HeroNode heroNode) {

        //1.因为头结点不可以动，因此我们需要一个辅助结点指针temp
        //2.因为时单链表，我们找的temps是位于添加位置的前一个结点，否则插入失败
        HeroNode temp=head;
        //标志添加的位置是否存在，默认不存在
        Boolean flag=false;
        while(true) {
            if(temp.next==null) {
                //已经到链表的最后，不管是否找到，都要break；
                break;
            }
            if(temp.next.no>heroNode.no){  //位置找到，就在temp的后面插入

                break;
            }else if(temp.next.no==heroNode.no) {
                //说明编号存在
                flag=true;
                break;
            }
            temp=temp.next;
        }
        if(flag) {
            System.out.println("编号"+heroNode.no+"已存在，不能插入");
        }else {
            //插入到链表，temp的后面
            heroNode.next=temp.next;
            temp.next=heroNode;
        }
    }

    //添加一个结点到链表
    public void addHeroNode(HeroNode heroNode ) {

        //1.不考虑编号顺序时，找到当前链表的最后结点，将最后这个结点的next域指向新节点
        //2.因为头结点不可以动，因此我们需要一个辅助结点指针temp
        HeroNode temp=head;
        //3.遍历链表，找到最后
        while(true) {
            //到达链表的最后
            if(temp.next==null) {
                break;
            }
            //没有找到最后，就将temp后移
            temp=temp.next;
        }
        //当退出循环，temp就指向了链表的最后
        temp.next=heroNode;
    }


    //显示链表信息
    public void show() {

        //1.先判断链表是否是空
        if(head.next==null) {
            return;
        }
        //2.不为空，头结点不动，需要辅助变量temp来遍历
        HeroNode temp=head.next;
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

    //修改结点的信息，根据no来改，所以保证no不变，如果改变就意味添加新节点了
    //根据新节点的no来修改即可
    public void update(HeroNode newheroNode) {

        //判断链表是否为空
        if(head.next==null) {
            return;
        }
        //找到需要修改的结点，根据no来改
        //先定义辅助指针
        HeroNode temp=head.next;
        boolean flag=false;
        while(true) {
            if(temp.next.no==newheroNode.no) {
                //找到
                flag=true;
                break;
            }
            temp=temp.next;
        }
        //根据flag判断是否找到修改的结点
        if(flag) {
            temp.name=newheroNode.name;
            temp.nickName=newheroNode.nickName;
        }else {
            System.out.println("没有找到要修改的结点");
        }
    }

    public void delete(int no) {
        //定义辅助指针找到删除结点的前一个结点
        //我们比较的时候，是temt.next.no和需要删除的结点进行比较
        HeroNode temp=head;
        boolean flag=false; //标志是否找到，默认没找到
        while(true) {
            if(temp.next==null) {
                //链表是空的
                System.out.println("链表为空");
                return;
            }
            if(temp.next.no==no) {
                //找到待删除结点的前一个结点
                flag=true;
                break;
            }
            temp=temp.next; //后移
        }
        if(flag) {
            temp.next=temp.next.next;
        }else {
            System.out.println("没有找到要删除的结点"+temp.next.no);
        }
    }


    //统计单链表的有效节点个数，如果有头结点，排除掉
    public int getLength(HeroNode heroNode) {
        if(head.next==null) { //空链表
            return 0;
        }
        int length=0;
        //定义一个辅助变量temp，用于遍历链表,这里我们没有统计头结点
        HeroNode temp=head.next;
        while(temp!=null) {

            length++;
            temp=temp.next;
        }
        return length;
    }
}



//1.定义一个heroNode结点，每个heronode对象就是一个结点
class HeroNode{
    public int no;
    public String name;
    public String nickName;
    public HeroNode next;  //指向下一个结点的数据域

    //2.构造器
    public HeroNode(int no,String name,String nickName ) {
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