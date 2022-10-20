package com.example.hksbdemo.domain.question_voter;

import com.example.hksbdemo.domain.Question;
import com.example.hksbdemo.domain.site_user.SiteUser;
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
    private Question question;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ManyToOne
    @JoinColumn(name = "voter_id")
    private SiteUser site_user;

    @Builder
    public question_voter(Question question, SiteUser site_user) {
        this.question = question;
        this.site_user = site_user;
    }

    public question_voter() {

    }
}
