package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringJUnitWebConfig
@WebMvcTest(TaskController.class)
class TaskControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DbService service;

    @MockBean
    private TaskMapper taskMapper;

    @Test
    void shouldFetchTasks() throws Exception {
        // Given
        List<TaskDto> taskDtos = Arrays.asList(new TaskDto(1L, "Test task", "Test content"));
        when(service.getAllTasks()).thenReturn(Arrays.asList(new Task(1L, "Test task", "Test content")));
        when(taskMapper.mapToTaskDtoList(any())).thenReturn(taskDtos);

        // When & Then
        mockMvc.perform(get("/v1/tasks")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].title").value("Test task"))
                .andExpect(jsonPath("$[0].content").value("Test content"));
    }
    @Test
    void shouldGetTask() throws Exception {
        //Given
        Task task = new Task(12L, "title", "content");
        TaskDto taskDto = new TaskDto(12L, "title", "content");
        when(service.getTask(task.getId())).thenReturn(task);
        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/tasks/12")
                        .contentType(MediaType.APPLICATION_JSON))

                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(12)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.is("title")));
    }


    @Test
    void shouldDeleteTask() throws Exception {
        // Given
        Mockito.doNothing().when(service).deleteTask(anyLong());

        // When & Then
        mockMvc.perform(delete("/v1/tasks/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    void shouldUpdateTask() throws Exception {
        // Given
        Task task = new Task(1L, "Updated task", "Updated content");
        TaskDto taskDto = new TaskDto(1L, "Updated task", "Updated content");

        when(taskMapper.mapToTask(any(TaskDto.class))).thenReturn(task);
        when(service.saveTask(any(Task.class))).thenReturn(task);
        when(taskMapper.mapToTaskDto(any(Task.class))).thenReturn(taskDto);

        // When & Then
        mockMvc.perform(put("/v1/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"title\":\"Updated task\",\"content\":\"Updated content\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("Updated task"))
                .andExpect(jsonPath("$.content").value("Updated content"));
    }

    @Test
    void shouldCreateTask() throws Exception {
        // Given
        Task task = new Task(1L, "New task", "Task content");
        TaskDto taskDto = new TaskDto(1L, "New task", "Task content");

        when(taskMapper.mapToTask(any(TaskDto.class))).thenReturn(task);
        when(service.saveTask(any(Task.class))).thenReturn(task);

        // When & Then
        mockMvc.perform(post("/v1/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"New task\",\"content\":\"Task content\"}"))
                .andExpect(status().isOk());
    }

}

