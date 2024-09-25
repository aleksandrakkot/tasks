package com.crud.tasks.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(GlobalHttpErrorHandler.class)
class GlobalHttpErrorHandlerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskController taskController;

    @Test
    void shouldHandleTaskNotFoundException() throws Exception {
        // Given:
        Mockito.when(taskController.getTask(anyLong())).thenThrow(new TaskNotFoundException());

        // When & Then:
        mockMvc.perform(get("/v1/tasks/1"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Task with given id doesn't exist"));
    }
}
