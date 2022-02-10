package com.assignment.markovchaindemo.services;

import com.assignment.markovchaindemo.models.InputData;
import com.assignment.markovchaindemo.util.FileContentIO;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Service
public class FileContentTransformService {
    private String fileContent;

    private final Random rand = new Random();
    private final Map<String, List<String>> ngrams = new HashMap<>();

    private final FileContentIO fileContentIO;

    public FileContentTransformService(FileContentIO fileContentIO) {
        this.fileContentIO = fileContentIO;
    }

    private void setup(MultipartFile file, int order) {
        fileContent = fileContentIO.readFileContent(file);
        String[] words = fileContent.trim().split(" ");

        for (int i = 0; i < (words.length - order); i++) {
            StringBuilder word = new StringBuilder(words[i]);
            for (int j = i + 1; j < i + order; j++) {
                word.append(' ').append(words[j]);
            }

            String value;
            if(i + order < words.length) {
                value = words[i + order];
            } else {
                value = "";
            }

            if (!ngrams.containsKey(word.toString())) {
                List<String> list = new ArrayList<>();
                list.add(value);
                ngrams.put(word.toString(), list);
            } else {
                ngrams.get(word.toString()).add(value);
            }
        }
    }

    public void applyMarkov(InputData data) {
        setup(data.getFile(), data.getOrder());

        int count = 0;
        int randomIndex = rand.nextInt(ngrams.size());
        String prefix = (String) ngrams.keySet().toArray()[randomIndex];
        List<String> output = new ArrayList<>(Arrays.asList(prefix.split(" ")));

        while(true) {
            List<String> suffix = ngrams.get(prefix);
            if (suffix.size() == 1) {
                if (Objects.equals(suffix.get(0), "")) {
                    fileContent = output.stream().reduce("", (a, b) -> a + " " + b).trim();
                    fileContentIO.writeToFile(fileContent, data.getOutputFilePath());
                    break;
                }
                output.add(suffix.get(0));
            } else {
                randomIndex = rand.nextInt(suffix.size());
                output.add(suffix.get(randomIndex));
            }

            if (output.size() >= data.getTextLength()) {
                fileContent = output.stream().limit(data.getTextLength()).reduce("", (a, b) -> a + " " + b).trim();
                fileContentIO.writeToFile(fileContent, data.getOutputFilePath());
                break;
            }

            count++;
            prefix = output.stream().skip(count).limit(data.getOrder()).reduce("", (a, b) -> a + " " + b).trim();
        }
    }
}
