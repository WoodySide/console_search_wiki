package com.alex.app.response;

import com.alex.app.request.WikiRequest;
import com.alex.app.utils.FileSaver;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Scanner;

@Component
@Slf4j
public class WikiResponse {

    private final WikiRequest request;

    private final FileSaver saver;

    @Autowired
    public WikiResponse(WikiRequest request, FileSaver saver) {
        this.request = request;
        this.saver = saver;
    }

    public void  gsonParser() throws IOException {

        Scanner scanner = new Scanner(System.in);

        System.out.println("What are you looking for? Type your question down below.");

        String jsonString = request.generate(scanner.nextLine());

        JsonElement element = new JsonParser().parse(jsonString);

        JsonObject jsonObject = element.getAsJsonObject();

        jsonObject = jsonObject.getAsJsonObject("query");

        JsonArray jsonElements = jsonObject.getAsJsonArray("search");

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < jsonElements.size(); i++) {
            jsonObject = jsonElements.get(i).getAsJsonObject();
            builder.append(jsonObject.get("snippet")).append("\n");
        }

        String result = builder
                .substring(0, 1).toUpperCase() + builder.substring(1)
                .replaceAll("<span class=\\\\\\\"searchmatch\\\\\\\">", "");

        result = result.replaceAll("</span>", "");

        saver.saveToFile(result);
    }
}
