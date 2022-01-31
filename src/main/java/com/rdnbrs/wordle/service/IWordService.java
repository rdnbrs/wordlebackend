package com.rdnbrs.wordle.service;

import com.rdnbrs.wordle.dto.CheckWordRequestDto;
import com.rdnbrs.wordle.dto.CheckWordResponseDto;
import com.rdnbrs.wordle.dto.RandomWordReponse;
import com.rdnbrs.wordle.dto.RandomWordRequest;

public interface IWordService {
    RandomWordReponse getRandomWord(RandomWordRequest dto);

    CheckWordResponseDto checkWord(CheckWordRequestDto dto);
}
