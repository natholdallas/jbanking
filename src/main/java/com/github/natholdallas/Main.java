package com.github.natholdallas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Main {

    public static ApplicationContext context;

    public static void main(String[] args) {
        context = SpringApplication.run(Main.class, args);
    }

}
