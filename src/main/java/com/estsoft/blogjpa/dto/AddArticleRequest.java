package com.estsoft.blogjpa.dto;


import com.estsoft.blogjpa.domain.Article;
import com.estsoft.blogjpa.domain.ArticleResponse;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AddArticleRequest {
    private String title;
    private String content;

    public Article toEntity(){
//        Article article = new Article(title,content);
        return Article.builder()
                .title(title).content(content).build();
    }

}

