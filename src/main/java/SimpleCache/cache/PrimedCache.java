package SimpleCache.cache;

import lombok.extern.java.Log;

@Log
public class PrimedCache implements Cache {
    private Cache cache;

    public PrimedCache(DemandCache cache) {
        this.cache = cache;
        cache.get("k1");
        cache.get("k2");
    }

    @Override
    public Object get(String key) {
        log.info("get by key " + key);
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
}
