package utils;

import java.io.File;

public class FileUtil {

    public static boolean isFileDownloaded(String expectedFileName) {
        File folder = new File(System.getProperty("user.dir"));
        //List the files on that folder
        File[] listOfFiles = folder.listFiles();
        boolean found = false;
        File f = null;
        //Look for the file in the files
        for (File listOfFile : listOfFiles) {
            if (listOfFile.isFile()) {
                String fileName = listOfFile.getName();
                System.out.println("File " + listOfFile.getName());
                if (fileName.equals(expectedFileName)) {
                    f = new File(fileName);
                    found = true;
                }
            }
        }
        f.deleteOnExit();
        return found;
    }
}
