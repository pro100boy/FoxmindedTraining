package SimpleCache.cache;

import lombok.extern.java.Log;

import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Queue;

import static java.util.Objects.nonNull;

/**
 * LRU cache
 */
@Log
public class CollectorCache implements Cache {
    private Cache cache;

    private final int size = 10;

    private final Queue<Pair<String, Long>> queue;

    public CollectorCache(Cache cache) {
        this.cache = cache;
        queue = new PriorityQueue<>(size, Comparator.comparingLong(o -> o.value));
    }

    @Override
    public Object get(String key) {
        log.info("get by key " + key);

        Object o = cache.get(key);

        // remove and re-insert
        Pair<String, Long> pair = searchAndRemoveItem(key);
        pair.value = System.nanoTime();
        queue.add(pair);

        return o;
    }

    @Override
    public void put(String key, Object obj) {
        log.info(String.format("put by key %s, object %s", key, obj));

        // remove oldest element (head of queue)
        if (queue.size() == size) {
            queue.poll();
        }
        queue.add(new Pair<>(key, System.nanoTime()));
        cache.put(key, obj);
    }

    @Override
    public Object remove(String key) {
        log.info("remove by key " + key);

        if (cache.contains(key)) {
            searchAndRemoveItem(key);
        }

        return cache.remove(key);
    }

    @Override
    public void clear() {
        log.info("clear cache");
        queue.clear();
        cache.clear();
    }

    @Override
    public boolean isEmpty() {
        return cache.isEmpty();
    }

    @Override
    public boolean contains(String key) {
        return cache.contains(key);
    }

    public Object getOldestObject() {
        Pair<String, Long> pair = queue.peek();
        if (nonNull(pair))
            return cache.get(pair.key);

        return null;
    }

    private Pair<String, Long> searchAndRemoveItem(String key) {
        Iterator<Pair<String, Long>> iterator = queue.iterator();
        while (iterator.hasNext()) {
            Pair<String, Long> pair = iterator.next();
            if (pair.key.equals(key)) {
                iterator.remove();
                return pair;
            }
        }

        return new Pair<>(key, System.nanoTime());
    }

    class Pair<K, V> {
        K key;
        V value;

        Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "Pair{" +
                    "key=" + key +
                    ", value=" + value +
                    '}';
        }
    }
}

