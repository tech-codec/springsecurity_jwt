package org.sid.jwtspringsec.web;

import org.sid.jwtspringsec.dao.TaskRepository;
import org.sid.jwtspringsec.entity.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TestRestController {

    @Autowired
    private TaskRepository taskRepository;

    @GetMapping(path = "/tasks")
    public List<Task> listTask(){
        return taskRepository.findAll();
    }

    @PostMapping(path = "/tasks")
    public Task saveTask(@RequestBody Task t){
        return taskRepository.save(t);
    }
}
