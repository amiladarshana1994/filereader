package com.epassi.filereader.service;

import com.epassi.filereader.util.FileDownloader;
import com.epassi.filereader.util.FileProcessor;
import com.epassi.filereader.util.Validator;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
public class FileProcessServiceImpl implements FileProcessService{
    @Autowired
    private FileProcessor fileProcessor;
    @Override
    public String getCountOfTheFrequentWords(String request) {
        JSONObject requestObj = new JSONObject(request);
        String link = requestObj.getString("link");
        int frequentWordCount = requestObj.getInt("frequentWordCount");
        Validator.validateRequest(link, frequentWordCount);
        String tmpFile = System.getProperty("user.home").concat("/").concat(UUID.randomUUID().toString().concat(".txt"));
        try {
            FileDownloader.downloadFileFromHttpURL(link, tmpFile);
            return fileProcessor.processFile(frequentWordCount, tmpFile).toString();
        }
        catch (Exception e){
            log.error("getCountOfTheFrequentWords : File download failed {}, error {}", link, e.getMessage());
            throw new RuntimeException("File download failed!");
        }
    }
}
