package SimpleCache;

import SimpleCache.cache.DemandCacheImpl;
import SimpleCache.server.ServerImpl;

public class Client {
    public static void main(String[] args) {

        DemandCacheImpl cache = new DemandCacheImpl(new ServerImpl());

        System.out.println(cache.get("k1"));
        System.out.println(cache.get("k2"));
        System.out.println(cache.get("k2"));
        cache.remove("k2");
        System.out.println(cache.get("k2"));

        cache.clear();

    }
}
