package com.crud.tasks.config;

import com.crud.tasks.trello.config.TrelloConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TrelloConfigTestSuite {
    @Autowired
    TrelloConfig config;

    @Test
    void configTest() {

        //when&then
        Assertions.assertNotNull(config.getTrelloApiEndpoint());
        Assertions.assertNotNull(config.getTrelloToken());
        Assertions.assertNotNull(config.getTrelloUser());
        Assertions.assertNotNull(config.getTrelloAppKey());
    }
}
