package com.assignment.markovchaindemo.controllers;

import com.assignment.markovchaindemo.models.InputData;
import com.assignment.markovchaindemo.services.FileContentTransformService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FileContentTransformController {
    private final FileContentTransformService fileContentTransformService;

    public FileContentTransformController(FileContentTransformService fileContentTransformService) {
        this.fileContentTransformService = fileContentTransformService;
    }

    @GetMapping("/")
    public String index() {
        return "upload";
    }

    @PostMapping("/upload")
    public String transformText(@ModelAttribute InputData data) {
        fileContentTransformService.applyMarkov(data);

        return "success";
    }
}
