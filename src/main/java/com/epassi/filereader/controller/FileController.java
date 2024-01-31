package com.epassi.filereader.controller;

import com.epassi.filereader.service.FileProcessService;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/file-service/v1/")
public class FileController {

    @Autowired
    private FileProcessService fileProcessService;
    @PostMapping(path = "word-count/most-frequent", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getCountOfTheFrequentWords(@RequestBody String request){
        log.info("getCountOfTheFrequentWords :  start {}", request);
        return fileProcessService.getCountOfTheFrequentWords(request);
    }
}
