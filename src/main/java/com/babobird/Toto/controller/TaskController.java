package com.babobird.Toto.controller;


import com.babobird.Toto.entity.Task;
import com.babobird.Toto.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/totos/{totoNo}/tasks")
    public String getTasksByTodoId(@PathVariable int totoNo, Model model) {
        List<Task> tasks = taskService.getTasksByTotoNo(totoNo);
        model.addAttribute("tasks", tasks);
        return "layouts/tasks";  // templates/tasks.html로 반환
    }
}
