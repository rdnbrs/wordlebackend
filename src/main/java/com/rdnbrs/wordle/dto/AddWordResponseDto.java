package com.rdnbrs.wordle.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddWordResponseDto {

    private Long id;
    private String value;
    private int length;

}
