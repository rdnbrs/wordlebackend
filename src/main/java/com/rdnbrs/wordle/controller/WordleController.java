package com.rdnbrs.wordle.controller;

import com.rdnbrs.wordle.dto.CheckWordRequestDto;
import com.rdnbrs.wordle.dto.CheckWordResponseDto;
import com.rdnbrs.wordle.dto.RandomWordReponse;
import com.rdnbrs.wordle.dto.RandomWordRequest;
import com.rdnbrs.wordle.service.IWordService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wordle")
public class WordleController {

    private final IWordService service;

    public WordleController(IWordService service){
        this.service = service;
    }

    @PostMapping("/getRandomWord")
    public ResponseEntity<RandomWordReponse> getRandomWord(@RequestBody RandomWordRequest dto){
        return new ResponseEntity<RandomWordReponse>(service.getRandomWord(dto), HttpStatus.OK);
    }

    @PostMapping("/checkWord")
    public ResponseEntity<CheckWordResponseDto> checkWord(@RequestBody CheckWordRequestDto dto){
        return new ResponseEntity<CheckWordResponseDto>(service.checkWord(dto), HttpStatus.OK);
    }

}
