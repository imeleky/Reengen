package testUtil;

import java.io.File;

/**
 * Created by melek on 15.02.2017.
 */
public class FileOperation {

    public FileOperation() {
    }

    public File getLatestFilefromDir(String dirPath){
        File dir = new File(dirPath);
        File[] files = dir.listFiles();
        if (files == null || files.length == 0) {
            return null;
        }

        File lastModifiedFile = files[0];
        for (int i = 1; i < files.length; i++) {
            if (lastModifiedFile.lastModified() < files[i].lastModified()) {
                lastModifiedFile = files[i];
            }
        }
        return lastModifiedFile;
    }
}
