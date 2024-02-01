package com.epassi.filereader.controller;

import com.epassi.filereader.dto.RequestDto;
import com.epassi.filereader.dto.ResponseDto;
import com.epassi.filereader.service.FileProcessService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/file-service/v1/")
public class FileController {

    @Autowired
    private FileProcessService fileProcessService;

    @PostMapping(path = "word-count/most-frequent", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseDto getCountOfTheFrequentWords(@RequestBody RequestDto request){
        log.info("getCountOfTheFrequentWords :  start {}", request);
        return fileProcessService.getCountOfTheFrequentWords(request);
    }
}
