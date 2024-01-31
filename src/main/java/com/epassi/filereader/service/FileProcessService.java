package com.epassi.filereader.service;

import org.json.JSONObject;
import org.springframework.stereotype.Repository;

@Repository
public interface FileProcessService {
    String getCountOfTheFrequentWords(String request);
}
