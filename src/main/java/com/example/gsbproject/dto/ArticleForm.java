package com.example.gsbproject.dto;

public class ArticleForm {
    //폼내용이 바로 dto로 전송됨 기존 하던거와는 차이가있음 기존엔
    //데이터 오가는건 무조건  dto  생성
    private String title;
    private String content;


    //생성자 단축키 art insert


    public ArticleForm(String title, String content) {
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
    }


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
}
