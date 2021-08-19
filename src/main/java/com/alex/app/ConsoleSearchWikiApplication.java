package com.alex.app;

import com.alex.app.response.WikiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ConsoleSearchWikiApplication implements CommandLineRunner {

    @Autowired
    private WikiResponse response;

    public static void main(String[] args) {
        SpringApplication.run(ConsoleSearchWikiApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("************************************\n\n");
        response.gsonParser();
    }
}
