package optimisticlock;

public class FileWrapper {
    private String name;
    private String content;

    public FileWrapper() {
    }

    public FileWrapper(String name, String content) {
        this.name = name;
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "FileWrapper{" +
                "name='" + name + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
