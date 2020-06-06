package com.mjw.tree;

public class BinaryTreeDemo {
    public static void main(String[] args) {
        //测试
        BinaryTree binaryTree=new BinaryTree();
        HeroNode root=new HeroNode(1,"faker");
        HeroNode heroNode1=new HeroNode(2,"Marin");
        HeroNode heroNode2=new HeroNode(3,"Bengi");
        HeroNode heroNode3=new HeroNode(4,"Blank");
        HeroNode heroNode4=new HeroNode(5,"Chovy");

        //构造二叉树
        binaryTree.setRoot(root);
        root.setLeft(heroNode1);
        root.setRight(heroNode2);
        heroNode2.setLeft(heroNode4);
        heroNode2.setRight(heroNode3);

        //测试
        System.out.println("前序遍历为：");
        binaryTree.preOrder();
        System.out.println("中序遍历为：");
        binaryTree.midOrder();
        System.out.println("后序遍历为：");
        binaryTree.postOrder();
        System.out.println("-----------------------------------------");

        System.out.println("前序遍历查找--->");
        HeroNode resNode=binaryTree.preOrderSearch(3);
        if(resNode!=null){
            System.out.println("查找到的编号："+resNode.getNo()+",姓名是："+resNode.getName());
        }else{
            System.out.println("没有你要查找的编号信息");
        }
        //------------------------------------
        System.out.println("中序遍历查找--->");
        HeroNode resNode1=binaryTree.midOrderSearch(2);
        if(resNode1!=null){
            System.out.println("查找到的编号："+resNode1.getNo()+",姓名是："+resNode1.getName());
        }else{
            System.out.println("没有你要查找的编号信息");
        }
        //------------------------------------
        System.out.println("后序遍历查找--->");
        HeroNode resNode2=binaryTree.postOrderSearch(10);
        if(resNode2!=null){
            System.out.println("查找到的编号："+resNode2.getNo()+",姓名是："+resNode2.getName());
        }else{
            System.out.println("没有你要查找的编号信息");
        }
    }
}

//创建一颗二叉树
class BinaryTree{

    //初始化一个根节点
    private HeroNode root;

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    //前序遍历
    public void preOrder(){
        if(this.root!=null){
            this.root.preOrder();
        }

    }

    //中序遍历
    public void midOrder(){
        if(this.root!=null){
            this.root.midOrder();
        }

    }
    //后序遍历
    public void postOrder(){
        if(this.root!=null){
            this.root.postOrder();
        }

    }

    //前序遍历查找
    public HeroNode preOrderSearch(int no){
        if(root!=null){
            return root.preOrderSearch(no);
        }else{
            System.out.println("二叉树为空~~~~");
            return null;
        }
    }
    //中序遍历查找
    public HeroNode midOrderSearch(int no){
        if(root!=null){
            return root.midOrderSearch(no);
        }else {
            System.out.println("二叉树为空~~~~");
            return null;
        }
    }
    //后序遍历查找
    public HeroNode postOrderSearch(int no){
        if(root!=null){
            return root.postOrderSearch(no);
        }else{
            System.out.println("二叉树为空~~~~");
            return null;
        }
    }

}

//创建HeroNode结点
class HeroNode{
    private int no;
    private String name;
    private HeroNode left;
    private HeroNode right;

    //构造器
    public HeroNode(int no,String name){
        this.no=no;
        this.name=name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    //前序遍历
    public void preOrder(){
        System.out.println(this);
        if(this.left!=null){
            this.left.preOrder();
        }
        if (this.right!=null){
            this.right.preOrder();
        }
    }
    //中序遍历
    public void midOrder(){
        if(this.left!=null){
            this.left.midOrder();
        }
        System.out.println(this);
        if (this.right!=null){
            this.right.midOrder();
        }
    }
    //后序遍历
    public void postOrder(){
        if(this.left!=null){
            this.left.postOrder();
        }
        if (this.right!=null){
            this.right.postOrder();
        }
        System.out.println(this);
    }

    //前序遍历查找
    public HeroNode preOrderSearch(int no){
        //判断当前结点是不是
        if(this.no==no){
            return this;
        }
        //1.判断当前结点的左节点是否为空,不为空，则递归前序查找
        //2.如果左递归前序查找，找到结点，则返回
        HeroNode resNode=null;
        if(this.left!=null){
            resNode=this.left.preOrderSearch(no);
        }
        if(resNode!=null){ //左子树找到，返回
            return resNode;
        }
        //1.判断当前结点的右节点是否为空,不为空，则递归前序查找
        //2.如果右递归前序查找，找到结点，则返回
        if(this.right!=null){
            resNode=this.right.preOrderSearch(no);
        }
        return resNode;
    }

    //中序遍历查找
    public HeroNode midOrderSearch(int no){

        //先判断当前结点的左节点是否为空，不为空，则递归中序查找
        HeroNode resNode=null;
        if(this.left!=null){
            resNode=this.left.midOrderSearch(no);
        }
        if(resNode!=null){
            return resNode;
        }
        //没有找到，就和当前结点比较
        if (this.no == no) {
            return  this;
        }
        //不相等，继续查看当前结点的右子结点是否为空，不为空，继续递归中序查找
        if(this.right!=null){
            resNode=this.right.midOrderSearch(no);
        }
        return resNode;
    }

    //后序遍历查找
    public HeroNode postOrderSearch(int no){

        //先判断当前结点的左子节点是否为空，不为空，继续左递归后序查找
        HeroNode resNode=null;
        if(this.left!=null){
            resNode=this.left.postOrderSearch(no);
        }
        //判断resNode是否空，不空，则证明找到了，直接返回
        if(resNode!=null){
            return resNode;
        }
        //判断当前结点的右子节点是否空，不为空，继续右递归后序遍历
        if(this.right!=null){
            resNode=this.right.postOrderSearch(no);
        }
        if(resNode!=null){
            return resNode;
        }
        if(this.no==no){
            return this;
        }
        return resNode;
    }

    //递归删除结点
    //如果要删除的结点是叶子结点，则直接删除改结点
    //如果删除的是非叶子结点，则直接删除该子树
    public void delNode(HeroNode heroNode){

    }

}
