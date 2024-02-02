package com.epassi.filereader.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class RequestDto {

    @NotNull(message = "Link to the text file must not be null")
    private String link;
    private int frequentWordCount;

    @Override
    public String toString(){
        return "{\"link\" : \"".concat(link).concat("\" , \"frequentWordCount\" : ").concat(frequentWordCount+" }");
    }
}
