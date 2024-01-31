package com.epassi.filereader.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CacheIndex {
    private String checksum;
    private int k;
}
