package task_1;

public class NameFactory {
    private NameFactory() {
    }

    public static Namer getName(String name) throws IllegalAccessException {
        switch (name) {
            case "firstFirst":
                return new FirstFirst(name);
            case "lastFirst":
                return new LastFirst(name);
            default:
                throw new IllegalAccessException("Cannot instantiate class " + name);
        }
    }
}
