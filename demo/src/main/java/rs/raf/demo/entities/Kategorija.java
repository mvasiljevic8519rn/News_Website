package rs.raf.demo.entities;

public class Kategorija {
    private int id;
    private String title;
    private String content;

    public Kategorija(){}

    public Kategorija(String title,String content){
        this.title=title;
        this.content=content;
    }
    public Kategorija(int id,String title,String content){
        this.id=id;
        this.content=content;
        this.title=title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
