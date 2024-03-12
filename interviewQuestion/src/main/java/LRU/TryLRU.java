package LRU;

public class TryLRU {
    public static void main(String[] args) {
        LRUCache<Integer, String> lruCache = new LRUCache<>(3);
        lruCache.put(1, "One");  // 修正：使用字符串值作为键 1
        lruCache.put(2, "Two");  // 修正：使用字符串值作为键 2
        lruCache.put(3, "Three");

        // 打印缓存的初始状态
        System.out.println(lruCache);

        // 访问一个元素以使其成为最近使用的元素
        String  value = lruCache.get(2);

        // 在访问一个元素后打印缓存的状态
        System.out.println(lruCache);

        // 添加新数据，触发淘汰
        lruCache.put(4, "Four");

        // 在添加新数据和淘汰后打印缓存的状态
        System.out.println(lruCache);
    }

}
