package com.estsoft.blogjpa.controller;


import com.estsoft.blogjpa.domain.Article;
import com.estsoft.blogjpa.domain.ArticleResponse;
import com.estsoft.blogjpa.domain.ArticleViewResponse;
import com.estsoft.blogjpa.dto.AddArticleRequest;
import com.estsoft.blogjpa.service.BlogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
public class BlogController {
    private final BlogService blogService;

    public BlogController(BlogService blogService, ExternalApiParser parser) {
        this.blogService = blogService;
        this.parser = parser;
    }

    @PostMapping(value = "/api/articles") //json이 넘어온다.
    public ResponseEntity<ArticleResponse> saveOne(@RequestBody AddArticleRequest request){
        Article article = blogService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(article.articleResponse());
    }

    @RequestMapping(value = "/api/articles", method = RequestMethod.GET)
    public String show(Model model){
        List<Article> article = blogService.findAll();

        model.addAttribute("articles",article);
        return "ArticleList";
    }


    @GetMapping(value = "api/article/{id}")
    public String findByid(@PathVariable Long id,Model model){
        Article article = blogService.findById(id);
        model.addAttribute("article",article.toViewResponse());

        return "Article";

    }

    @ResponseBody
    @GetMapping(value = "api/article/title")
    public ResponseEntity<List<Article>> findByTitle(@RequestParam String title){
        List<Article> article = blogService.findByTitle(title);

        return ResponseEntity.status(HttpStatus.OK).body(article);

    }

    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Void> deleteByid(@PathVariable Long id){
        blogService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/api/articles/update/{id}")
    public ResponseEntity<Article> update(@PathVariable Long id,@RequestBody AddArticleRequest request){
        Article article = blogService.update(id,request);
        return ResponseEntity.status(HttpStatus.OK).body(article);
    }

    @PutMapping("/api/articles/updatetitle/{id}")
    public ResponseEntity<Article> updateTitle(@PathVariable Long id, @RequestBody AddArticleRequest request){
        Article article = blogService.updateTitle(id,request.getTitle());
        return ResponseEntity.ok(article);
    }

    @GetMapping("/new-article")      //   /new-article?id= dd (수정)   /new-article (등록)
    public String newArticle(@RequestParam(required = false) Long id, Model model){
        if(id==null){
            model.addAttribute("article",new ArticleViewResponse());
        }else{
            Article article = blogService.findById(id);
            model.addAttribute("article",new ArticleViewResponse(article));
        }

        return "newArticle";
    }
    private final ExternalApiParser parser;
    @ResponseBody
    @GetMapping("/api/load")
    public String loadApi(){
        parser.parser();
        return "";
    }


}
