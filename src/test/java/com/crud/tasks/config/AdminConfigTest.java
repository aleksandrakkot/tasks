package com.crud.tasks.config;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class AdminConfigTest {

    @Autowired
    private AdminConfig adminConfig;

    @Test
    public void shouldReturnAdminMail() {
        // given
        String expectedAdminMail = "aleksandrak.kot@gmail.com";

        // when
        String adminMail = adminConfig.getAdminMail();

        // then
        assertEquals(expectedAdminMail, adminMail);
    }
}
