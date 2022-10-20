package com.example.hksbdemo;

import com.example.hksbdemo.repository.SiteUserRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class HksbdemoApplicationTests {

	@Autowired
	protected com.example.hksbdemo.repository.questionRepository questionRepository;

	@Autowired
	protected SiteUserRepository site_userRepository;

	private final String title = "제목";
	private final String email = "gprud@naver.com";

//	뭔지 모르겠음. 경득님에게 질문
//	@BeforeEach
//	public void init() {
//		site_user author_id = questionRepository.save(site_user.builder()
//				.username("hk")
//				.password("1234")
//				.email("email")
//				.build());
//
//		questionRepository.save(question.builder()
//				.content("내용")
//				.site_user(author_id)
//				.subject("제목")
//				.build());
//	}
//
//	@Test
//	void contextLoads() {
//		site_user site_user = site_userRepository.findByEmail(email);
//		assertThat(site_user.getId(), is("seek"));
//		assertThat(site_user.getPassword(), is("1234"));
//		assertThat(site_user.getEmail(), is(email));
//
//		question question = questionRepository.findByAuthor_id(author_id);
//		assertThat(question.getSubject(), is(subject));
//		assertThat(question.getContent(), is("내용"));
//	}
}
