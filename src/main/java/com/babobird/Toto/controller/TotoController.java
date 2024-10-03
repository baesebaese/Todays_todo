package com.babobird.Toto.controller;

import com.babobird.Toto.entity.Toto;
import com.babobird.Toto.service.TotoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}