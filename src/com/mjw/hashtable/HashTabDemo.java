package com.mjw.hashtable;

/**
 * 6.哈希表数据结构（散列表）
 *     是根据关键码值而直接进行访问的数据结构，也就是说，通过把关键码值映射到表中的一个位置来访问记录，加快查找速率，z这个映射函数叫散列函数，
 *     存放记录的数组叫散列表
 */

public class HashTabDemo {
    public static void main(String[] args) {

        HashTab hashTab=new HashTab(7);
        Emp emp=new Emp(1,"faker");
        hashTab.add(emp);
        hashTab.list();
        System.out.println("------------------");
        hashTab.findEmpById(1);

    }
}

//表示一个雇员
class Emp{
    public int id;
    public String name;
    public Emp next;

    //构造器
    public Emp(int id,String name){
        this.id=id;
        this.name=name;
    }

}

//创建EmpLinkedList
class EmpLinkedList{

    //初始化头结点
    private Emp head; //默认是null

    //添加雇员信息，id是自增长的
    //直接加入本链表的最后
    public void addEmp(Emp emp){
        //如果是添加第一个雇员
        if(head==null){
            head=emp;
            return;
        }
        Emp curEmp=head;
        while (true) {
            if(curEmp.next==null){
                //到达链表的最后
                break;
            }
            curEmp=curEmp.next; //后移
        }
        //退出直接将emp加入链表
        curEmp.next=emp;
    }

    //遍历雇员信息
    public void list(){
        if(head==null){
            System.out.println("链表为空~~~");
            return;
        }
        Emp curEmp=head;
        while(true){
            System.out.printf("=>id=%d name=%s\t",curEmp.id,curEmp.name);
            //如果到达链表的最后
            if(curEmp.next==null){
                break;
            }
            curEmp=curEmp.next;
        }
        System.out.println();
    }

    //根据id查找Emp雇员信息,如果找到就返回Emp,没有找到返回null
    public Emp findEmpById(int id){
        if(head==null){
            System.out.println("链表为空~~~");
            return null;
        }
        Emp curEmp=head; //辅助指针
        while(true){
            if(curEmp.id==id){
                //找到了
               break;  //这时curEmp就是要查找的雇员
            }
            if(curEmp.next==null){
                //到达最后了
                curEmp=null;
            }
            curEmp=curEmp.next;
        }
        return curEmp;
    }
}

//创建Hashtab，管理多条链表
class HashTab{
    private EmpLinkedList[] empLinkedListArray;
    private int size;  //有几条链表

    //构造器
    public HashTab(int size){
        this.size=size;
        //初始化数组
        empLinkedListArray=new EmpLinkedList[size];
        //分别初始化empLinkedListArray
        for (int i = 0; i <size; i++) {
            empLinkedListArray[i]=new EmpLinkedList();
        }
    }

    //添加雇员
    public void add(Emp emp){
        //根据id顺序添加到那条链表
        int empLinkedListNo=hashFun(emp.id);
        empLinkedListArray[empLinkedListNo].addEmp(emp);

    }

    //遍历所有链表,遍历hashTab
    public void list(){
        for (int i=0;i<size;i++){
            empLinkedListArray[i].list();
        }
        System.out.println();
    }

    //根据输入id查找雇员
    public void findEmpById(int id){
        //使用散列函数确定在那条链表
        int empLinkedListNo=hashFun(id);
        Emp emp=empLinkedListArray[empLinkedListNo].findEmpById(id);
        if (emp!=null){
            System.out.println("id= "+id);
        }else{
            System.out.println("没有找到该雇员");
        }
    }

    //散列函数，返回哪一条链表
    public int hashFun(int id){
        return id % size;
    }
}