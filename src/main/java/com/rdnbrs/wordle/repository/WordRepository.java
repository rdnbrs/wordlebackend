package com.rdnbrs.wordle.repository;

import com.rdnbrs.wordle.entity.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WordRepository extends JpaRepository<Word, Long> {

    List<Word> findByLengthEquals(int length);

    Optional<Word> findByValueEqualsAndLengthEquals(String text, int length);

}
