package com.alex.app.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

@Component
@Slf4j
public class FileSaver {

    private static Scanner scanner;

    public void saveToFile(String response) {
        scanner = new Scanner(System.in);

        System.out.println("Do you your information to be saved? y/n");

        String respond = scanner.nextLine();

        if (respond.equals("y")) {

            System.out.println("Enter file name down below:");

            String fileName = scanner.nextLine();

            try {
                Path filePath = Paths.get(fileName);
                if(!Files.exists(filePath) && filePath.getFileName() != null) {

                    Files.createFile(filePath);

                    Files.write(filePath, response.getBytes());

                    log.info("Saving to file {}", filePath.getFileName());
                } else {
                    System.out.println("The file with this name already exists, choose another name");
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @PreDestroy
    private void closeResources() throws IOException {
        log.info("Closing resources...");
        scanner.close();
    }
}
