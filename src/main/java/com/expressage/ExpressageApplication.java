package com.expressage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.expressage.mapper")
public class ExpressageApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExpressageApplication.class, args);
	}

}

