package LRU;

public class TryLRUDLinkedNode {
    public static void main(String[] args) {
        LRUCacheDLinkedNode lruCacheDLinkedNode = new LRUCacheDLinkedNode(2);
//        lruCacheDLinkedNode.put(1,0);
//        lruCacheDLinkedNode.put(2,2);
//        lruCacheDLinkedNode.get(1);
//        lruCacheDLinkedNode.put(3,3);
//        lruCacheDLinkedNode.get(2);
//        lruCacheDLinkedNode.put(4,4);
//        lruCacheDLinkedNode.get(1);
//        lruCacheDLinkedNode.get(3);
//        lruCacheDLinkedNode.get(4);
        lruCacheDLinkedNode.put(2,1);
        lruCacheDLinkedNode.put(1,1);
        lruCacheDLinkedNode.put(2,3);
        lruCacheDLinkedNode.put(4,1);
        lruCacheDLinkedNode.get(1);
        lruCacheDLinkedNode.get(2);
    }
}
