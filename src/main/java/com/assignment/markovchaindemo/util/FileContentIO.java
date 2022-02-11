package com.assignment.markovchaindemo.util;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class FileContentIO {
    /**
     * Reads content that's in file
     *
     * @param file file that's selected by the user to have its text transformed
     *
     * @return a string that's all text in file
     */
    public String readFileContent(MultipartFile file) {
        String fileContent = null;
        try {
            fileContent = new String(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return fileContent;
    }

    /**
     * Writes fileContent to the file that's specified by the outputFilePath. If the
     * file doesn't exist there, it creates a file there and writes to it
     *
     * @param fileContent file content that's to be written to the file
     * @param outputFilePath output file path specified by the user
     */
    public void writeToFile(String fileContent, String outputFilePath) {
        try {
            Path path = Paths.get(outputFilePath);

            if(!Files.exists(path)) {
                Files.createFile(path);
            }

            PrintWriter writer = new PrintWriter(path.toAbsolutePath().toString(), StandardCharsets.UTF_8);
            writer.println(fileContent);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
