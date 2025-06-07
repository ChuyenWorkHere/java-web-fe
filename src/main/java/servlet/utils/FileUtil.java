package servlet.utils;

import org.apache.commons.fileupload.FileItem;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class FileUtil {

    public static String uploadImage(String path, FileItem item) throws IOException {
        String originalName = new File(item.getName()).getName();

        String extension = originalName.substring(originalName.lastIndexOf("."));
        String fileName = UUID.randomUUID() + extension;

        File folder = new File(path);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        File destinationFile = new File(folder, fileName);
        Files.copy(item.getInputStream(), destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

        return fileName;
    }
    public static boolean deleteImages(List<String> pathFiles) {
        boolean allDeleted = true;

        for (String path : pathFiles) {
            try {
                File file = new File(path);

                if (file.exists()) {
                    if (!file.delete()) {
                        allDeleted = false;
                    }
                } else {
                    allDeleted = false;
                }
            } catch (Exception e) {
                e.printStackTrace();
                allDeleted = false;
            }
        }

        return allDeleted;
    }
}
