package com.assignment.markovchaindemo.controllers;

import com.assignment.markovchaindemo.models.InputData;
import com.assignment.markovchaindemo.services.FileContentTransformationService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileContentTransformationController {
    private final FileContentTransformationService fileContentTransformationService;

    public FileContentTransformationController(FileContentTransformationService fileContentTransformationService) {
        this.fileContentTransformationService = fileContentTransformationService;
    }

    @GetMapping("/")
    public String index() {
        return "upload";
    }

    /*
            I tried using @ModelAttribute with a POJO that I created in models folder, but that didn't work since
            it didn't have an @Id or @Entity annotations and I believe @ModelAttribute only works if the model
            has @Entity annotation with its id being annotated with @Id
     */
    @PostMapping("/upload")
    public String index(@RequestParam MultipartFile file,
                        @RequestParam int order,
                        @RequestParam String outputFilePath) {


        // Todo: remove InputData???
        InputData data = new InputData(file, order, outputFilePath);

        String fileContent = fileContentTransformationService.readFileContent(file);
        fileContentTransformationService.writeToFile(fileContent, outputFilePath);

        return "success";
    }
}
