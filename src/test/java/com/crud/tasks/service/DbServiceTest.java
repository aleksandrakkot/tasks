package com.crud.tasks.service;

import com.crud.tasks.controller.TaskNotFoundException;
import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class DbServiceTest {

    @Mock
    private TaskRepository taskRepository;
    @InjectMocks
    private DbService dbService;

    @Test
    void getAllTasks() {
        //Given
        List<Task> tasks = List.of(new Task(1L,"Task 1","Content 1"),
                new Task(2L,"Task 2", "Content 2"));

        //When
        when(taskRepository.findAll()).thenReturn(tasks);
        List<Task> result = dbService.getAllTasks();

        //Then
        assertEquals(tasks,result);

    }

    @Test
    void saveTask() {
        //Given
        Task task = new Task(1L,"New Task","Content New");

        //When
        when(taskRepository.save(task)).thenReturn(task);
        Task result = dbService.saveTask(task);

        //Then
        assertEquals(task,result);
    }

    @Test
    void getTask() throws TaskNotFoundException {
        //Given
        Task task = new Task(1L,"Task 1", "Content");
        taskRepository.save(task);
        //When
        when(taskRepository.findById(task.getId())).thenReturn(Optional.of(task));
        Task result = dbService.getTask(task.getId());

        //Then
        assertEquals(task,result);

    }

    @Test
    void deleteTask() {
        //Given
        Task task = new Task(1L,"Task 1", "Content");
        Task task1 = taskRepository.save(task);

        //When
        taskRepository.delete(task1);

        //Then
        assertNull(task1);
    }
}