package com.example.hksbdemo.domain.answer;

import com.example.hksbdemo.domain.question.question;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@Entity(name = "answer")
//@EntityListeners(value = AuditingEntityListener.class)
public class answer {

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
    private com.example.hksbdemo.domain.site_user.site_user site_user;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private question question;

    @Builder
    public answer(String content, LocalDateTime create_date, LocalDateTime modify_date, com.example.hksbdemo.domain.site_user.site_user site_user, question question) {
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
    void modify_date() {
        this.modify_date = this.modify_date = LocalDateTime.now();
    }
}


