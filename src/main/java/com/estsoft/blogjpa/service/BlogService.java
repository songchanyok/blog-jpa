package com.estsoft.blogjpa.service;

import com.estsoft.blogjpa.domain.Article;
import com.estsoft.blogjpa.domain.ArticleResponse;
import com.estsoft.blogjpa.dto.AddArticleRequest;
import com.estsoft.blogjpa.repository.BlogRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class BlogService {
    private final BlogRepository blogRepository;


    public BlogService(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    public Article save(AddArticleRequest request){
        return blogRepository.save(request.toEntity());
    }

    public List<Article> findAll(){
        return blogRepository.findAll();
    }

    public Article findById(Long id){
        try {
            blogRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(id + ""));
        }catch (IllegalArgumentException e){
            log.error(String.valueOf(e));
        }
        return blogRepository.findById(id).orElse(new Article());
    }

    public void deleteById(Long id){
        blogRepository.deleteById(id);
    }


    public List<Article> findByTitle(String title){
        return blogRepository.findByTitle(title);
    }

    @Transactional
    public Article update(Long id, AddArticleRequest request) {

        Optional<Article> article = blogRepository.findById(id);
        try {
            article.orElseThrow(() -> new IllegalArgumentException("not found " + id));
        } catch (IllegalArgumentException e) {
            log.error(" " + e);
        }
        if(article.isPresent()){
            if (article.get().getTitle().equals(request.getContent()) && article.get().getTitle().equals(request.getTitle())) {
                return article.get();
            } else {
                article.get().update(request.getTitle(), request.getContent());
                return article.get();

            }
        }else{
            return new Article();
        }

    }

    @Transactional
    public Article updateTitle(Long id, String title) {
        Article article = null;
        try {
            article = blogRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("not found " + id));
        } catch (IllegalArgumentException e) {
            log.error(" " + e);
        }
        blogRepository.updateTitle(id, title);
        return article;
    }
}
