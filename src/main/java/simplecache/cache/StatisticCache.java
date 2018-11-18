package simplecache.cache;

import lombok.extern.java.Log;

import java.util.HashMap;
import java.util.Map;

@Log
public class StatisticCache implements Cache {
    private Cache cache;

    private static final Map<String, Integer> statistic = new HashMap<>();

    public StatisticCache(Cache cache) {
        this.cache = cache;
    }

    @Override
    public Object get(String key) {
        log.info("get by key " + key);
        statistic.put(key, statistic.getOrDefault(key, 1) + 1);
        return cache.get(key);
    }

    @Override
    public void put(String key, Object obj) {
        log.info(String.format("put by key %s, object %s", key, obj));
        cache.put(key, obj);
    }

    @Override
    public Object remove(String key) {
        log.info("remove by key " + key);
        return cache.remove(key);
    }

    @Override
    public void clear() {
        log.info("clear cache");
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

    public void printStatistic()
    {
        System.out.println(statistic);
    }
}
