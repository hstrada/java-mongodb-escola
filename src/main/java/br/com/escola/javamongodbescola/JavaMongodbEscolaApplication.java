package br.com.escola.javamongodbescola;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

// @EnableMongoRepositories(repositoryFactoryBeanClass=AlunoRepository.class)
@SpringBootApplication
@ComponentScan({"br.com.escola.javamongodbescola"})
public class JavaMongodbEscolaApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaMongodbEscolaApplication.class, args);
	}
}
