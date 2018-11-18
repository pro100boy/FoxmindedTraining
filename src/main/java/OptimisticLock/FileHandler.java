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
    private final static String filePath = "/home/pavel";
    private final static String fileNamePrefix = "file";
    private final static String fileExtension = "txt";

    private Path makeFilePath() {
        return Paths.get(filePath + "/" + fileNamePrefix + version + "." + fileExtension);
    }

    private Path makeFilePath(FileWrapper wrapper) {
        return Paths.get(filePath + "/" + wrapper.getName());
    }

    public FileWrapper readFile() throws IOException {
        Path path = makeFilePath();

        return new FileWrapper(
                path.getFileName().toString(),
                Files.readAllLines(path).stream().collect(Collectors.joining(System.lineSeparator()))
        );
    }

    public void saveFile(FileWrapper wrapper) throws IOException {
        Path path = makeFilePath(wrapper);
        File file = path.toFile();
        if (file.isFile())
        {
            Files.write(path, Arrays.asList(wrapper.getContent().split(System.lineSeparator())));
            ++version;
            Path target = makeFilePath();
            Files.move(path, target, StandardCopyOption.ATOMIC_MOVE);
            System.out.println("File " + wrapper.getName() + " was wrote");
        }
        else
        {
            throw new OptimisticLockException("File " + wrapper.getName() + " already has changed");
        }
    }
}
