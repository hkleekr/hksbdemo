package com.example.hksbdemo.controller;

import com.example.hksbdemo.domain.answer.AnswerResponseDto;
import com.example.hksbdemo.domain.answer.AnswerSaveRequestDto;
import com.example.hksbdemo.domain.question.QuestionResponseDto;
import com.example.hksbdemo.domain.question.question;
import com.example.hksbdemo.domain.question.QuestionSaveRequestDto;
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
    private ResponseEntity<QuestionResponseDto> Boolean;

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
    // 스프링 내장함수 "ResponseEntity<>"의 <>안 타입영역에 불러올 DTO를 넣는다. "AnswerSubmit()"에 요청 DTO를 담고,
    public ResponseEntity<AnswerResponseDto> AnswerSubmit(AnswerSaveRequestDto answerSaveRequestDto) {
        AnswerResponseDto answerResponseDto = new AnswerResponseDto(); // "AnswerResponseDto"의 객체를 생성
        answerService.save(answerSaveRequestDto, answerResponseDto); // answerService.java의 "save()"를 통해 인자를 주입
        return ResponseEntity.ok().body(answerResponseDto); // "ResponseEntity.ok()" 잘 실행 되었으면, body의 "answerResponseDto"를 리턴하시오
    }

    // question 삭제
    public ResponseEntity<QuestionResponseDto> deleteQuestion(@RequestParam ("id") Integer id, QuestionResponseDto responseDto) {  // ResponseEntity<QuestionResponseDto>, requestDTO로 작업요청, id도 넣어줌: 이 방법을 수행하려면, QuestionRequestDto에 id 변수가 있어야 했는데, 내가 그건 생성을 안해둬서 이번엔 사용할 수 없음.
        questionService.delete(id, responseDto);   // id에 해당하는 question을 삭제요청 in questionSaveReqeustDto.java by 'questionService.java'에 있는 delete()
        return ResponseEntity.ok().body(responseDto);  // void로 return이 필요없을 것 같지만, 잘 수행했는지 확인용으로 responseDto를 받아줌
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



//    게시판 추천기능을 위한 코드
//    boolean like = false; // 비로그인 유저라면 무조건 false;
//    if(user != null) {  // 로그인 했다면
//
//        Long member_id = user.getMember().getId()
//    }
//}
}