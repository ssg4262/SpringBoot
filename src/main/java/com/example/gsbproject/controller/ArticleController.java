package com.example.gsbproject.controller;

import com.example.gsbproject.dto.ArticleForm;
import com.example.gsbproject.entity.Article;
import com.example.gsbproject.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller // 주의 컨트롤러시작할때 항상 선언
public class ArticleController {
    @Autowired // 스프링 부트가 미리 생성해 놓은 객체를 가져다 자동연결
    //private ArticleRepository articleRepository = new ArticleRepository();하는 객체만드는 과정 생략
    private ArticleRepository articleRepository; //필드 변수 선언 그다음 ArticleRepository 인터페이스 생성

    @GetMapping("/articles/new")
    public String newArticleForm(){ // 폼리턴 메서드
         return "/articles/new";


    }
    @PostMapping("/articles/create") // 포스트 방식으로 던져서 요청을 포스트 매핑으로 받아야함
    public String createArticle(ArticleForm form){ // 매게값을 dto 객체로 받아서 바로 담기
        //현재 form에서 보낸정보 들고있는중
        
        //jpa 로 db에 전달
        System.out.println(form.toString());//확인용

        //dto를 entity로 변환
        Article article = form.toEntity(); //엔티티로 변환한걸 아티클 타입으로
        //2.repository 에게 entity를 db안에 저장하게함
        System.out.println("dto 에서 toEntity 로변환된값 "+article);

      Article saved =  articleRepository.save(article);  //articleRepository.save() 위에서 만든 entity를 db에 저장하겠다! 이순간 entity 에있는 자동생성된 아이디값이 null자리에 입력됨
        System.out.println(saved.toString());
       return "";
    }

}
