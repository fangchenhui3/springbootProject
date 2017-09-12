package com.imyzone;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.imyzone.dao")//映射mapper和dao*/
public class SpringbootTest0610Application {
	public static void main(String[] args) {
		SpringApplication.run(SpringbootTest0610Application.class, args);
	}
}
