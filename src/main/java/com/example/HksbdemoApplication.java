package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication //이 애너테이션을 통해 스프링부트의 모든 설정이 관리됨
@RestController

public class HksbdemoApplication {

		public static void main(String[] args) throws Throwable {
			SpringApplication.run(HksbdemoApplication.class, args);
	}
}

