package com.epassi.filereader.util;

import com.epassi.filereader.dto.ResponseDto;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Slf4j
@Component
public class FileProcessor {
    Cache<String, ResponseDto> cache = Caffeine.newBuilder().build();
    private final Pattern CAPTURE_WORDS_REGEX = Pattern.compile("[^a-zA-Z]");

    public ResponseDto processFile(int k, String fileNameWithPath) {
        String fileChecksum = getChecksumOfFile(fileNameWithPath);
        String cacheIndex = fileChecksum.concat("_").concat(String.valueOf(k));
        ResponseDto ifPresent = cache.getIfPresent(cacheIndex);
        log.info("processFile : read file start {} {} fromCache {}", fileNameWithPath, k, ifPresent);
        return ifPresent != null ? ifPresent : processFileContinue(k, fileNameWithPath, cacheIndex);
    }

    private ResponseDto processFileContinue(int k, String fileNameWithPath, String cacheIndex) {
        Map<String, Integer> countMap = new HashMap<>();
        try (BufferedReader br = Files.newBufferedReader(Paths.get(fileNameWithPath))) {
            br.lines()
                    .flatMap(line -> CAPTURE_WORDS_REGEX.splitAsStream(line.toLowerCase()))
                    .filter(word -> !word.isEmpty())
                    .forEach(word -> countMap.merge(word, 1, Integer::sum));
        } catch (IOException e) {
            log.error("processFile : read file error", e);
            return ResponseDto.builder().message("Failed to process the file!").build();
        }

        Map<String, Integer> sortedMap = countMap.entrySet().stream()
                .sorted((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

        ResponseDto result = new ResponseDto("Success", collectResultMap(k, sortedMap));
        log.info("processFile : read file complete {} {} {}", fileNameWithPath, k, result);
        cache.put(cacheIndex, result);
        deleteFile(fileNameWithPath);
        return result;
    }

    private static Map<String, Integer> collectResultMap(int k, Map<String, Integer> sortedMap) {
        return sortedMap.entrySet().stream()
                .limit(k)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public void deleteFile(String fileNameWithPath){
        Path path = Paths.get(fileNameWithPath);
        try {
            Files.deleteIfExists(path);
            log.info("deleteFile : success {}", fileNameWithPath);
        }
        catch (IOException e) {
            log.error("deleteFile : error", e);
        }
    }

    public String getChecksumOfFile(String filePath){
        String checksum = "";
        try {
            byte[] data = Files.readAllBytes(Paths.get(filePath));
            byte[] hash = MessageDigest.getInstance("MD5").digest(data);
            checksum = new BigInteger(1, hash).toString(16);
            log.info("getChecksumOfFile : filePath {} checksum {}", filePath, checksum);
        }
        catch (Exception e){
            log.error("getChecksumOfFile : error", e);
        }
        return checksum;
    }
}
