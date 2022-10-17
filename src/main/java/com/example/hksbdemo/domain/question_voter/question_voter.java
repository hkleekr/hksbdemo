package com.example.hksbdemo.domain.question_voter;

import com.example.hksbdemo.domain.question.question;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity(name = "question_voter")
@IdClass(question_voter.class)
@Table(name = "question_voter")
public class question_voter implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ManyToOne
    @JoinColumn(name = "question_id")
    private question question;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ManyToOne
    @JoinColumn(name = "voter_id")
    private com.example.hksbdemo.domain.site_user.site_user site_user;

    @Builder
    public question_voter(com.example.hksbdemo.domain.question.question question, com.example.hksbdemo.domain.site_user.site_user site_user) {
        this.question = question;
        this.site_user = site_user;
    }

    public question_voter() {

    }
}
