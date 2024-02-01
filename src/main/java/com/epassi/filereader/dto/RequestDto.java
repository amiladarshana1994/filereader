package com.epassi.filereader.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class RequestDto {
    private String link;
    private int frequentWordCount;

    @Override
    public String toString(){
        return "{\"link\" : \"".concat(link).concat("\" , \"frequentWordCount\" : ").concat(frequentWordCount+" }");
    }
}
