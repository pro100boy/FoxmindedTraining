package SimpleCache.cache;

import SimpleCache.server.Server;
import lombok.extern.java.Log;

import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Log
public class DemandCache implements Cache {

    private static final Map<String, Object> cache = new HashMap<>();

    private Server server;

    public DemandCache(Server server) {
        this.server = server;
    }

    @Override
    public Object get(String key) {
        log.info("get by key " + key);
        Object obj = cache.get(key);
        if (isNull(obj)) {
            obj = server.getObject(key);
            put(key, obj);
        }
        return obj;
    }

    @Override
    public void put(String key, Object obj) {
        log.info(String.format("put by key %s, object %s", key, obj));
        if (nonNull(key) && nonNull(obj))
            cache.put(key, obj);
        else
            throw new UnsupportedOperationException();
    }

    @Override
    public Object remove(String key) {
        log.info("remove by key " + key);
        if (nonNull(key)) {
            return cache.remove(key);
        } else
            throw new UnsupportedOperationException();
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
        return cache.containsKey(key);
    }
}
