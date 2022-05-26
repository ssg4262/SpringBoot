package com.example.gsbproject.service;

import com.example.gsbproject.dto.ArticleForm;
import com.example.gsbproject.entity.Article;
import com.example.gsbproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service // 서비스 선언! (서비스 객체를 스프링부트에 생성)
public class ArticleService {


    @Autowired  // 외부 객체 땡기기
    private ArticleRepository articleRepository;


    public List<Article> index() {
        return articleRepository.findAll();
    }

    public Article show(Long id) {

        return articleRepository.findById(id).orElse(null);
    }

    public Article create(ArticleForm dto) {

     Article article = dto.toEntity();

     if(article.getId() !=null){ //id 값이있는데 생성이 되면 수정이 되버리기 때문에 있다면 널로 만들어서 오류가 되도록
         return null;
     }

     return articleRepository.save(article);

    }

    public Article update(Long id, ArticleForm dto) {



        //1: 수정용 엔티티 생성
         Article article = dto.toEntity(); // 요청이 들어올땐 db로 보낼수있는 enttity 가아니기 때문에 //dto 는 요청받은값
        log.info("id:{} , article:{}",id,article.toString());


    //2: 수정 대상 엔티티 를 db 에서 조회
      Article target = articleRepository.findById(id).orElse(null); //<- 는 db에서 조회한거  / 위엔 요청받은거 차이점

     //3: 잘못된 요청 처리
      if(target==null|| id !=article.getId()){
          //400 잘못된요청 응답
        log.info("잘못된 응답id:{} , article:{}",id,article.toString());
         return null;
     }

      //4: 업데이트 및 정상 응답
         target.patch(article); // 아무것도 입력안할시에 기존에 있는걸 넣기
         Article updated =  articleRepository.save(target);

            return updated;




    }

    public Article delete(Long id) {
        // 대상 찾기
      Article target = articleRepository.findById(id).orElse(null);


       //잘못된 요청 처리
          if(target == null){ // 데이터가없으면 못지우니
            return null;
        }

       //대상삭제
        articleRepository.delete(target);
       return  target  ;


    }
@Transactional // 해당 메소드를 트렉젝션으로 묶어 조건중 실패시 롤백
    public List<Article> createArticles(List<ArticleForm> dtos) {
        //dto 묶음을 entity 묶음으로 변환
   List<Article>  articleList  = dtos.stream()
                .map(dto-> dto.toEntity())
                .collect(Collectors.toList());
        
        //entity 묶음을 db로 저장
        articleList.stream()
                .forEach(article -> articleRepository.save(article));
        
        //강제 예외발생
        articleRepository.findById(-1L).orElseThrow(
                ()->new IllegalArgumentException("결재 실패!")
        );
        
        
        //결과반환
         return articleList;
        
    }
}
