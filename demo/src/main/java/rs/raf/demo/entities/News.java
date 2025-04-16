package rs.raf.demo.entities;

import java.sql.Timestamp;
import java.util.Date;
import java.time.DateTimeException;
import java.util.ArrayList;

public class News {
    private int id;
    private String title;
    private String content;
    private  int clicks;
    private int kategorija;

    private ArrayList<Coment>komentari;
    private int autor_id;

    private Kategorija kategorijaOBJ;

    private String KategorijaName;

    private String tagovi;
    private ArrayList<String> tags;
    private User userOBJ;

    private String username;
    private Timestamp date;
    //private String date;
    public News(){}

    public News(int id, String title,int kategorija, String content, int autor_id) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.kategorija = kategorija;
        this.autor_id = autor_id;
        komentari=new ArrayList<>();
        tags=new ArrayList<>();
    }
    //id,title,content,tagovi,kategorijaName
    public News(int id, String title,String KategorijaName, String content,String tagovi) {
        this.id = id;
        this.title = title;
        this.KategorijaName = KategorijaName;
        this.content = content;
        komentari=new ArrayList<>();
        this.tagovi=tagovi;
    }
    public News(int id, String title,String KategorijaName, String content, int autor_id,String tagovi) {
        this.id = id;
        this.title = title;
        this.KategorijaName = KategorijaName;
        this.content = content;
        this.autor_id = autor_id;
        komentari=new ArrayList<>();
        this.tagovi=tagovi;
    }
    public News(int id, String title, String content, int autor_id, int clicks, int kategorija,Timestamp date) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.clicks = clicks;
        this.kategorija = kategorija;
        this.autor_id = autor_id;
        this.date = date;
        komentari=new ArrayList<>();
    }

    public String getKategorijaName() {
        return KategorijaName;
    }

    public void setKategorijaName(String kategorijaName) {
        KategorijaName = kategorijaName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTagovi() {
        return tagovi;
    }

    public void setTagovi(String tagovi) {
        this.tagovi = tagovi;
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }

    public User getUserOBJ() {
        return userOBJ;
    }

    public void setUserOBJ(User userOBJ) {
        this.userOBJ = userOBJ;
    }

    public ArrayList<Coment> getKomentari() {
        return komentari;
    }

    public void setKomentari(ArrayList<Coment> komentari) {
        this.komentari = komentari;
    }

    public Kategorija getKategorijaOBJ() {
        return kategorijaOBJ;
    }

    public void setKategorijaOBJ(Kategorija kategorijaOBJ) {
        this.kategorijaOBJ = kategorijaOBJ;
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
    public Integer getClick() {
        return clicks;
    }

    public void setClick(Integer clicks) {
        this.clicks = clicks;
    }

    public void incerementClicks(){
        clicks=clicks+1;
    }
    public int getAutor_id() {
        return autor_id;
    }

    public void setAutor_id(int autor_id) {
        this.autor_id = autor_id;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public int getKategorija() {
        return kategorija;
    }

    public void setKategorija(int kategorija) {
        this.kategorija = kategorija;
    }

    public int getClicks() {
        return clicks;
    }

    public void setClicks(int clicks) {
        this.clicks = clicks;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "News{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", clicks=" + clicks +
                ", kategorija=" + kategorija +
                ", komentari=" + komentari +
                ", autor_id=" + autor_id +
                ", kategorijaOBJ=" + kategorijaOBJ +
                ", KategorijaName='" + KategorijaName + '\'' +
                ", tagovi='" + tagovi + '\'' +
                ", tags=" + tags +
                ", userOBJ=" + userOBJ +
                ", date=" + date +
                '}'+"\n";
    }
}
