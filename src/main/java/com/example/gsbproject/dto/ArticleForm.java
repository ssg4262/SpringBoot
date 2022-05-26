package com.example.gsbproject.dto;

import com.example.gsbproject.entity.Article;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Setter
@NoArgsConstructor//인수가없는 생성자
public class ArticleForm {
    //데이터 오가는건 무조건  dto  생성
    private String title;
    private String content;
    private Long id;

    //생성자 단축키 art insert


   /* public ArticleForm(String title, String content) {
        this.title = title;
        this.content = content;
    }


    //데이터 확인용  tostring 굳이 없어도됨

    @Override
    public String toString() {
        return "ArticleForm{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }*/ //리펙토링하여서 주석처리


    //당연히 private 여서 get set 만들기


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Article toEntity() {  // dto로 이동됨 아까만든 entity타입으로받음
        return  new Article(id,title,content); // dto 에 있는값을 생성자에담아 리턴
    }
}
