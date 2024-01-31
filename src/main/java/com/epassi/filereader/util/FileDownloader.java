package com.epassi.filereader.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

@Slf4j
public class FileDownloader {
    private static final int CONNECT_TIMEOUT = 60000;
    private static final int READ_TIMEOUT = 100000;
    public static void downloadFileFromHttpURL(String url, String fileNameWithPath) throws IOException {
        FileUtils.copyURLToFile(
                new URL(url),
                new File(fileNameWithPath),
                CONNECT_TIMEOUT,
                READ_TIMEOUT);
        log.info("downloadFileFromHttpURL :  url {}, path {}", url, fileNameWithPath);
    }
}
