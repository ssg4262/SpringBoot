package com.example.gsbproject.service;

import com.example.gsbproject.dto.ArticleForm;
import com.example.gsbproject.entity.Article;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest // 해당클래스는 스프링 부트와 연동되어 테스팅
class ArticleServiceTest {
        @Autowired ArticleService articleService; // 외부객체 생성

    @Test
    void index() {
        //예상 시나리오
        Article a =new Article(1L,"가가가","1111");
        Article b =new Article(2L,"나나나","2222");
        Article c =new Article(3L,"다다다","3333");
        List<Article> expected = new ArrayList<Article>(Arrays.asList(a,b,c));


        //실제
          List<Article> articles = articleService.index();
        
        //비교
        assertEquals(expected.toString(),articles.toString());

        
    }

    @Test
    void show_성공___존재아이디_입력() {
        //예상
        Long id = 1L;
       Article expected=   new Article(id,"가가가","1111");
        //실제
        Article article = articleService.show(id);
        assertEquals(expected.toString(),article.toString());

    }

    @Test
    void show_실패__존재하지_않는_아이디입력() {
        //예상
        Long id = -1L;
        Article expected= null;
        //실제
        Article article = articleService.show(id);

        //비교
        assertEquals(expected,article);



    }

    @Test
    @Transactional
    void create_성공__title과__content만_있는거입력() {
        //예상
        String title="라라라";
        String content="444";
        ArticleForm dto = new ArticleForm(title,content,null);


        Article expected = new Article(4L,title,content);

        //실제
        Article article = articleService.create(dto);

        //비교
        assertEquals(expected.toString(),article.toString());



    }

    @Test
    @Transactional
    void create_실패_id가_포함된_dto입력() {
        //예상
        String title="라라라";
        String content="444";
        ArticleForm dto = new ArticleForm(title,content,4L);


        Article expected = null;

        //실제
        Article article = articleService.create(dto);

        //비교
        assertEquals(expected,article);



    }
}