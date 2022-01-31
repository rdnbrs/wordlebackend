package com.rdnbrs.wordle.service;

import com.rdnbrs.wordle.dto.*;

public interface IWordService {
    RandomWordReponse getRandomWord(RandomWordRequest dto);

    CheckWordResponseDto checkWord(CheckWordRequestDto dto);

    AddWordResponseDto addWord(AddWordRequestDto dto);
}
