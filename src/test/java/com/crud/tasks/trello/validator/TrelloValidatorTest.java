package com.crud.tasks.trello.validator;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloList;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TrelloValidatorTest {

    TrelloValidator validator = new TrelloValidator();

    @Test
    void testValidateTrelloBoards() {
        //Given
        List<TrelloList> trelloLists = List.of(new TrelloList("1","test_list",false));
        List<TrelloBoard> trelloBoards = List.of(new TrelloBoard("1","test",trelloLists),
                new TrelloBoard("1","board",trelloLists ));

        //When
        List<TrelloBoard> validatorBoard = validator.validateTrelloBoards(trelloBoards);

        //Then
        assertEquals(1,validatorBoard.size());
    }
}
