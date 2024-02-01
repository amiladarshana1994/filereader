package com.epassi.filereader.dto;

import lombok.*;

import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ResponseDto {
    private String message;
    private Map<String, Integer> result;
}
