package com.example.hksbdemo.domain.question;

import com.example.hksbdemo.domain.answer.answer;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity(name = "question")
public class question {
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

    @ManyToOne
    @JoinColumn(name = "author_id")
    private com.example.hksbdemo.domain.site_user.site_user site_user;

    @OneToMany (mappedBy = "question", cascade = CascadeType.REMOVE) // cascade => answer도 함께 저장
//    @OrderBy("id asc")  // 댓글 정렬 10/17 추가
    private List<answer> answerList;  // 이 항목을 추가함으로써 answer와 연결

    @Builder
    public question(String content, LocalDateTime create_date, LocalDateTime modify_date, String subject, com.example.hksbdemo.domain.site_user.site_user site_user) {
        this.content = content;
        this.create_date = create_date;
        this.modify_date = modify_date;
        this.subject = subject;
        this.site_user = site_user;
    }

    @PrePersist
    void create_date() {
        this.create_date = this.create_date = LocalDateTime.now();
    }
    void modify_date() {
        this.modify_date = this.modify_date = LocalDateTime.now();
    }
}
