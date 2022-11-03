package com.example.hksbdemo.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@Entity(name = "answer")
//@EntityListeners(value = AuditingEntityListener.class)
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, length=4)
    private Integer id;

    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    @Column(name = "create_date", columnDefinition = "TIMESTAMP")
    private LocalDateTime create_date;

    @Column(name = "modify_date", columnDefinition = "TIMESTAMP")
    private LocalDateTime modify_date;

    @ManyToOne //여러 개의 답변을 한 명이 작성할 수 있으므로
    @JoinColumn(name = "author_id")
    private SiteUser site_user;

    @ManyToOne  // 여러 개의 답변과 질문 1개가 연결 됨을 명시
    @JoinColumn(name = "question_id")
    @JsonBackReference  //question-answer의 순환참조를 해결하기 위해
    private Question question; //Question Entity와 연결된 속성임을 표기

//    for 추천 10/26
    @ManyToMany
    Set<SiteUser> voter;  //중복을 허용하지 않기 위해 set

    @Builder
    public Answer(String content, LocalDateTime create_date, LocalDateTime modify_date, SiteUser site_user, Question question) {
        this.content = content;
        this.create_date = create_date;
        this.modify_date = modify_date;
        this.site_user = site_user;
        this.question = question;
    }
    @PrePersist
    void create_date() {
        this.create_date = this.create_date = LocalDateTime.now();
    }

//    @PrePersist Modify_date에도 썼더니 에러발생
    void modify_date() {
        this.modify_date = this.modify_date = LocalDateTime.now();
    }
}


