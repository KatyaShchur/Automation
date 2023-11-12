package utils;

import java.io.File;
import java.util.Arrays;
import java.util.Objects;

public class FileUtil {

    public static boolean isFileDownloaded(String expectedFileName) {
        File folder = new File(System.getProperty("user.dir"));
        return Arrays.stream(Objects.requireNonNull(folder.listFiles()))
                .filter(file -> file.isFile() && file.getName().equals(expectedFileName))
                .peek(File::deleteOnExit)
                .findFirst()
                .isPresent();
    }
}
