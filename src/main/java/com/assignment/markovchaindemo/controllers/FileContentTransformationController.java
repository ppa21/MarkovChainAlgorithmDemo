package com.assignment.markovchaindemo.controllers;

import com.assignment.markovchaindemo.services.FileContentTransformationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class FileContentTransformationController {
    private final FileContentTransformationService fileContentTransformationService;

    public FileContentTransformationController(FileContentTransformationService fileContentTransformationService) {
        this.fileContentTransformationService = fileContentTransformationService;
    }

    /*
            I tried using @ModelAttribute with a POJO that I created in models folder, but that didn't work since
            it didn't have an @Id or @Entity annotations and I believe @ModelAttribute only works if the model
            has @Entity annotation with its id being annotated with @Id
     */
    // Todo: create a class with these 3 fields
    @PostMapping("/")
    public String index(@RequestParam("file") MultipartFile file,
                        @RequestParam("order") int order,
                        @RequestParam("outputFilePath") String outputFilePath) {

        String content = null;
        try {
            content = new String(file.getBytes());

            Path path = Paths.get(outputFilePath);

            if(!Files.exists(path)) {
                Files.createFile(path);
            }

            PrintWriter writer = new PrintWriter(path.toAbsolutePath().toString(), StandardCharsets.UTF_8);
            writer.println(content);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "successfully applied markov chain algorithm and the output file has been created";
    }
}
