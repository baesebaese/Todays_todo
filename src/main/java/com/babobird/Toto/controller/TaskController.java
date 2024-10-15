package com.babobird.Toto.controller;


import com.babobird.Toto.entity.Task;
import com.babobird.Toto.entity.Toto;
import com.babobird.Toto.service.TaskService;
import com.babobird.Toto.service.TotoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/totos")
public class TaskController {

    private final TaskService taskService;
    private final TotoService totoService;  // TotoService를 주입받아 Toto 정보 사용

    public TaskController(TaskService taskService, TotoService totoService) {
        this.taskService = taskService;
        this.totoService = totoService;
    }


    @GetMapping("/{totoNo}/tasks")
    public String getTasksByTodoId(@PathVariable int totoNo, Model model) {
        List<Task> tasks = taskService.getTasksByTotoNo(totoNo);
        Toto toto = totoService.getTotoById(totoNo);

        if (toto == null) {
            // Toto 객체가 없을 경우 처리 (예: 에러 페이지나 리다이렉트)
            return "redirect:/totos";  // 에러 시 목록으로 리다이렉트
        }

        // 3. Model에 Task 목록과 Toto 이름 추가
        model.addAttribute("tasks", tasks);
        model.addAttribute("totoNm", toto.getTotoNm());

        return "layouts/tasks";  // templates/tasks.html로 반환
    }

    // 입력 폼을 보여주는 URL을 매핑
    @GetMapping("/{totoNo}/tasks/new")
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

    // Task 수정 폼을 보여주는 메서드 (taskNm과 modifyDt 수정)
    @GetMapping("/{totoNo}/tasks/{taskNo}/edit")
    public String showEditTaskForm(@PathVariable int totoNo, @PathVariable int taskNo, Model model) {
        Task task = taskService.getTaskById(taskNo, totoNo);

        if (task == null) {
            // 해당 Task가 없을 경우 처리
            return "redirect:/totos/" + totoNo + "/tasks";
        }

        model.addAttribute("task", task);
        return "task/inputForm";  // 수정 폼 페이지로 이동
    }

    @PostMapping("/{totoNo}/tasks/{taskNo}/edit")
    public String updateTaskNameAndDate(@PathVariable int totoNo, @PathVariable int taskNo, @ModelAttribute Task task) {
        try {
            Task existingTask = taskService.getTaskById(taskNo, totoNo);

            if (existingTask != null) {
                existingTask.setTaskNm(task.getTaskNm());  // Task 이름 수정
                existingTask.setModifyDt(LocalDateTime.now());  // 수정일자 변경
                taskService.saveTask(existingTask);
            }
        } catch (Exception e) {
            return "redirect:/totos/" + totoNo + "/tasks";
        }

        return "redirect:/totos/" + totoNo + "/tasks";
    }

    // Task 상태와 수정일자를 업데이트하는 메서드
    @PostMapping("/{totoNo}/tasks/{taskNo}/status")
    public String updateTaskStatus(@PathVariable int totoNo, @PathVariable int taskNo, @RequestBody Task task) {
        try {
            Task existingTask = taskService.getTaskById(taskNo, totoNo);

            if (existingTask != null) {
                existingTask.setStatus(task.getStatus());  // 상태 업데이트
                existingTask.setModifyDt(task.getModifyDt());  // 수정일자 변경
                taskService.saveTask(existingTask);
            }
        } catch (Exception e) {
            return "redirect:/totos/" + totoNo + "/tasks";
        }

        return "redirect:/totos/" + totoNo + "/tasks";
    }

    // Task 입력을 처리하는 POST 메서드
    @PostMapping("/tasks")
    public String saveTask(@RequestBody Task task) {
        try {
            taskService.saveTask(task);  // Task를 저장
        }
        catch (Exception e) {
            return "redirect:/totos";
        }
        int totoNo = task.getTotoNo();  // task에서 totoNo 값을 추출
        return "redirect:/totos/" + totoNo + "/tasks";
    }

    // Task 삭제 요청을 처리하는 메서드
    @DeleteMapping("/{totoNo}/tasks/{taskNo}")
    public String deleteTask(@PathVariable int totoNo, @PathVariable int taskNo) {
        taskService.deleteTaskById(taskNo, totoNo);
        return "redirect:/totos/" + totoNo + "/tasks";  // 삭제 후 해당 투두의 Task 목록 페이지로 리다이렉트
    }
}
