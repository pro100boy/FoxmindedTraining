package OptimisticLock;

import javax.persistence.OptimisticLockException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.stream.Collectors;

public class FileHandler {
    private int version;
    private final static String filePath = "/home/user";
    private final static String fileNamePrefix = "file";
    private final static String fileExtension = "txt";

    private String makeFilePath() {
        return filePath + "/" + fileNamePrefix + version + "." + fileExtension;
    }

    private String makeFilePath(FileWrapper wrapper) {
        return filePath + "/" + wrapper.getName();
    }

    public FileWrapper readFile() throws IOException {
        Path path = Paths.get(makeFilePath());

        return new FileWrapper(
                path.getFileName().toString(),
                Files.readAllLines(path).stream().collect(Collectors.joining(System.lineSeparator()))
        );
    }

    public void saveFile(FileWrapper wrapper) throws IOException {
        Path path = Paths.get(makeFilePath(wrapper));
        File file = path.toFile();
        if (file.isFile())
        {
            Files.write(path, Arrays.asList(wrapper.getContent().split(System.lineSeparator())));
            version += 1;
            Path target = Paths.get(makeFilePath());
            Files.move(path, target, StandardCopyOption.ATOMIC_MOVE);
            System.out.println("File " + wrapper.getName() + " was wrote");
        }
        else
        {
            System.err.println("File " + wrapper.getName() + " don't wrote");
            throw new OptimisticLockException();
        }
    }

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
