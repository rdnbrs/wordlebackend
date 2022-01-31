package com.rdnbrs.wordle.dto;

import lombok.Data;

@Data
public class CheckWordRequestDto {

    private long responseId;
    private int length;
    private String text;

}
