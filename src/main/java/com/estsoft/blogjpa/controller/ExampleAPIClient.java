package com.estsoft.blogjpa.controller;


import com.estsoft.blogjpa.repository.BlogRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class ExampleAPIClient {
    private final String API_URL = "https://jsonplaceholder.typicode.com/posts";

    public String fetchDataFromApi() {
        RestTemplate restTemplate = new RestTemplate(); // RestTemplate 객체 생성

        // API로 GET 요청을 보냈고, ResponseEntity<String>으로 json 응답 받음
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(API_URL, String.class);

        if (responseEntity.getStatusCode().is2xxSuccessful()) {  //HTTP status code 200 체크
            return responseEntity.getBody();
        } else {
            log.error("Failed to fetch data from API. Status code: " + responseEntity.getStatusCodeValue());
            return null;
        }

    }
    @Autowired
    private BlogRepository blogRepository;
//    public String saveJsonToDb(){
//        String string = fetchDataFromApi();
//        for()
//        blogRepository.saveAll(string);
//    }

}
