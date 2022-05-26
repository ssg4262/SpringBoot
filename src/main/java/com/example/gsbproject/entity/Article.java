package com.example.gsbproject.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity// 어노테이션으로 엔티티로 만들어줌 (db에도 create 테이블이 만들어진다)
@AllArgsConstructor
@ToString   //롬북으로리펙토링
@NoArgsConstructor // 기본생성자 어노테이션
@Getter
public class Article {
    //dto랑 같음
    @Id // 구분짓기위한 값 like a 주민번호
    @GeneratedValue(strategy = GenerationType.IDENTITY) //  @GeneratedValue 자돟생성 1,2,3,..... db가 id 를 자동생성
    private Long id; //구분짓기위한 아이디를 여기서 만들어줌

    @Column
    private String title;
     
    @Column
    private String content;

    public void patch(Article article) {
        if(article.title !=null) // 기존에 입력된 title이있다면
            this.title=article.title; //매개로 오는거에 기존걸넣기

        if(article.content != null)
            this.content = article.content;

    }
/*
    public Long getId() {
        return id;
    }// 롬북으로 @Getter어노테이션으로 처리
    
    
    
 */
//   @Column을 붙여서 둘다 컬럼취급
/*
    //entity 생성자 추가
    public Article(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }


    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }*/

    //기본생성자 필수
   // public  Article(){}


}
