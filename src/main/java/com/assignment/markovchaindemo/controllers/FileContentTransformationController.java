package com.assignment.markovchaindemo.controllers;

import com.assignment.markovchaindemo.models.InputData;
import com.assignment.markovchaindemo.services.FileContentTransformationService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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
    public String index(@ModelAttribute InputData data) {
        String fileContent = fileContentTransformationService.readFileContent(data.getFile());
        fileContentTransformationService.writeToFile(fileContent, data.getOutputFilePath());

        return "success";
    }
}
