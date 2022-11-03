package com.example.hksbdemo.repository;

import com.example.hksbdemo.domain.SiteUser;
import com.example.hksbdemo.domain.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
//JpaRepository를 상속할 때는 제네릭스 타입으로 <엔터티 타입, PK속성 타입>을 지정하는 것이 규칙
public interface QuestionRepository extends JpaRepository <Question, Integer> {

    List<Question> findBySubject(String subject);
    List<Question> findBySubjectOrContent(String subject, String content);
    List<Question> findAllByOrderByIdDesc();  // 처음부터 순서 맞춰서 데려온다.

    Page<Question> findBySubjectContainingOrContentContaining(String subject, String content, Pageable pageable);
    default Question findByAuthor_id(SiteUser author_id) {
        return null;
    }

//    Repository의 메서드명은 데이터 조회하는 쿼리문의 where 조건을 결정하는 역할
//    return type이 여러 건인 경우는 리턴타입을 리스트 형태로 해야함 : List<Question>
}

