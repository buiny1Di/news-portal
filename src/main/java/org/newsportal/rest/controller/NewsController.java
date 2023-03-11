package org.newsportal.rest.controller;

import org.newsportal.service.NewsService;
import org.newsportal.service.model.News;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/news-portal")
public class NewsController {
    private final NewsService newsService;

    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping("/news")
    public ResponseEntity<List<News>> getAllNews() {
        return ResponseEntity.ok(newsService.getAll().orElseThrow(RuntimeException::new));

    }

    @GetMapping("/news/{id}")
    public ResponseEntity<News> getNewsById(
            @PathVariable String id
    ) {
        return ResponseEntity.ok(newsService.getNewsById(id).orElseThrow(RuntimeException::new));
    }
}
