package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TrelloMapperTest {
    @Autowired
    private TrelloMapper trelloMapper;

    @Test
    public void mapToBoardsTest() {
        //Given
        TrelloListDto list1 = new TrelloListDto("3", "three", true);
        TrelloListDto list2 = new TrelloListDto("4", "four", false);
        List<TrelloListDto> lists = new ArrayList<>();
        lists.add(list1);
        lists.add(list2);
        TrelloBoardDto trelloBoardDto1 = new TrelloBoardDto("1", "One", lists);
        TrelloBoardDto trelloBoardDto2 = new TrelloBoardDto("2", "Two", lists);
        List<TrelloBoardDto> trelloBoardDtoList = new ArrayList<>();
        trelloBoardDtoList.add(trelloBoardDto1);
        trelloBoardDtoList.add(trelloBoardDto2);

        //When
        List<TrelloBoard> resultList = trelloMapper.mapToBoards(trelloBoardDtoList);

        //Then
        assertEquals(2, resultList.size());
    }

    @Test
    public void mapToBoardsDtoTest() {
        //Given
        TrelloList list1 = new TrelloList("3", "three", true);
        TrelloList list2 = new TrelloList("4", "four", true);
        List<TrelloList> lists = new ArrayList<>();
        lists.add(list1);
        lists.add(list2);
        TrelloBoard board1 = new TrelloBoard("1", "one", lists);
        List<TrelloBoard> boards = new ArrayList<>();
        boards.add(board1);

        //When
        List<TrelloBoardDto> resultList = trelloMapper.mapToBoardsDto(boards);

        //Then
        assertEquals(1, resultList.size());
        assertEquals("one", resultList.get(0).getName());
    }

    @Test
    public void mapToListTest() {
        //Given
        TrelloListDto list1 = new TrelloListDto("3", "three", true);
        TrelloListDto list2 = new TrelloListDto("4", "four", true);
        List<TrelloListDto> lists = new ArrayList<>();
        lists.add(list1);
        lists.add(list2);

        //When
        List<TrelloList> resultList = trelloMapper.mapToList(lists);

        //Then
        assertEquals(2, resultList.size());
        assertEquals("three", resultList.get(0).getName());
    }

    @Test
    public void mapToListDtoTest() {
        //Given
        TrelloList list1 = new TrelloList("3", "three", true);
        TrelloList list2 = new TrelloList("4", "four", true);
        List<TrelloList> lists = new ArrayList<>();
        lists.add(list1);
        lists.add(list2);

        //When
        List<TrelloListDto> resultList = trelloMapper.mapToListDto(lists);

        //Then
        assertEquals(2, resultList.size());
        assertEquals("three", resultList.get(0).getName());
    }

    @Test
    public void mapToCardDtoTest() {
        //Given
        TrelloCard trelloCard = new TrelloCard("card1", "description1", "10", "1");

        //When
        TrelloCardDto resultCard = trelloMapper.mapToCardDto(trelloCard);

        //Then
        assertEquals("card1", resultCard.getName());
    }

    @Test
    public void mapToCardTest() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("card1", "description1", "10", "1");

        //When
        TrelloCard resultCard = trelloMapper.mapToCard(trelloCardDto);

        //Then
        assertEquals("card1", resultCard.getName());
    }
}