package com.example.hksbdemo.domain.question_voter;

import com.example.hksbdemo.domain.Question;
import com.example.hksbdemo.domain.SiteUser;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity(name = "question_voter")
@IdClass(Question_Voter.class)
@Table(name = "question_voter")
public class Question_Voter implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ManyToOne
    @JoinColumn(name = "voter_id")
    private SiteUser siteUser;

    @Builder
    public Question_Voter(Question question, SiteUser siteUser) {
        this.question = question;
        this.siteUser = siteUser;
    }

    public Question_Voter() {

    }
}
