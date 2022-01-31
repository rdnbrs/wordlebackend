package com.rdnbrs.wordle.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CheckWordResponseDto {

    private boolean answerStatus;
    private HashMap<Integer, Boolean> charControl;

}
