package com.babobird.Toto.controller;

import com.babobird.Toto.entity.Task;
import com.babobird.Toto.entity.Toto;
import com.babobird.Toto.service.TotoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/totos")
public class TotoController {

    private final TotoService totoService;

    public TotoController(TotoService totoService) {
        this.totoService = totoService;
    }

    // 할 일 목록 조회 기능
    @GetMapping("")
    public String getAllTotos(Model model) {
        List<Toto> totos = totoService.getAllTotos();
        model.addAttribute("totos", totos);  // 데이터를 모델에 추가
        return "layouts/totos";  // "totos.html"로 리턴
    }

    @GetMapping("/new")
    public String showTaskForm(Model model) {
        model.addAttribute("todo", new Toto());
        return "task/inputList";  // 입력 폼 페이지로 이동
    }

    // 리스트 저장 메서드
    @PostMapping("/new")
    @ResponseBody  // AJAX 요청에 JSON 형태로 응답하기 위해 사용
    public Map<String, String> saveTask(@RequestBody Toto toto) {

        Map<String, String> response = new HashMap<>();

        try {
            totoService.saveToto(toto);  // 서비스에서 투두 항목 저장
            response.put("status", "success");
            response.put("redirectUrl", "/totos");  // 리다이렉트할 URL
        } catch (Exception e) {
            response.put("status", "error");
            response.put("redirectUrl", "/totos");  // 에러 발생 시에도 리다이렉트할 URL
        }

        return response;
    }

    // Toto 삭제 요청을 처리하는 메서드
    @DeleteMapping("/{totoNo}")
    @ResponseBody
    public Map<String, String> deleteToto(@PathVariable int totoNo) {
        Map<String, String> response = new HashMap<>();

        try {
            // 서비스에서 totoNo에 해당하는 투두 항목을 삭제
            totoService.deleteTotoById(totoNo);
            response.put("status", "success");
            response.put("redirectUrl", "/totos");  // 삭제 후 리다이렉트할 URL
        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", "삭제에 실패했습니다.");
            response.put("redirectUrl", "/totos");  // 에러 시 리다이렉트할 URL
        }

        return response;
    }

}

