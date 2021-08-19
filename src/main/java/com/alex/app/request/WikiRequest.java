package com.alex.app.request;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 *
 */
@Component
@Slf4j
public class WikiRequest {

    private static final String WIKI_REQUEST = "https://ru.wikipedia.org/w/api.php?action=query&list=search&utf8=&format=json&srsearch=";

    public String generate(String data) throws IOException {

        log.info("Getting information from Wikipedia...");
        Content getResult = Request.Get(WIKI_REQUEST + data)
                .execute()
                .returnContent();

        return getResult.asString();
    }
}
