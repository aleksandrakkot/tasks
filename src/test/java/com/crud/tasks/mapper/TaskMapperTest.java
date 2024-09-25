package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TaskMapperTest {
    @Autowired
    private TaskMapper taskMapper;

    @Test
    public void mapToTaskTest() {
        //Given
        TaskDto taskDto = new TaskDto(2L, "title", "content");

        //When
        Task task = taskMapper.mapToTask(taskDto);

        //Then
        assertEquals(2L, task.getId());
    }

    @Test
    public void mapToTaskDtoTest() {
        //Given
        Task task = new Task(2L, "title", "content");

        //When
        TaskDto taskDto = taskMapper.mapToTaskDto(task);

        //Then
        assertEquals("title", taskDto.getTitle());
    }

    @Test
    public void mapToTaskDtoListTest() {
        //Given
        Task task1 = new Task(3L, "title", "content");
        Task task2 = new Task(3L, "title2", "content2");
        List<Task> taskList = List.of(task1, task2);

        //When
        List<TaskDto> dtoList = taskMapper.mapToTaskDtoList(taskList);

        //Then
        assertEquals(2, dtoList.size());
        assertEquals("title", dtoList.get(0).getTitle());
    }
}