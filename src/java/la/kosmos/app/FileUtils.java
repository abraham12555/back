/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package la.kosmos.app;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.NoSuchFileException;

/**
 *
 * @author elizabeth
 */
public class FileUtils {

    public static void saveFile(String path, byte[] content) throws IOException {
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(content);
            try (OutputStream outputStream = new FileOutputStream(path)) {
                int data;
                while ((data = byteArrayInputStream.read()) != -1) {
                    outputStream.write(data);
                }
            }
        } catch (IOException e) {
            throw e;
        }
    }

    public static boolean makeDirectory(String path) {
        File folder = new File(path);
        if (!folder.exists()) {
            return folder.mkdirs();
        } else {
            return Boolean.TRUE;
        }
    }

    public static void deleteFile(String path) throws NoSuchFileException, IOException {
        File file = new File(path);
        if (file.exists()) {
            file.delete();
        }
    }

    public static void deleteDirectory(String path) throws NoSuchFileException, IOException {
        File file = new File(path);
        File[] contents = file.listFiles();
        if (contents != null) {
            for (File f : contents) {
                f.delete();
            }
        }
        file.delete();
    }
}
