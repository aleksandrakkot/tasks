package com.crud.tasks.trello;

import com.crud.tasks.trello.config.TrelloConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestPropertySource(properties = {
        "trello.api.endpoint.prod=https://api.trello.com/1",
        "trello.app.key=testKey",
        "trello.app.token=testToken",
        "trello.username=testUser"
})
class TrelloConfigTest {

    @Autowired
    private TrelloConfig trelloConfig;

    @Test
    void shouldLoadTrelloConfig() {
        // When & Then
        assertEquals("https://api.trello.com/1", trelloConfig.getTrelloApiEndpoint());
        assertEquals("testKey", trelloConfig.getTrelloAppKey());
        assertEquals("testToken", trelloConfig.getTrelloToken());
        assertEquals("testUser", trelloConfig.getTrelloUser());
    }
}
