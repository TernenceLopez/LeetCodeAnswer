package com.ustc.doubleOrientLinkList;

public class TestLinkList {
    public static void main(String[] args) {
        MyLinkedList obj = new MyLinkedList();
//        int param_1 = obj.get(index);
        obj.addAtIndex(0,0);
        obj.addAtIndex(0,0);
        obj.addAtIndex(1,0);
        obj.get(0);
        obj.addAtHead(7);
//        obj.addAtHead(2);
//        obj.addAtHead(1);
//        obj.addAtIndex(3,0);

        obj.deleteAtIndex(0);
        obj.addAtHead(6);
        obj.addAtTail(4);
        obj.get(4);
        obj.addAtHead(4);
        obj.addAtIndex(5,0);
        obj.addAtHead(6);
        obj.addAtIndex(3,0);
    }
}
