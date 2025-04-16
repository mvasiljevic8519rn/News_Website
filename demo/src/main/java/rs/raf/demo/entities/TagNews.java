package rs.raf.demo.entities;

public class TagNews {
    private int tag_id;
    private int news_id;

    public TagNews(){};

    public TagNews(int news_id,int tag_id) {
        this.news_id=news_id;
        this.tag_id=tag_id;
    }

    public int getTag_id() {
        return tag_id;
    }

    public void setTag_id(int tag_id) {
        this.tag_id = tag_id;
    }

    public int getNews_id() {
        return news_id;
    }

    public void setNews_id(int news_id) {
        this.news_id = news_id;
    }
}
