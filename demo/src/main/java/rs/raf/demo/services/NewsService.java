package rs.raf.demo.services;

import rs.raf.demo.entities.Id;
import rs.raf.demo.entities.News;
import rs.raf.demo.repositories.newsall.NewsRepository;

import javax.inject.Inject;
import java.util.List;

public class NewsService {
    public NewsService(){
        System.out.println(this);
    }
    @Inject
    private NewsRepository newsRepository;
    public News addNews(News news){return this.newsRepository.addNews(news);}

    public News replace(News news){return this.newsRepository.replace(news);}

    public List<News> allNews(){return this.newsRepository.allNews();}
    public List<News> allNews(Integer id){return this.newsRepository.allNews(id);}

    public Integer max(){return  this.newsRepository.max();}
    public List<News> allNewsinKagtegorija(Integer id){return this.newsRepository.allNewsInKategorija( id);}
    public List<News> allNewsWithTag(String name){return this.newsRepository.allNewsWithTag( name);}
    public List<News> allNewsSortedDate(){return this.newsRepository.allNewsSortedDate();}
    public List<News> allNewsSortedClicks(){return this.newsRepository.allNewsSortedClicks();}
    public void incrementClicks(Id id){this.newsRepository.incrementClicks(id);}
    public List<News> topTen(){return this.newsRepository.topTenNews();}
    public News findNews(Integer id){return  this.newsRepository.findNewsById(id);}
    public Integer deleteNews(Integer id){return this.newsRepository.deleteNewsById(id);}
    public List<News> News_withWord(String in){return  this.newsRepository.News_withWord(in);}
}
