package ArrayToStack;

import java.security.cert.TrustAnchor;

public class Stack {
    private int[] arr;
    private int top;

    public Stack(int capacity){
        arr = new int[capacity];
        top = -1; // 指向栈顶元素
    }

    public void push(int element){
        if(top==arr.length-1){
            throw new IllegalArgumentException("Stack is Full");
        }
        top++;
        arr[top] = element;
    }

    public int pop(){
        if(top==-1){
            throw new IllegalArgumentException("Stack is Empty()");
        }
        top--;
        return arr[top+1];
    }

    public int top(){
        if(isEmpty()){
            throw new IllegalArgumentException("Stack is Empty()");
        }
        return arr[top];
    }

    public boolean isEmpty(){
        return top == -1;
    }

    public int size(){
        return top+1;
    }
}
