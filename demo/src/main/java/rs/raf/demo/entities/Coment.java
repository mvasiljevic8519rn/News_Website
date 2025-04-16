package rs.raf.demo.entities;

import java.sql.Date;
import java.sql.Timestamp;

public class Coment {
    private int id;
    private String content;
    private String autor;
    private Timestamp date;
    private int newsId;

    public Coment(){}
    public Coment(int newsId,String content, String autor) {
        this.content = content;
        this.autor = autor;
        this.newsId =newsId;
    }
    public Coment(int id, String content, String autor, Timestamp date) {
        this.id = id;
        this.content = content;
        this.autor = autor;
        this.date = date;
    }
    public Coment(int id, String content, String autor, Timestamp date,int newsId) {
        this.id = id;
        this.content = content;
        this.autor = autor;
        this.date = date;
        this.newsId=newsId;
    }

    public int getNewsId() {
        return newsId;
    }

    public void setNewsId(int newsId) {
        this.newsId = newsId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Timestamp getDate() {
        return date;
    }
    public void setDate(Timestamp date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Coment{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", autor='" + autor + '\'' +
                ", date=" + date +
                ", newsId=" + newsId +
                '}';
    }
}
