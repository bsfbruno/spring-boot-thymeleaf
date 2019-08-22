package br.com.bruno.javaavancado;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
//as anotações abaixo não são necessárias
//só estou colocando por questões didáticas
@EntityScan(basePackages="br.com.bruno.javaavancado.model")
@ComponentScan(basePackages= {"br.com.bruno.*"})
@EnableJpaRepositories(basePackages="br.com.bruno.javaavancado.repository")
@EnableTransactionManagement
public class JavaAvancadoApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaAvancadoApplication.class, args);
	}

}
