package com.epassi.filereader.service;

import com.epassi.filereader.dto.RequestDto;
import com.epassi.filereader.dto.ResponseDto;
import org.springframework.stereotype.Repository;

@Repository
public interface FileProcessService {
    ResponseDto getCountOfTheFrequentWords(RequestDto request);
}
