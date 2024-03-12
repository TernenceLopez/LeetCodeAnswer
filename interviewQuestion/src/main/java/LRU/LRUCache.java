package LRU;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache<K, V> extends LinkedHashMap<K, V> {
    private int capacity;

    public LRUCache(int capacity) {
        // 调用父类构造方法设置元素按照访问顺序排序为true，这样元素将按照访问顺序进⾏排序
        super(capacity, 0.75f, true);
        this.capacity = capacity;
    }
    @Override
    public V get(Object key) {
        return super.getOrDefault(key, null);
    }

    @Override
    public V put(K key, V value) {
        return super.put(key, value);
    }

    @Override
    public boolean removeEldestEntry(Map.Entry<K, V> entry) {
        return this.size() > capacity;
    }
}

