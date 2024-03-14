package com.estsoft.blogjpa.domain;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArticleResponse {
    private String title;
    private String content;

    public ArticleResponse(Article article){

    }
    public Article mapper(){
        Article article = new Article(title,content);
        return article;
    }


}
