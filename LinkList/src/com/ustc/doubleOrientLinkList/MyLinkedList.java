package com.ustc.doubleOrientLinkList;

class MyLinkedList {
    int length;
    ListNode head;

    public MyLinkedList() { // 构造函数
        length = 0;
        head = null;
    }

    public int get(int index) {
        if(head == null)
            return -1;

        ListNode curNode = head;  // 首先指向index=0的元素
        for(int i=1; i<=index; i++){
            if(curNode.next == null){
                return -1;
            }else{
                curNode = curNode.next;
            }
        }

        return curNode.val;
    }

    public ListNode getNode(int index){
        if(head == null)
            return null;

        ListNode curNode = head;  // 首先指向index=0的元素
        for(int i=1; i<=index; i++){
            if(curNode.next == null){
                return null;
            }else{
                curNode = curNode.next;
            }
        }

        return curNode;
    }

    public void addAtHead(int val) {
        if(head==null){
            ListNode newNode = new ListNode(val, null, null);
            head = newNode; // head指向新的头节点
        }else{
            ListNode newNode = new ListNode(val, null, head);
            head = newNode; // head指向新的头节点

            newNode.next.prev = newNode; // 之前的头节点的prev指针指向newNode
        }

        length++;
    }

    public void addAtTail(int val) {
        if(this.length == 0){
            addAtHead(val);
            return;
        }
        int lastIndex = this.length - 1; // 最后一个元素的索引
        ListNode lastNode = getNode(lastIndex);  // 获取最后一个元素
        ListNode newNode = new ListNode(val, lastNode, null);
        lastNode.next = newNode;
        length++;
    }

    public void addAtIndex(int index, int val) {
        if(index == length){
            addAtTail(val);  // 在addAtTail函数中length自加过了，不需要反复自加
            return;
        }

        if(index<=0){
            addAtHead(val);
            return;
        }

        if(index > length){
            return;
        }else{
            ListNode insertNode = getNode(index);
            ListNode newNode = new ListNode(val, insertNode.prev, insertNode);
            insertNode.prev.next = newNode;
            insertNode.prev = newNode;
            length++;
        }
    }

    public void deleteAtIndex(int index) {
        if(index<0 || index >= length){  // 下标无效
            return;
        }

        if(length==1) {
            head = null;
            return;
        }

        if(index == 0){ //头节点
            this.head.next.prev = null;
            this.head = this.head.next;
            length--;
            return;
        }

        if(index == length - 1) // 尾节点
        {
            ListNode delNode = getNode(index);
            delNode.prev.next = null;
            length--;
            return;
        }

        ListNode delNode = getNode(index);
        delNode.prev.next = delNode.next;
        delNode.next.prev = delNode.prev;
        length--;
    }
}
