package com.mjw.stack;

public class Calculator {

    public static void main(String[] args) {

        String expression="30+2*6-2";
        ArrayStack1 numStack=new ArrayStack1(10);
        ArrayStack1 openStack=new ArrayStack1(10);

        //定义相关的变量
        int index=0;
        int num1=0;
        int num2=0;
        int oper=0;
        int res=0;
        char ch;  //把每次扫描的得到的char保存到ch
        String keepNum="";  //用于拼接多位数

        //开始循环扫描expression
        while(true){
            ch=expression.substring(index,index+1).charAt(0);
            //判断ch是什么，做相应处理
            if(openStack.isOper(ch)){
                if(openStack.isEmpty()){  //判断符号栈是否是否为空
                    //不空，如果符号栈有操作符，就要进行比较，如果当前操作符的优先级小于或等于栈中的操作符，
                    // 就需要从数栈中pop出俩个数，再从符号栈pop出一个符号，进行运算，将得到结果，放入数栈，
                    // 然后将当前的操作符放入符号栈
                    if (openStack.priorty(ch)<=openStack.priorty(openStack.peek())){
                        num1=numStack.pop();
                        num2=numStack.pop();
                        oper=openStack.pop();
                        res=numStack.cal(num1,num2,oper);
                        //把运算的结果加入数字栈
                        numStack.push(res);
                        //把当前操作符栈放入符号栈
                        openStack.push(ch);
                    }else{
                        //如果当前操作符的优先级大于栈中的操作符，直接放入操作符栈
                        openStack.push(ch);
                    }
                }else{
                    //是空栈，直接入符号栈
                    openStack.push(ch);
                }
            }else{
                //如果是数字，直接入数栈,因为是char，所以要转换为数字
                //numStack.push(ch-48);
                //处理多位数
                keepNum +=ch;

                //如果ch已经是表达式的最后一位，就直接入栈
                if(index==expression.length()-1){
                    numStack.push(Integer.parseInt(keepNum));
                }

                //判断下一位是不是数字，如果是数字，继续扫描，否则入栈
                if(openStack.isOper(expression.substring(index+1,index+2).charAt(0))){
                    //如果后一位是运算符，则入栈
                    numStack.push(Integer.parseInt(keepNum));
                    //重要！！！，keepNum清空
                    keepNum="";
                }

            }
            index++;
            if(index>=expression.length()-1){
                break;
            }
        }

        //当表达式结束，就顺序的从数栈和符号栈取出相应的数和符号，运行
        while (true){
            //如果符号栈是空，数栈应该只有一个最终的结果
            if (openStack.isEmpty()){
                //不为空
                break;
            }else {
                num1 = numStack.pop();
                num2 = numStack.pop();
                oper = openStack.pop();
                res = numStack.cal(num1, num2, oper);
                numStack.push(res);
            }
        }
        System.out.println("表达式"+expression+"表达式的结果是："+numStack.pop());

    }
}

//1.定义一个类,表示栈结构
class ArrayStack1 {
    private int maxSize; // 数组的最大容量
    private int[] stack;
    private int top = -1; // 1表示栈顶，初始化-1

    // 构造器
    public ArrayStack1(int size) {
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

    //增加一个方法返回当前栈的栈顶元素，但是不出栈
    public int peek(){
        return  stack[top];
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
    //返回运算符的优先级，手动确定优先级，数字表示，数字越大，优先级越大
    public int priorty(int oper){
        if(oper=='*'|| oper=='/'){
            return 1;
        }else if(oper=='+' || oper=='-'){
            return 0;
        }else{
            return -1;
        }
    }

    //判断是不是一个运算符
    public boolean isOper(char val){
        return val=='+'|| val=='-' || val=='*'|| val=='/';
    }

    //计算方法
    public int cal(int num1,int num2,int oper){
        int res=0;  //存放的结果
        switch(oper){
            case '+':
                res=num1+num2;
                break;
            case '-':
                res=num2-num1;  //注意顺序
                break;
            case '*':
                res=num1*num2;
                break;
            case '/':
                res=num1/num2;
                break;
            default:
                break;
        }
        return res;
    }
}