package com.estsoft.blogjpa.controller;

import com.estsoft.blogjpa.domain.Article;
import com.estsoft.blogjpa.dto.AddArticleRequest;
import com.estsoft.blogjpa.service.BlogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class ExternalApiParser {

    private final BlogService blogService;

    public ExternalApiParser(BlogService blogService) {
        this.blogService = blogService;
    }

    public void parser(){
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://jsonplaceholder.typicode.com/posts";
        ResponseEntity<List> response = restTemplate.getForEntity(url, List.class);
        if(response.getStatusCode().is2xxSuccessful()){
            log.info("body={}",response.getBody());
            List<LinkedHashMap<String,Object>> list = response.getBody();
            for(LinkedHashMap<String,Object> map : list){
                String title = (String)map.get("title");
                String content = (String)map.get("body");
                blogService.save(new AddArticleRequest(title,content));
            }
        }
    }
}
