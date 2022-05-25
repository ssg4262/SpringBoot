package com.example.gsbproject.controller;

import com.example.gsbproject.dto.ArticleForm;
import com.example.gsbproject.entity.Article;
import com.example.gsbproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller // 주의 컨트롤러시작할때 항상 선언
@Slf4j // 로깅을 위한 어노테이션
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
        log.info(form.toString());
        //jpa 로 db에 전달


        //dto를 entity로 변환
        Article article = form.toEntity(); //엔티티로 변환한걸 아티클 타입으로
        //2.repository 에게 entity를 db안에 저장하게함
        log.info(article.toString());

      Article saved =  articleRepository.save(article);  //articleRepository.save() 위에서 만든 entity를 db에 저장하겠다! 이순간 entity 에있는 자동생성된 아이디값이 null자리에 입력됨
        log.info(saved.toString());
       return "";// 저장만 하기때매 리턴은 없음
    }
        @GetMapping("/articles/{id}")
    public String show(@PathVariable  Long id, Model model){ // Model model 은 돌려주는값 응답 이라 생각하면 편함

        log.info("id= "+id);
        //1: id 로 데이터를 가져옴
           Article articleEntity = articleRepository.findById(id).orElse(null); //.orElse(null); 뜻  아이디를 찾았는데 없으면 널반환
            //2: 가져온 데이터를 모델에 등록!
            model.addAttribute("article",articleEntity);
            //3: 보여줄 페이지를 설정정

        return "articles/show";

       }
}
