package SimpleCache;

import SimpleCache.cache.*;
import SimpleCache.model.Obj1;
import SimpleCache.server.ServerImpl;

public class Client {
    public static void main(String[] args) {

        Cache cache =  new CollectorCache(new StatisticCache(new PrimedCache(new DemandCache(new ServerImpl()))));

        cache.put("k3", new Obj1("descr3"));
        System.out.println(cache.get("k1"));
        System.out.println(cache.get("k2"));
        System.out.println(cache.get("k2"));
        cache.remove("k2");
        System.out.println(cache.get("k2"));
        cache.put("k3", new Obj1("descr3"));
        System.out.println("oldest");
        System.out.println(((CollectorCache) cache).getOldestObject());

        cache.clear();
    }
}
