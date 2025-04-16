package rs.raf.demo.repositories.newsall;

import rs.raf.demo.entities.Id;
import rs.raf.demo.entities.News;

import java.util.List;

public interface NewsRepository {
    public News addNews(News news);
    public List<News> allNews();

    public List<News> allNews(Integer id);

    public  News replace(News news);
    public Integer max();
    public List<News> allNewsInKategorija(Integer id);
    public List<News> allNewsSortedDate();
    public List<News> allNewsSortedClicks();
    public void incrementClicks(Id id);
    public List<News> allNewsWithTag(String name);
    public List<News> topTenNews();
    public News findNewsById(Integer id);
    public Integer deleteNewsById(Integer id);

    public List<News>News_withWord(String in);


}
