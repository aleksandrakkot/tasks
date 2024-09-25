package com.crud.tasks.controller;

import com.crud.tasks.domain.CreatedTrelloCardDto;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.trello.facade.TrelloFacade;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TrelloController.class)
class TrelloControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TrelloFacade trelloFacade;

    @Test
    void shouldFetchTrelloBoards() throws Exception {
        // Given
        List<TrelloBoardDto> trelloBoards = Arrays.asList(new TrelloBoardDto("1", "Test Board", Arrays.asList()));
        when(trelloFacade.fetchTrelloBoards()).thenReturn(trelloBoards);

        // When & Then
        mockMvc.perform(get("/v1/trello/boards")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value("1"))
                .andExpect(jsonPath("$[0].name").value("Test Board"));
    }

    @Test
    void shouldCreateTrelloCard() throws Exception {
        // Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("Test Card", "Test Description", "top", "1");
        CreatedTrelloCardDto createdTrelloCardDto = new CreatedTrelloCardDto("1", "Test Card", "http://test.com");

        when(trelloFacade.createCard(any(TrelloCardDto.class))).thenReturn(createdTrelloCardDto);

        // When & Then
        mockMvc.perform(post("/v1/trello/cards")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Test Card\",\"description\":\"Test Description\",\"pos\":\"top\",\"listId\":\"1\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.name").value("Test Card"))
                .andExpect(jsonPath("$.shortUrl").value("http://test.com"));
    }
}
