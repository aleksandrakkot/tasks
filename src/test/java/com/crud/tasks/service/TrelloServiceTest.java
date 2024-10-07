package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.CreatedTrelloCardDto;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.trello.client.TrelloClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class TrelloServiceTest {

    @Mock
    private TrelloClient trelloClient;

    @Mock
    private SimpleEmailService emailService;

    @Mock
    private AdminConfig adminConfig;

    private TrelloService trelloService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        trelloService = new TrelloService(trelloClient, emailService, adminConfig);
    }

    @Test
    void fetchTrelloBoards() {
        // Given
        List<TrelloBoardDto> expectedBoards = Collections.singletonList(new TrelloBoardDto("board1", "Board 1", Collections.emptyList()));
        when(trelloClient.getTrelloBoards()).thenReturn(expectedBoards);

        // When
        List<TrelloBoardDto> actualBoards = trelloService.fetchTrelloBoards();

        // Then
        assertEquals(expectedBoards, actualBoards);
    }

    @Test
    void createTrelloCard() {
        // Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("Card 1", "Card Description", "top", "list1");
        CreatedTrelloCardDto expectedCard = new CreatedTrelloCardDto("card1", "Card 1", "http://test.com");
        when(trelloClient.createNewCard(any(TrelloCardDto.class))).thenReturn(expectedCard);
        when(adminConfig.getAdminMail()).thenReturn("admin@test.com");

        // When
        CreatedTrelloCardDto actualCard = trelloService.createTrelloCard(trelloCardDto);

        // Then
        assertEquals(expectedCard, actualCard);
    }
}