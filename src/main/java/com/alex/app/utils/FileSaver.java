package com.alex.app.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Scanner;

@Component
@Slf4j
public class FileSaver {

    private static RandomAccessFile file;

    private static FileChannel channel;

    private static Scanner scanner;

    public void saveToFile(String response) {
        scanner = new Scanner(System.in);

        System.out.println("Do you your information to be saved? y/n");

        String respond = scanner.nextLine();

        if (respond.equals("y")) {

            System.out.println("Enter file name down below");

            String fileName = scanner.nextLine();

            try {

                file = new RandomAccessFile(fileName, "rw");

                channel = file.getChannel();

                ByteBuffer byteBuffer = ByteBuffer.wrap(response.getBytes());

                log.info("Saving to file {}", fileName);
                channel.write(byteBuffer);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @PreDestroy
    private void closeResources() throws IOException {
        log.info("Closing resources...");
        scanner.close();
        file.close();
        channel.close();
    }
}
