package com.rdnbrs.wordle.service.impl;

import com.rdnbrs.wordle.dto.CheckWordRequestDto;
import com.rdnbrs.wordle.dto.CheckWordResponseDto;
import com.rdnbrs.wordle.dto.RandomWordReponse;
import com.rdnbrs.wordle.dto.RandomWordRequest;
import com.rdnbrs.wordle.entity.Word;
import com.rdnbrs.wordle.repository.WordRepository;
import com.rdnbrs.wordle.service.IWordService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class WordServiceImpl implements IWordService {

    private final WordRepository repository;

    public WordServiceImpl(WordRepository repository) {
        this.repository = repository;
    }

    @Override
    public RandomWordReponse getRandomWord(RandomWordRequest dto) {
        List<Word> wordList = repository.findByLengthEquals(dto.getLength());
        Random r = new Random();
        int randomIndex = r.nextInt(wordList.size());
        RandomWordReponse randomWordReponse = RandomWordReponse.builder().id(wordList.get(randomIndex).getId()).build();
        return randomWordReponse;
    }

    @Override
    public CheckWordResponseDto checkWord(CheckWordRequestDto dto) {
        CheckWordResponseDto responseDto = new CheckWordResponseDto();
        HashMap<Integer, Boolean> charControl = new HashMap<>();
        Word answer = repository.findById(dto.getResponseId()).get();
        if (answer.equals(dto.getText())) {
            //cevap bilindi
            responseDto.setAnswerStatus(true);
        } else {
            responseDto.setAnswerStatus(false);
            Optional<Word> inputCheck = repository.findByValueEqualsAndLengthEquals(dto.getText(), dto.getLength());
            if (inputCheck.isPresent()) {
                //cevap yanlış ama kelime var
                char[] answerChar = answer.getValue().toCharArray();
                char[] requestChar = dto.getText().toCharArray();

                for (int i = 0; i < dto.getLength(); i++) {
                    if (answerChar[i] == requestChar[i]) {
                        charControl.put(i, true);
                    } else {
                        charControl.put(i, false);
                    }
                }
            }
            responseDto.setCharControl(charControl);
        }
        return responseDto;
    }
}
