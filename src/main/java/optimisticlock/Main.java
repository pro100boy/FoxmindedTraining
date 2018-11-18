package optimisticlock;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        FileHandler fileHandler1 = new FileHandler();
        FileWrapper fileWrapper1 = fileHandler1.readFile();

        FileHandler fileHandler2 = new FileHandler();
        FileWrapper fileWrapper2 = fileHandler2.readFile();

        System.out.println(fileWrapper1);
        System.out.println(fileWrapper2);

        fileWrapper1.setContent("new content 1");
        fileHandler1.saveFile(fileWrapper1);

        fileWrapper1.setContent("new content 2");
        fileHandler1.saveFile(fileWrapper2);
    }
}
