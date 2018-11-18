package simplecache.server;

import simplecache.model.Obj1;
import simplecache.model.Obj2;

public class ServerImpl implements Server {
    @Override
    public Object getObject(String key) {
        if (key.equals("k1")) return new Obj1("object 1");
        if (key.equals("k2")) return new Obj2("object 2");
        return null;
    }
}
