package com.myblog12.myblog12;


import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class Myblog12Application {

	public static void main(String[] args) {
		SpringApplication.run(Myblog12Application.class, args);


		}
		@Bean
		public ModelMapper getModelMapper(){

		return new ModelMapper();

	}

}
