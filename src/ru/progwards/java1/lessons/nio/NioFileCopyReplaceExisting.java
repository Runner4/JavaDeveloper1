package ru.progwards.java1.lessons.nio;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class NioFileCopyReplaceExisting {
    final static String HOME_DIR = "C:/Projects/Academy/Java1";

    public static void main(String[] args) {
        Path dir1 = Paths.get(HOME_DIR);

        try {
            Path srcFile = dir1.resolve("file1.txt");
            Path dstFile = dir1.resolve("file1copy.txt");

            Files.copy(srcFile, dstFile, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
