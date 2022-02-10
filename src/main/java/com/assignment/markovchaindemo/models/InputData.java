package com.assignment.markovchaindemo.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
public class InputData {
    private final MultipartFile file;
    private final int order;
    private final int textLength;
    private final String outputFilePath;
}
