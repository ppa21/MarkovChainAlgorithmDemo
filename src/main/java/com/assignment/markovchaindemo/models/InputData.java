package com.assignment.markovchaindemo.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class InputData {
    private final MultipartFile file;
    private final int order;
    private final String outputFilePath;

    private static String FILE_CONTENT;

    public InputData(MultipartFile file, int order, String outputFilePath) {
        this.file = file;
        this.order = order;
        this.outputFilePath = outputFilePath;
    }
}
