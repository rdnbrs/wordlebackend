package com.rdnbrs.wordle.dto;

import lombok.Data;

@Data
public class AddWordRequestDto {

    private String value;
    private int length;

}
