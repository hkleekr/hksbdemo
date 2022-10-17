package com.example.hksbdemo.domain.question;

import com.example.hksbdemo.domain.answer.answer;
import com.example.hksbdemo.domain.site_user.site_user;
import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.core.annotation.Order;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@Data
@Entity(name = "question")
public class question {

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

    @OneToMany (mappedBy = "question", cascade = CascadeType.REMOVE)
    @OrderBy("id asc")  // 댓글 정렬 10/17 추가
    private List<answer> answerList;

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
}
