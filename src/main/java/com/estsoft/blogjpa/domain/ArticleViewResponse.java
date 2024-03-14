package com.estsoft.blogjpa.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ArticleViewResponse {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    public ArticleViewResponse(Article article) {
        this.id = article.getId();
        this.title= article.getTitle();
        this.content=article.getContent();
        this.createDate=article.getCreateDate();
        this.updateDate=article.getUpdateDate();
    }
}
