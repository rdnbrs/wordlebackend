package com.rdnbrs.wordle.service.impl;

import com.rdnbrs.wordle.dto.*;
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
        if (answer.getValue().equals(dto.getText())) {
            //cevap bilindi
            responseDto.setAnswerStatus(true);
        } else {
            responseDto.setAnswerStatus(false);
            Optional<Word> inputCheck = repository.findByValueEqualsAndLengthEquals(dto.getText(), dto.getLength());
            if (inputCheck.isPresent()) {
                //cevap yanlış ama kelime var
                for (int i = 0; i < dto.getLength(); i++) {
                    if (answer.getValue().charAt(i) == dto.getText().charAt(i)) {
                        charControl.put(i, true);
                    } else {
                        if (answer.getValue().indexOf(dto.getText().charAt(i)) != -1)
                            charControl.put(i, false);
                    }
                }
            }
            else{
                //yanlış kelime
                charControl.put(-1, true);
            }
            responseDto.setCharControl(charControl);
        }
        return responseDto;
    }

    @Override
    public AddWordResponseDto addWord(AddWordRequestDto dto) {
        Word newWord = Word.builder().value(dto.getValue()).length(dto.getLength()).build();
        newWord = repository.save(newWord);
        return AddWordResponseDto.builder().id(newWord.getId()).value(newWord.getValue()).length(newWord.getLength()).build();
    }
}
