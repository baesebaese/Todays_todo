package com.babobird.Toto.controller;


import com.babobird.Toto.entity.Task;
import com.babobird.Toto.entity.Toto;
import com.babobird.Toto.service.TaskService;
import com.babobird.Toto.service.TotoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TaskController {

    private final TaskService taskService;
    private final TotoService totoService;  // TotoService를 주입받아 Toto 정보 사용

    public TaskController(TaskService taskService, TotoService totoService) {
        this.taskService = taskService;
        this.totoService = totoService;
    }


    @GetMapping("/totos/{totoNo}/tasks")
    public String getTasksByTodoId(@PathVariable int totoNo, Model model) {
        List<Task> tasks = taskService.getTasksByTotoNo(totoNo);
        model.addAttribute("tasks", tasks);
        return "layouts/tasks";  // templates/tasks.html로 반환
    }

    // 입력 폼을 보여주는 URL을 매핑
    @GetMapping("/totos/{totoNo}/tasks/new")
    public String showTaskForm(@PathVariable int totoNo, Model model) {
        // 선택된 totoNo에 해당하는 Todo 객체를 가져옴
        Toto toto = totoService.getTotoById(totoNo);

        if (toto == null) {
            // 해당하는 Toto가 없을 경우 처리
            return "redirect:/totos";
        }

        Task task = new Task();  // 새 Task 객체 생성
        task.setToto(toto);  // 해당하는 Toto 객체를 Task에 설정

        // Task 객체와 totoNo 값을 모델에 추가
        model.addAttribute("task", task);
        model.addAttribute("totoNo", totoNo);

        return "task/inputForm";  // 입력 폼 페이지로 이동
    }


    // Task 입력을 처리하는 POST 메서드
    @PostMapping("/totos/tasks")
    public String saveTask(@ModelAttribute Task task) {
        taskService.saveTask(task);  // Task를 저장
        return "redirect:/tasks";  // 저장 후 Task 목록 페이지로 리다이렉트
    }

    // Task 삭제 요청을 처리하는 메서드
    @DeleteMapping("/totos/{totoNo}/tasks/{taskNo}")
    public String deleteTask(@PathVariable int totoNo, @PathVariable int taskNo) {
        taskService.deleteTaskById(taskNo, totoNo);
        return "redirect:/totos/" + totoNo + "/tasks";  // 삭제 후 해당 투두의 Task 목록 페이지로 리다이렉트
    }
}
