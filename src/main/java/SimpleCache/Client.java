package SimpleCache;

import SimpleCache.cache.Cache;
import SimpleCache.cache.DemandCache;
import SimpleCache.cache.PrimedCache;
import SimpleCache.server.ServerImpl;

public class Client {
    public static void main(String[] args) {

        Cache cache = new PrimedCache(new DemandCache(new ServerImpl()));

        System.out.println(cache.get("k1"));
        System.out.println(cache.get("k2"));
        System.out.println(cache.get("k2"));
        cache.remove("k2");
        System.out.println(cache.get("k2"));

        cache.clear();

    }
}
