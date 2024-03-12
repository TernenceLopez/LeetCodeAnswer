package LRU;

import java.util.HashMap;

public class LRUCacheDLinkedNode {

    // 双向链表节点
    class DLinkedNode{
        int key;
        int value;
        DLinkedNode prev;
        DLinkedNode next;
        public DLinkedNode(){}
        public DLinkedNode(int key, int value){
            this.key = key;
            this.value = value;
        }
    }

    HashMap<Integer, DLinkedNode> map = new HashMap<>(); // {key, DLinkedNode}，快速判断是否存在于LRU中
    private int capacity; // LRU 容量
    private DLinkedNode head, tail; // dummy节点，双向链表的首尾节点
    private int curVolume; // 当前容量

    public LRUCacheDLinkedNode(int capacity) {
        this.capacity = capacity;
        this.curVolume = 0;
        head = new DLinkedNode();
        tail = new DLinkedNode();
        head.next = tail; tail.prev = head;
    }

    public int get(int key) {
        if(!map.containsKey(key)) return -1;
        // 访问的节点移到链表首位
        moveToHead(map.get(key));
        return map.get(key).value;
    }

    public void put(int key, int value) {
        if(map.containsKey(key)){
            // 如果 key 存在，先通过哈希表定位，再修改 value，并移到头部，最近一次使用的节点
            map.get(key).value = value;
            moveToHead(map.get(key));
            return;
        }

        DLinkedNode node = new DLinkedNode(key, value);
        map.put(key, node);
        if(capacity==curVolume){
            // 移除队列尾部节点
            map.remove(tail.prev.value);
            tail.prev.prev.next = tail;
            tail.prev = tail.prev.prev;
            // 向队列头插入节点
            node.next = head.next;
            head.next.prev = node;
            head.next = node;
            node.prev = head;
        }else{
            // 向队列头插入节点
            node.next = head.next;
            head.next.prev = node;
            head.next = node;
            node.prev = head;

            curVolume++;
        }
    }

    public void moveToHead(DLinkedNode node){
        // 链表中移除这个节点
        node.prev.next = node.next;
        node.next.prev = node.prev;

        // 在链表头部添加这个节点
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
        node.prev = head;
    }
}
