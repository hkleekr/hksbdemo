package com.example.hksbdemo.domain;

import com.example.hksbdemo.domain.question_voter.Question_Voter;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity(name = "question")
public class Question {
//    왜 오버라이드 하는가?
    @Override
    public String toString() {
        return "question{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", create_date=" + create_date +
                ", modify_date=" + modify_date +
                ", subject='" + subject + '\'' +
                ", site_user=" + site_user +
                ", answerList=" + answerList +
                '}';
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, length = 4)
    private Integer id;

    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    @Column(name = "create_date", columnDefinition = "TIMESTAMP")
    private LocalDateTime create_date;

    @Column(name = "modify_date", columnDefinition = "TIMESTAMP")
    private LocalDateTime modify_date;

    @NotNull
    @Column(name = "subject", length = 200)
    private String subject;

    @ManyToOne(fetch = FetchType.LAZY)  //여러 개의 질문을 한 명이 작성할 수 있으므로
    @JoinColumn(name = "author_id")
    private SiteUser site_user;

    @OneToMany (mappedBy = "question", cascade = CascadeType.REMOVE) // 질문 1개 to 답변 여러 개의 관계, cascade = REMOVE => 질문 삭제하면, 달린 답변 모두 삭제
    private List<Answer> answerList;  // 답변을 참조하기 위해, question.getAnswerList()형태로 호출 가능해 짐

//    for 추천 10/26
    @ManyToMany
    Set<SiteUser> voter;  //중복을 허용하지 않기 위해 set


    @Builder  //Entity에 생성자 대체로 만든다. DTO에는 필요 없음
    public Question(String content, LocalDateTime create_date, LocalDateTime modify_date, String subject, SiteUser site_user) {
        this.content = content;
        this.create_date = create_date;
        this.modify_date = modify_date;
        this.subject = subject;
        this.site_user = site_user;
    }

//    기본생성자 @NoArgsConstructor 애너테이션을 사용했으므로 생성자 불필요함
//    public Question() {
//    }

    @PrePersist
    void create_date() {
        this.create_date = this.create_date = LocalDateTime.now();
    }
//    @PrePersist Modify_date에도 썼더니 에러발생
    void modify_date() {
        this.modify_date = this.modify_date = LocalDateTime.now();
    }
}
