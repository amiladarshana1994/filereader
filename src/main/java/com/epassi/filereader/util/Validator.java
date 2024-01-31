package com.epassi.filereader.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.validator.routines.UrlValidator;

@Slf4j
public class Validator {
    public static void validateRequest(String link, int frequentWordCount){
        log.debug("validateRequest :  link {}, frequentWordCount {}", link, frequentWordCount);
        UrlValidator validator = new UrlValidator();
        if(!validator.isValid(link)){
            log.error("validateRequest : Invalid link to text file link {}, frequentWordCount {}", link, frequentWordCount);
            throw new IllegalArgumentException("Invalid link to text file!");
        }
        if(frequentWordCount == 0){
            log.error("validateRequest : Invalid frequent word count {}, frequentWordCount {}", link, frequentWordCount);
            throw new IllegalArgumentException("Invalid frequent word count!");
        }
    }
}
