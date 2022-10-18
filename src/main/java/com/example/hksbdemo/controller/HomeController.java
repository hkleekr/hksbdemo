package com.example.hksbdemo.controller;

import com.example.hksbdemo.domain.answer.AnswerResponseDto;
import com.example.hksbdemo.domain.answer.AnswerSaveRequestDto;
import com.example.hksbdemo.domain.question.question;
import com.example.hksbdemo.domain.question.questionSaveRequestDto;
import com.example.hksbdemo.repository.answerRepository;
import com.example.hksbdemo.repository.questionRepository;
import com.example.hksbdemo.service.answer.answerService;
import com.example.hksbdemo.service.question.questionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
public class HomeController {

    @Autowired
    private answerRepository answerRepository;
    @Autowired
    private questionRepository questionRepository;

    //    GET- cRud
    @GetMapping("/question/list")
    public String list(Model model, @PageableDefault(size = 8, sort="id", direction = Sort.Direction.DESC) Pageable pageable,
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
    @PostMapping("/question/answ")
    public ResponseEntity<AnswerResponseDto> AnswerSubmit(AnswerSaveRequestDto answerSaveRequestDto) {
        AnswerResponseDto answerResponseDto = new AnswerResponseDto();
        answerService.save(answerSaveRequestDto, answerResponseDto);
        return ResponseEntity.ok().body(answerResponseDto);
    }

    @GetMapping("/user/login")
    public String login() {
        return "login";
    }

    //    POST - Crud
    private final answerService answerService;
    private final questionService questionService;


    @ResponseBody
    @PutMapping("/question/answ/{id}")
    public ResponseEntity<Integer> update(@PathVariable Integer id, @RequestBody AnswerSaveRequestDto requestDto) {
        return ResponseEntity.ok().body(answerService.update(id, requestDto));
    }

    @ResponseBody
    @PutMapping("/question/ques/{id}")
    public String questionModiSubmit(@ModelAttribute question question) {
        questionRepository.save(question);
        return "redirect:list";
    }

//    @ResponseBody
//    @DeleteMapping("/question/ques/{id}")
//    public ResponseEntity<Integer> delete(@PathVariable Integer id, @RequestBody questionSaveRequestDto requestDto) {
//        return ResponseEntity.ok().body(questionService.delete((id, requestDto)));
//    }

    @ResponseBody
    @DeleteMapping("/question/answ/{id}")
    public ResponseEntity<Integer> delete(@PathVariable Integer id, @RequestBody AnswerSaveRequestDto requestDto) {
        return ResponseEntity.ok().body(answerService.delete(id, requestDto));
    }


//    게시판 추천기능을 위한 코드
//    boolean like = false; // 비로그인 유저라면 무조건 false;
//    if(user != null) {  // 로그인 했다면
//
//        Long member_id = user.getMember().getId()
//    }
//}
}