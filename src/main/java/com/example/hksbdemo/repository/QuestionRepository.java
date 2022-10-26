package com.example.hksbdemo.repository;

import com.example.hksbdemo.domain.SiteUser;
import com.example.hksbdemo.domain.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository <Question, Integer> {

    List<Question> findBySubject(String subject);
    List<Question> findBySubjectOrContent(String subject, String content);

    Page<Question> findBySubjectContainingOrContentContaining(String subject, String content, Pageable pageable);
    default Question findByAuthor_id(SiteUser author_id) {
        return null;
    }
}

