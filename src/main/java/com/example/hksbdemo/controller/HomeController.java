package com.example.hksbdemo.controller;

import com.example.hksbdemo.domain.AnswerResponseDto;
import com.example.hksbdemo.domain.AnswerSaveRequestDto;
import com.example.hksbdemo.domain.QuestionResponseDto;
import com.example.hksbdemo.domain.Question;
import com.example.hksbdemo.domain.QuestionSaveRequestDto;
import com.example.hksbdemo.domain.site_user.SiteUser;
import com.example.hksbdemo.domain.site_user.SiteUserResponseDto;
import com.example.hksbdemo.domain.site_user.SiteUserSaveRequestDto;
import com.example.hksbdemo.repository.answerRepository;
import com.example.hksbdemo.repository.questionRepository;
import com.example.hksbdemo.service.answer.AnswerService;
import com.example.hksbdemo.service.question.QuestionService;
import com.example.hksbdemo.service.site_user.SiteUserService;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.dao.DataIntegrityViolationException;

import javax.validation.Valid;

@RequiredArgsConstructor
@Controller
public class HomeController {

    private final AnswerService answerService;
    private final QuestionService questionService;

    @Autowired
    private answerRepository answerRepository;
    @Autowired
    private questionRepository questionRepository;
    private ResponseEntity<QuestionResponseDto> Boolean;

    //    GET- cRud
    @GetMapping("/board")
    public String list(Model model, @PageableDefault(size = 8, sort="id", direction = Sort.Direction.DESC) Pageable pageable,
                       @RequestParam(required = false, defaultValue = "") String searchText) {
//        Page<question> q = questionRepository.findAll(pageable);
        Page<Question> q = questionRepository.findBySubjectContainingOrContentContaining(searchText, searchText, pageable);
        int startPage = Math.max(1, q.getPageable().getPageNumber() - 4);
        int endPage = Math.min(q.getTotalPages(), q.getPageable().getPageNumber() + 4);

        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("q", q);
        return "list";
    }

    @GetMapping("/board/detail")
    public String answ(@RequestParam Integer id, Model model) {  //pageable 추가, id영역에 answer_id를 넣어야 할 것 같음
        Question q = (Question) questionService.getDetail(id);
        model.addAttribute("detail",q);
    return "answerdetail";
    }

    @GetMapping("/board/question")
    public String ques(Model model, @RequestParam(required = false) Integer id) {
        if(id == null){
            model.addAttribute("ques", new Question());
        } else {
            Question ques = questionRepository.findById(id).orElse(null);
            model.addAttribute("ques", ques);
        }
        return "questiondetail";
    }

    //게시물 쓰기
    @PostMapping("/board/question")
    public ResponseEntity<QuestionResponseDto> questionSubmit(QuestionSaveRequestDto questionSaveRequestDto) {
        QuestionResponseDto questionResponseDto = new QuestionResponseDto();
        questionService.save(questionSaveRequestDto, questionResponseDto);
        return ResponseEntity.ok().body(questionResponseDto);
    }


    @PostMapping("/board/detail")
        public ResponseEntity<AnswerResponseDto> AnswerSubmit(AnswerSaveRequestDto answerSaveRequestDto) {  // 스프링 내장함수 "ResponseEntity<>"의 <>안 타입영역에 불러올 DTO를 넣는다. "AnswerSubmit()"에 요청 DTO를 담고,
        AnswerResponseDto answerResponseDto = new AnswerResponseDto(); // "AnswerResponseDto"의 객체를 생성
        answerService.save(answerSaveRequestDto, answerResponseDto); // answerService.java의 "save()"를 통해 인자를 주입
        return ResponseEntity.ok().body(answerResponseDto); // "ResponseEntity.ok()" 잘 실행 되었으면, body의 "answerResponseDto"를 리턴하시오
    }

