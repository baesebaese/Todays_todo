package com.babobird.Toto.controller;

import com.babobird.Toto.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import com.babobird.Toto.dto.TaskFormDto;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @GetMapping(value = "/task/new")
    public String taskForm(Model model){
        model.addAttribute("taskFormDto", new TaskFormDto());
        return "task/taskForm";
    }

    @PostMapping("/task/new")
    public String taskNew(@Valid TaskFormDto taskFormDto, BindingResult bindingResult, Model model){

        if(bindingResult.hasErrors()){
            return "task/taskForm";
        }

        try{
            taskService.saveTask(taskFormDto);
        } catch (Exception e){
            model.addAttribute("errorMessage", "task 등록 중 에러가 발생했습니다. ");
            return "task/taskForm";
        }

        return "redirect:/";
    }

    @GetMapping(value = "/task/{taskNo}")
    public String taskDtl(@PathVariable("taskNo") int taskNo, Model model){

        try{
            TaskFormDto taskFormDto = taskService.getTaskDtl(taskNo);
            model.addAttribute("taskFormDto", taskFormDto);
        } catch (Exception e){
            model.addAttribute("errorMessage", "존재하지 않는 task 입니다.");
            model.addAttribute("taskFormDto", new TaskFormDto());
            return "task/taskForm";
        }

        return "task/taskForm";
    }

    @PostMapping(value = "/task/{taskNo}")
    public String taskUpdate(@Valid TaskFormDto taskFormDto, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            return "task/taskForm";
        }

        try{
            taskService.updateTask(taskFormDto);
        }catch (Exception e){
            model.addAttribute("erroMessage", "task 수정 중 에러가 발생했습니다.");
            return "task/taskForm";
        }

        return "redirect:/";
    }

}