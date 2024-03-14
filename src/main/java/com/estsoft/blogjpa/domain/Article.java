package com.estsoft.blogjpa.domain;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;


@Getter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    @CreatedDate
    @Column(name = "create_date", nullable = false)
    private LocalDateTime createDate;

    @LastModifiedDate
    @Column(name = "update_date",nullable = false)
    private LocalDateTime updateDate;

    @Builder
    public Article(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public ArticleResponse articleResponse(){

        return ArticleResponse.builder()
                .content(content)
                .title(title)
            .build();
    }

    public void update(String title,String content){
        this.title=title;
        this.content=content;
    }

    public ArticleViewResponse toViewResponse(){
        return new ArticleViewResponse(id,title,content,createDate,updateDate);
    }

}
