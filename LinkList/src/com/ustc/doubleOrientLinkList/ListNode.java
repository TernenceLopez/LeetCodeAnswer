package com.ustc.doubleOrientLinkList;

class ListNode{
    int val;
    ListNode next;
    ListNode prev;


    public ListNode() {
    }

    public ListNode(int val, ListNode prev, ListNode next) {
        this.val = val;
        this.next = next;
        this.prev = prev;
    }

    /**
     * 获取
     * @return val
     */
    public int getVal() {
        return val;
    }

    /**
     * 设置
     * @param val
     */
    public void setVal(int val) {
        this.val = val;
    }

    /**
     * 获取
     * @return next
     */
    public ListNode getNext() {
        return next;
    }

    /**
     * 设置
     * @param next
     */
    public void setNext(ListNode next) {
        this.next = next;
    }

    /**
     * 获取
     * @return prev
     */
    public ListNode getPrev() {
        return prev;
    }

    /**
     * 设置
     * @param prev
     */
    public void setPrev(ListNode prev) {
        this.prev = prev;
    }

}
