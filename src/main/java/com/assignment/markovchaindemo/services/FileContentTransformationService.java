package com.assignment.markovchaindemo.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileContentTransformationService {
    public String readFileContent(MultipartFile file) {
        String fileContent = null;
        try {
            fileContent = new String(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return fileContent;
    }

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


