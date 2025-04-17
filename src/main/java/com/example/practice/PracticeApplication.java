package com.example.practice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication

public class PracticeApplication {

	public static void main(String[] args) {
		String a;
		Scanner sc = new Scanner(System.in);
		a=sc.next();

		SpringApplication.run(PracticeApplication.class, args);
	}

}
