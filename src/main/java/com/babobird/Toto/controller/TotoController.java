package com.babobird.Toto.controller;

import com.babobird.Toto.entity.Task;
import com.babobird.Toto.entity.Toto;
import com.babobird.Toto.service.TotoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class TotoController {

    private final TotoService totoService;

    public TotoController(TotoService totoService) {
        this.totoService = totoService;
    }

    // 할 일 목록 조회 기능
    @GetMapping("/totos")
    public String getAllTotos(Model model) {
        List<Toto> totos = totoService.getAllTotos();
        model.addAttribute("totos", totos);  // 데이터를 모델에 추가
        return "layouts/totos";  // "totos.html"로 리턴
    }

    @GetMapping("/totos/new")
    public String showTaskForm(Model model) {

        return "task/inputForm";  // 입력 폼 페이지로 이동
    }

    // 리스트 저장 메서드
    @PostMapping("/new")
    public String saveToto(@ModelAttribute("toto") Toto toto, RedirectAttributes redirectAttributes) {
        totoService.saveToto(toto);  // 서비스 레이어에서 저장 처리
        redirectAttributes.addFlashAttribute("message", "리스트 저장이 완료되었습니다.");
        return "redirect:/totos";  // 저장 후 리스트 페이지로 리다이렉트
    }

}

