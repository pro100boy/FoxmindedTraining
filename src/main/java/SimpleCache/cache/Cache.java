package SimpleCache.cache;

public interface Cache {
    Object get(String key);
    void put(String key, Object obj);
    Object remove(String key);
    void clear();
    boolean isEmpty();
    boolean contains(String key);
}
