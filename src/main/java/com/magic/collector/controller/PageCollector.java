package com.magic.collector.controller;

import com.magic.collector.model.PageInfo;
import com.magic.collector.queue.Producer;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@Slf4j
public class PageCollector {

    private final Producer producer;

    public PageCollector(Producer producer) {
        this.producer = producer;
    }

    @GetMapping("/collect")
    public PageInfo collect(@RequestParam(value = "link", defaultValue = "") String link) {
        log.info("Mylink: {}", link);

        try {
            URL url = new URL(link);
            Document document = Jsoup.connect(url.toString()).get();
            Elements linksOnPage = document.select("a[href]");
            List<URL> childPages = new ArrayList<>();
            for (Element page: linksOnPage) {
                String s = page.attr("abs:href");
                log.info("Child page: {}", s);
                childPages.add(new URL(s));
            }
            producer.sendMessage("Hello");
            log.info("Total child pages: {}", childPages.size());
            return new PageInfo(url, childPages);
        } catch (MalformedURLException exp) {
            log.error("Mal formed URL {}", link, exp);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                                              "Link "+link +" is not a proper URL");
        } catch (Exception exp) {
            log.error("General error to process the URL {}", link, exp);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                                              "Cpunld not process link "+link);
        }
    }
}