    @DeleteMapping("/board/question")
    public ResponseEntity<QuestionResponseDto> deleteQuestion(@RequestParam ("id") Integer id, QuestionResponseDto responseDto) {  // ResponseEntity<QuestionResponseDto>, requestDTO로 작업요청, id도 넣어줌: 이 방법을 수행하려면, QuestionRequestDto에 id 변수가 있어야 했는데, 내가 그건 생성을 안해둬서 이번엔 사용할 수 없음.
        questionService.delete(id, responseDto);   // id에 해당하는 question을 삭제요청 in questionSaveReqeustDto.java by 'questionService.java'에 있는 delete()
        return ResponseEntity.ok().body(responseDto);  // void로 return이 필요없을 것 같지만, 잘 수행했는지 확인용으로 responseDto를 받아줌
    }

//    Answer 삭제
    @DeleteMapping("/board/detail")
    public ResponseEntity<AnswerResponseDto> deleteAnswer(@RequestParam ("id") Integer id, AnswerResponseDto responseDto) {  // id = question의 id
//        answer a = new answer(); // id를 통해 클릭이 된 Answer의 id를 데려오는 코드 "answerService.java"
//        id = answerService.findAnswerId(question_id, responseDto);  // 제거대상인 answer의 id를 데려오면
        answerService.delete(id, responseDto);  // answer의 id를 통해서 답변 삭제

        return ResponseEntity.ok().body(responseDto);
    }

//    질문수정
    @PutMapping("/board/question")  // id로 response를 받아서, 내용 수정한 데이터를 modify함수로 덮고, 저장하라고 보내기(RequestSaveDto)
    public ResponseEntity<QuestionResponseDto> questionModifySubmit(@RequestParam ("id") Integer id, QuestionResponseDto questionResponseDto, QuestionSaveRequestDto questionSaveRequestDto) {  //넘어오긴 했음. 그럼 fetch 정상작동
        questionService.update(id, questionSaveRequestDto, questionResponseDto);
        return ResponseEntity.ok().body(questionResponseDto);
    }

//    답변수정
    @PutMapping("/board/detail")
    public ResponseEntity<AnswerResponseDto> answerModifySubmit(@RequestParam ("id") Integer id, AnswerResponseDto answerResponseDto, AnswerSaveRequestDto answerSaveRequestDto) {
        answerService.update(id, answerSaveRequestDto, answerResponseDto);
        return ResponseEntity.ok().body(answerResponseDto);
    }

    private final SiteUserService siteUserService;

    //**회원**
    //등록페이지
    @GetMapping("/user/signup")
    public String signup(@RequestParam(required = false) Long id, Model model) {
            model.addAttribute("signup", new SiteUser());
        return "signup_form";
    }

    //저장
    @PostMapping("/user/signup")
        public ResponseEntity<SiteUserResponseDto> signupSubmit(SiteUserSaveRequestDto siteUserSaveRequestDto) {
        SiteUserResponseDto siteUserResponseDto = new SiteUserResponseDto();
        siteUserService.save(siteUserSaveRequestDto, siteUserResponseDto);
        return ResponseEntity.ok().body(siteUserResponseDto);
    }

    //수정
    @PutMapping("/user/signup")
    public ResponseEntity<SiteUserResponseDto> userModifySubmit(SiteUserSaveRequestDto siteUserSaveRequestDto, SiteUserResponseDto siteUserResponseDto) {
        siteUserService.update(siteUserSaveRequestDto, siteUserResponseDto);
        return ResponseEntity.ok().body(siteUserResponseDto);
    }

    //탈퇴
    @DeleteMapping("/user/signup")
    public ResponseEntity<SiteUserResponseDto> userDelete(SiteUserSaveRequestDto siteUserSaveRequestDto, SiteUserResponseDto siteUserResponseDto) {
        siteUserService.update(siteUserSaveRequestDto, siteUserResponseDto);
        return ResponseEntity.ok().body(siteUserResponseDto);
    }

//    게시판 추천기능을 위한 코드
//    boolean like = false; // 비로그인 유저라면 무조건 false;
//    if(user != null) {  // 로그인 했다면
//
//        Long member_id = user.getMember().getId()
//    }
//}
}