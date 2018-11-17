package SimpleCache.cache;

public interface DemandCache {
    Object get(String key);
    void put(String key, Object obj);
    Object remove(String key);
    void clear();
}
