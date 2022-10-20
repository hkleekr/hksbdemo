package com.example.hksbdemo.domain;

import com.example.hksbdemo.domain.site_user.SiteUser;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

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

    @ManyToOne
    @JoinColumn(name = "author_id")
    private SiteUser site_user;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

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


