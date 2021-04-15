package hello.hellospring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HelloSpringApplication {

	public static void main(String[] args) {
		//HelloSpringApplication 클래스의 annotation
		//@SpringBootApplication 에 의해 내장된 tomcat이 같이 실행
		SpringApplication.run(HelloSpringApplication.class, args);
	}

}
