package br.com.bruno.javaavancado;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages="br.com.bruno.javaavancado.model")
public class JavaAvancadoApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaAvancadoApplication.class, args);
	}

}
