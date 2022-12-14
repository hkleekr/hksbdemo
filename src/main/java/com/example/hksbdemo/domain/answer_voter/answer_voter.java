package com.example.hksbdemo.domain.answer_voter;

import com.example.hksbdemo.domain.Answer;

import com.example.hksbdemo.domain.SiteUser;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity(name = "answer_voter")
@IdClass(answer_voter.class)
@Table(name = "answer_voter")
public class answer_voter implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ManyToOne
    @JoinColumn(name = "answer_id")
    private Answer answer;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ManyToOne
    @JoinColumn(name = "voter_id")
    private SiteUser site_user;

    @Builder

    public answer_voter(Answer answer, SiteUser site_user) {
        this.answer = answer;
        this.site_user = site_user;
    }

    public answer_voter() {

    }
}
