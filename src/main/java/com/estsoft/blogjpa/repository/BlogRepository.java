package com.estsoft.blogjpa.repository;

import com.estsoft.blogjpa.domain.Article;
import com.estsoft.blogjpa.dto.AddArticleRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<Article,Long> {

    @Query(value = "SELECT title,content,id FROM article WHERE title= :title", nativeQuery = true)
    List<Article> findByTitle(String title);
    void deleteByContent(String content);
    //conflict conflict conflict conflict conflict
    //conflict conflict conflict conflict conflict

    //JPQL
    @Query(value = "update Article set title = :title where id = :id")
    @Modifying
    @Transactional
    void updateTitle(Long id, String title);
}
