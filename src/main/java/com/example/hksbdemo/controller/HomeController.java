package com.example.hksbdemo.controller;

import com.example.hksbdemo.domain.answer.answerSaveRequestDto;
import com.example.hksbdemo.domain.question.question;
import com.example.hksbdemo.domain.question.questionSaveRequestDto;
import com.example.hksbdemo.repository.questionRepository;
import com.example.hksbdemo.service.answer.answerService;
import com.example.hksbdemo.service.question.questionService;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Controller
public class HomeController {

    @Autowired
    private questionRepository questionRepository;

    //    GET- cRud
    @GetMapping("/question/list")
    public String list(Model model, @PageableDefault(size = 8, sort="id") Pageable pageable,
                       @RequestParam(required = false, defaultValue = "") String searchText) {
//        Page<question> q = questionRepository.findAll(pageable);
        Page<question> q = questionRepository.findBySubjectContainingOrContentContaining(searchText, searchText, pageable);
        int startPage = Math.max(1, q.getPageable().getPageNumber() - 4);
        int endPage = Math.min(q.getTotalPages(), q.getPageable().getPageNumber() + 4);

        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("q", q);
        return "list";
    }

    @GetMapping("/question/answ")
    public String answ(@RequestParam Integer id, Model model) {
        question q = (question) questionService.getDetail(id);
        model.addAttribute("detail",q);
    return "answerdetail";
    }

    //답변저장하기
//    @PostMapping("/question/answ")
//    public String greetingSubmit(@ModelAttribute Greeting greeting, Model model) {
//        model.addAttribute("greeting", greeting);
//        return "result";
//    }

    @GetMapping("/question/ques")
    public String ques(Model model, @RequestParam(required = false) Integer id) {
        if(id == null){
            model.addAttribute("ques", new question());
        } else {
            question ques = questionRepository.findById(id).orElse(null);
            model.addAttribute("ques", ques);
        }
        return "questiondetail";
    }

    @PostMapping("/question/ques")
    public String questionSubmit(@Validated question question, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "questiondetail";
        }
        questionRepository.save(question);
        return "redirect:list";
    }


    @GetMapping("/user/login")
    public String login() {
        return "login";
    }

    //    POST - Crud
    private final answerService answerService;
    private final questionService questionService;

// 질문에 해당하는 답변이므로, 질문id의 page에 구현되어야 함
//    @ResponseBody
//    @PostMapping("/question/answ/")
//    public ResponseEntity<Integer> answ(@RequestBody answerSaveRequestDto requestDto) {
//        return ResponseEntity.ok().body(answerService.save(requestDto));
//    }

//    PUT - crUd: POST와 뭐가 다른건가...!
//    question 수정

    @ResponseBody
    @PutMapping("/question/answ/{id}")
    public ResponseEntity<Integer> update(@PathVariable Integer id, @RequestBody answerSaveRequestDto requestDto) {
        return ResponseEntity.ok().body(answerService.update(id, requestDto));
    }

    @ResponseBody
    @PutMapping("/question/ques/{id}")
    public String questionModiSubmit(@ModelAttribute question question) {
        questionRepository.save(question);
        return "redirect:list";
    }

//    public ResponseEntity<Integer> update(@PathVariable Integer id, @RequestBody questionSaveRequestDto requestDto) {
//        return ResponseEntity.ok().body(questionService.update(id, requestDto));
//    }

    //    만들긴 했는데,, 맞나? id만으로 지워지면 될 것 같은데, 내용이 있어야 지워지는 모양새
//    @ResponseBody
//    @DeleteMapping("/question/answ/{id}")
//    public ResponseEntity<Integer> delete(@PathVariable Integer id, @RequestBody answerSaveRequestDto requestDto) {
//        return ResponseEntity.ok().body(answerService.delete(id, requestDto));
//    }
//
    @ResponseBody
    @DeleteMapping("/question/ques/{id}")
    public ResponseEntity<Integer> delete(@PathVariable Integer id, @RequestBody questionSaveRequestDto requestDto) {
        return ResponseEntity.ok().body(questionService.delete(id, requestDto));
    }


//    게시판 추천기능을 위한 코드
//    boolean like = false; // 비로그인 유저라면 무조건 false;
//    if(user != null) {  // 로그인 했다면
//
//        Long member_id = user.getMember().getId()
//    }
//}
}