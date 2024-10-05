package com.babobird.Toto.controller;


import com.babobird.Toto.entity.Task;
import com.babobird.Toto.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    // Task 입력을 처리하는 POST 메서드
    @PostMapping("/tasks")
    public String saveTask(@ModelAttribute Task task) {
        taskService.saveTask(task);  // Task를 저장
        return "redirect:/tasks";  // 저장 후 Task 목록 페이지로 리다이렉트
    }
}
