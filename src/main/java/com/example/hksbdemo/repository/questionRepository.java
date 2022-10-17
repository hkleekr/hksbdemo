package com.example.hksbdemo.repository;

import com.example.hksbdemo.domain.site_user.site_user;
import com.example.hksbdemo.domain.question.question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface questionRepository extends JpaRepository <question, Integer> {

    List<question> findBySubject(String subject);
    List<question> findBySubjectOrContent(String subject, String content);

    Page<question> findBySubjectContainingOrContentContaining(String subject, String content, Pageable pageable);
    default question findByAuthor_id(site_user author_id) {
        return null;
    }
}

//Long 맞나 확인 필요함