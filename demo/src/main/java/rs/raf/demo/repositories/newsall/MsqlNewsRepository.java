package rs.raf.demo.repositories.newsall;

import rs.raf.demo.entities.*;
import rs.raf.demo.repositories.MySqlAbstractRepository;


import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class MsqlNewsRepository extends MySqlAbstractRepository implements NewsRepository {
    @Override
    public News addNews(News news) {
        Date currentDate = new Date();
        Timestamp timestamp = new Timestamp(currentDate.getTime());
        news.setDate(timestamp);

        Kategorija kategorija = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;


        try {
            //dodavanje kategorije
            connection = this.newConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM kategorije where title = ?");
            preparedStatement.setString(1, news.getKategorijaName());
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                news.setKategorija(resultSet.getInt("id"));
            }
            connection = null;
            preparedStatement = null;
            resultSet = null;
            //dodavanje kategorije

            //dodavanje News
            connection = this.newConnection();
            String[] generatedColumns = {"id"};
            preparedStatement = connection.prepareStatement(
                    "INSERT INTO news (title, content,id_autor,clicks,id_kategorija,date) VALUES(?,?,?,?,?,?)", generatedColumns);
            preparedStatement.setString(1, news.getTitle());
            preparedStatement.setString(2, news.getContent());
            preparedStatement.setInt(3, news.getAutor_id());
            preparedStatement.setInt(4, 0);
            preparedStatement.setInt(5, news.getKategorija());
            preparedStatement.setTimestamp(6,news.getDate());

            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                news.setId(resultSet.getInt(1));
            }
            resultSet.close();

            connection = null;
            preparedStatement = null;

            connection = this.newConnection();

            String tagovi= news.getTagovi();
            ArrayList<String>tags=new ArrayList<>();
            if(tagovi.contains(",")){
                String pom[]=tagovi.split(",");
                tags.addAll(Arrays.asList(pom));
            }
            System.out.println("TAGOVI"+tags);
            for(int i=0;i< tags.size();i++) {
                preparedStatement = connection.prepareStatement("INSERT INTO tags (id_news, name) VALUES(?, ?)");
                preparedStatement.setInt(1, news.getId());
                preparedStatement.setString(2, tags.get(i));
                preparedStatement.executeUpdate();
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

//        System.out.println("cccc"+timestamp);
//        System.out.println("cccc"+news);
        //return news;
//        return news;
        System.out.println(news);

//            String tagovi= news.getTagovi();
//            ArrayList<String>tags=new ArrayList<>();
//            if(tagovi.contains(",")){
//                String pom[]=tagovi.split(",");
//                tags.addAll(Arrays.asList(pom));
//            }
//        System.out.println(tags);
        return  news;
    }

    @Override
    public News replace(News news) {
//        Date currentDate = new Date();
//        Timestamp timestamp = new Timestamp(currentDate.getTime());
//        news.setDate(timestamp);

        Kategorija kategorija = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;


        try {
            //dodavanje kategorije
            connection = this.newConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM kategorije where title = ?");
            preparedStatement.setString(1, news.getKategorijaName());
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                news.setKategorija(resultSet.getInt("id"));
            }
            connection = null;
            preparedStatement = null;
            resultSet = null;
            //dodavanje kategorije

            //dodavanje News
            connection = this.newConnection();
            //String[] generatedColumns = {"id"};
            preparedStatement = connection.prepareStatement(
                    "UPDATE news SET title=?, content=?,id_kategorija=? WHERE id = ?");
            preparedStatement.setString(1, news.getTitle());
            preparedStatement.setString(2, news.getContent());
            preparedStatement.setInt(3, news.getKategorija());
            preparedStatement.setInt(4, news.getId());

            preparedStatement.executeUpdate();

//            resultSet = preparedStatement.getGeneratedKeys();
//            if (resultSet.next()) {
//                news.setId(resultSet.getInt(1));
//            }
//            resultSet.close();

            connection = null;
            preparedStatement = null;
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("DELETE FROM tags where id_news = ?");
            preparedStatement.setInt(1, news.getId());
            preparedStatement.executeUpdate();

            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("INSERT INTO tags (id_news, name) VALUES(?, ?)");
            preparedStatement.setInt(1,news.getId() );
            preparedStatement.setString(2,news.getTagovi() );
            preparedStatement.executeUpdate();

            System.out.println(
                    news
            );
            //resultSet.close();
            preparedStatement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            //this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }
        System.out.println(
                news
        );
        //return news;
        return news;
    }
    @Override
    public List<News> allNewsSortedDate() {
        List<News> news = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM news ORDER BY date DESC");
            while (resultSet.next()) {

                News pom=new News(resultSet.getInt("id"), resultSet.getString("title"), resultSet.getString("content")
                        , resultSet.getInt("id_autor"), resultSet.getInt("clicks"), resultSet.getInt("id_kategorija"),resultSet.getTimestamp("date"));
                System.out.println("AAAAAAAAAAAAAAa"+pom.getTitle()+pom.getContent()+pom.getKategorija());
                news.add(pom);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        //kategorije
        List<Kategorija> kategorije = new ArrayList<>();

        connection = null;
        statement = null;
        resultSet = null;
        try {
            connection = this.newConnection();

            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from kategorije");
            while (resultSet.next()) {
                Kategorija pom=new Kategorija(resultSet.getInt("id"), resultSet.getString("title"), resultSet.getString("content"));
                System.out.println("BBBBBBBBBBBBBBBBB"+pom.getContent()+" "+pom.getTitle());
                kategorije.add(pom);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        for(int i=0;i<news.size();i++){
            for(int j=0;j< kategorije.size();j++){
                if(news.get(i).getKategorija()==kategorije.get(j).getId()){
                    news.get(i).setKategorijaOBJ(kategorije.get(j));
                }
            }
        }
        //autori
        List<User> users = new ArrayList<>();

        connection = null;
        statement = null;
        resultSet = null;
        try {
            connection = this.newConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from korisnici");
            while (resultSet.next()) {
                User pom=new User(resultSet.getInt("id"),resultSet.getString("email"),resultSet.getString("ime"),resultSet.getString("prezime"),
                        resultSet.getString("tip_korisnika"),resultSet.getString("status_korisnika"),"");

                users.add(pom);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }
        for(int i=0;i<news.size();i++){
            for(int j=0;j< users.size();j++){
                if(news.get(i).getAutor_id()==users.get(j).getId()){
                    news.get(i).setUserOBJ(users.get(j));
                    news.get(i).setUsername(users.get(j).getIme()+" "+users.get(j).getPrezime());
                }
            }
        }

        System.out.println("+++ MOJE NOVINE SVE +++ "+news);
        return news;
    }
    @Override
    public List<News> allNewsWithTag(String name) {
        List<News> news = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet = null;

        ArrayList<Integer>newsIds=new ArrayList<>();
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM tags WHERE name = ?");
            preparedStatement.setString(1, name);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                newsIds.add(resultSet.getInt("id_news"));
            }
            connection = this.newConnection();

            for(int i=0;i< newsIds.size();i++) {
                preparedStatement = connection.prepareStatement("SELECT * FROM news WHERE id = ? ORDER BY date DESC");
                preparedStatement.setInt(1, newsIds.get(i));
                resultSet = preparedStatement.executeQuery();

                    while (resultSet.next()) {
                        News pom = new News(resultSet.getInt("id"), resultSet.getString("title"), resultSet.getString("content")
                                , resultSet.getInt("id_autor"), resultSet.getInt("clicks"), resultSet.getInt("id_kategorija"), resultSet.getTimestamp("date"));
                        news.add(pom);
                    }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        //kategorije
        List<Kategorija> kategorije = new ArrayList<>();

        connection = null;
        statement = null;
        resultSet = null;
        try {
            connection = this.newConnection();

            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from kategorije");
            while (resultSet.next()) {
                Kategorija pom=new Kategorija(resultSet.getInt("id"), resultSet.getString("title"), resultSet.getString("content"));
                //System.out.println("BBBBBBBBBBBBBBBBB"+pom.getContent()+" "+pom.getTitle());
                kategorije.add(pom);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        for(int i=0;i<news.size();i++){
            for(int j=0;j< kategorije.size();j++){
                if(news.get(i).getKategorija()==kategorije.get(j).getId()){
                    news.get(i).setKategorijaOBJ(kategorije.get(j));
                }
            }
        }
        //autori
        List<User> users = new ArrayList<>();

        connection = null;
        statement = null;
        resultSet = null;
        try {
            connection = this.newConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from korisnici");
            while (resultSet.next()) {
                User pom=new User(resultSet.getInt("id"),resultSet.getString("email"),resultSet.getString("ime"),resultSet.getString("prezime"),
                        resultSet.getString("tip_korisnika"),resultSet.getString("status_korisnika"),"");

                users.add(pom);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }
        for(int i=0;i<news.size();i++){
            for(int j=0;j< users.size();j++){
                if(news.get(i).getAutor_id()==users.get(j).getId()){
                    news.get(i).setUserOBJ(users.get(j));
                    news.get(i).setUsername(users.get(j).getIme()+" "+users.get(j).getPrezime());
                }
            }
        }

        System.out.println("+++ MOJE NOVINE SVE +++ "+news);
        return news;
    }
    @Override
    public List<News> allNewsInKategorija(Integer id) {
        List<News> news = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet = null;
        try {
//            connection = this.newConnection();
//
//            preparedStatement = connection.prepareStatement("SELECT * FROM news where id = ?");
//            preparedStatement.setInt(1, id);
//            resultSet = preparedStatement.executeQuery();


            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM news WHERE id_kategorija = ? ORDER BY date DESC");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
//
//            connection = this.newConnection();
//
//            statement = connection.createStatement();
//            resultSet = statement.executeQuery("SELECT * FROM news WHERE kategorija_id = ? ORDER BY date DESC");
            while (resultSet.next()) {

                News pom=new News(resultSet.getInt("id"), resultSet.getString("title"), resultSet.getString("content")
                        , resultSet.getInt("id_autor"), resultSet.getInt("clicks"), resultSet.getInt("id_kategorija"),resultSet.getTimestamp("date"));
                //System.out.println("AAAAAAAAAAAAAAa"+pom.getTitle()+pom.getContent()+pom.getKategorija());
                news.add(pom);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        //kategorije
        List<Kategorija> kategorije = new ArrayList<>();

        connection = null;
        statement = null;
        resultSet = null;
        try {
            connection = this.newConnection();

            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from kategorije");
            while (resultSet.next()) {
                Kategorija pom=new Kategorija(resultSet.getInt("id"), resultSet.getString("title"), resultSet.getString("content"));
                //System.out.println("BBBBBBBBBBBBBBBBB"+pom.getContent()+" "+pom.getTitle());
                kategorije.add(pom);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        for(int i=0;i<news.size();i++){
            for(int j=0;j< kategorije.size();j++){
                if(news.get(i).getKategorija()==kategorije.get(j).getId()){
                    news.get(i).setKategorijaOBJ(kategorije.get(j));
                }
            }
        }
        //autori
        List<User> users = new ArrayList<>();

        connection = null;
        statement = null;
        resultSet = null;
        try {
            connection = this.newConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from korisnici");
            while (resultSet.next()) {
                User pom=new User(resultSet.getInt("id"),resultSet.getString("email"),resultSet.getString("ime"),resultSet.getString("prezime"),
                        resultSet.getString("tip_korisnika"),resultSet.getString("status_korisnika"),"");

                users.add(pom);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }
        for(int i=0;i<news.size();i++){
            for(int j=0;j< users.size();j++){
                if(news.get(i).getAutor_id()==users.get(j).getId()){
                    news.get(i).setUserOBJ(users.get(j));
                    news.get(i).setUsername(users.get(j).getIme()+" "+users.get(j).getPrezime());
                }
            }
        }

       //System.out.println("+++ MOJE NOVINE SVE +++ "+news);
        return news;
    }
    @Override
    public List<News> allNewsSortedClicks() {
        List<News> news = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        Date currentDate = new Date();
        Timestamp timestamp = new Timestamp(currentDate.getTime());
        try {
            connection = this.newConnection();

            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT* FROM news WHERE date >= CURDATE() - INTERVAL 10 DAY "
                                                    + "ORDER BY clicks DESC LIMIT 5");
            while (resultSet.next()) {

                News pom=new News(resultSet.getInt("id"), resultSet.getString("title"), resultSet.getString("content")
                        , resultSet.getInt("id_autor"), resultSet.getInt("clicks"), resultSet.getInt("id_kategorija"),resultSet.getTimestamp("date"));
                System.out.println("AAAAAAAAAAAAAAa"+pom.getTitle()+pom.getContent()+pom.getKategorija());
                news.add(pom);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        //kategorije
        List<Kategorija> kategorije = new ArrayList<>();

        connection = null;
        statement = null;
        resultSet = null;
        try {
            connection = this.newConnection();

            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from kategorije");
            while (resultSet.next()) {
                Kategorija pom=new Kategorija(resultSet.getInt("id"), resultSet.getString("title"), resultSet.getString("content"));
                System.out.println("BBBBBBBBBBBBBBBBB"+pom.getContent()+" "+pom.getTitle());
                kategorije.add(pom);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        for(int i=0;i<news.size();i++){
            for(int j=0;j< kategorije.size();j++){
                if(news.get(i).getKategorija()==kategorije.get(j).getId()){
                    news.get(i).setKategorijaOBJ(kategorije.get(j));
                }
            }
        }
        //autori
        List<User> users = new ArrayList<>();

        connection = null;
        statement = null;
        resultSet = null;
        try {
            connection = this.newConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from korisnici");
            while (resultSet.next()) {
                User pom=new User(resultSet.getInt("id"),resultSet.getString("email"),resultSet.getString("ime"),resultSet.getString("prezime"),
                        resultSet.getString("tip_korisnika"),resultSet.getString("status_korisnika"),"");
                users.add(pom);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }
        for(int i=0;i<news.size();i++){
            for(int j=0;j< users.size();j++){
                if(news.get(i).getAutor_id()==users.get(j).getId()){
                    news.get(i).setUserOBJ(users.get(j));
                }
            }
        }

        System.out.println("+++ MOJE NOVINE SVE +++ "+news);
        return news;
    }
    @Override
    public void incrementClicks(Id id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
       // ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("UPDATE news SET clicks = clicks + 1 WHERE id = ?");
            preparedStatement.setInt(1, id.getId());
            preparedStatement.executeUpdate();


          //  resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            //this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }
        //Kategorija kk=kategorija;
        //System.out.println("\nPROBICA "+kk.getId()+kk.getTitle()+kk.getContent());


    }
    @Override
    public List<News> allNews() {
        List<News> news = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM news");
            while (resultSet.next()) {

                News pom=new News(resultSet.getInt("id"), resultSet.getString("title"), resultSet.getString("content")
                        , resultSet.getInt("id_autor"), resultSet.getInt("clicks"), resultSet.getInt("id_kategorija"),resultSet.getTimestamp("date"));
                //System.out.println("AAAAAAAAAAAAAAa"+pom.getTitle()+pom.getContent()+pom.getKategorija());
                news.add(pom);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        //kategorije
        List<Kategorija> kategorije = new ArrayList<>();

        connection = null;
        statement = null;
        resultSet = null;
        try {
            connection = this.newConnection();

            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from kategorije");
            while (resultSet.next()) {
                Kategorija pom=new Kategorija(resultSet.getInt("id"), resultSet.getString("title"), resultSet.getString("content"));
                //System.out.println("BBBBBBBBBBBBBBBBB"+pom.getContent()+" "+pom.getTitle());
                kategorije.add(pom);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        for(int i=0;i<news.size();i++){
            for(int j=0;j< kategorije.size();j++){
                if(news.get(i).getKategorija()==kategorije.get(j).getId()){
                    news.get(i).setKategorijaOBJ(kategorije.get(j));
                }
            }
        }
        //autori
        List<User> users = new ArrayList<>();

        connection = null;
        statement = null;
        resultSet = null;
        try {
            connection = this.newConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from korisnici");
            while (resultSet.next()) {
                User pom=new User(resultSet.getInt("id"),resultSet.getString("email"),resultSet.getString("ime"),resultSet.getString("prezime"),
                        resultSet.getString("tip_korisnika"),resultSet.getString("status_korisnika"),"");
                users.add(pom);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }
        for(int i=0;i<news.size();i++){
            for(int j=0;j< users.size();j++){
                if(news.get(i).getAutor_id()==users.get(j).getId()){
                    news.get(i).setUserOBJ(users.get(j));
                }
            }
        }

        //System.out.println("+++ MOJE NOVINE SVE +++ "+news);
        return news;
    }
    @Override
    public List<News> allNews(Integer id){
        List<News> news = new ArrayList<>();
        int limit=2;
        id=id-1;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM news LIMIT 2 OFFSET "+id*limit);
            while (resultSet.next()) {

                News pom=new News(resultSet.getInt("id"), resultSet.getString("title"), resultSet.getString("content")
                        , resultSet.getInt("id_autor"), resultSet.getInt("clicks"), resultSet.getInt("id_kategorija"),resultSet.getTimestamp("date"));
                //System.out.println("AAAAAAAAAAAAAAa"+pom.getTitle()+pom.getContent()+pom.getKategorija());
                news.add(pom);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        //kategorije
        List<Kategorija> kategorije = new ArrayList<>();

        connection = null;
        statement = null;
        resultSet = null;
        try {
            connection = this.newConnection();

            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from kategorije");
            while (resultSet.next()) {
                Kategorija pom=new Kategorija(resultSet.getInt("id"), resultSet.getString("title"), resultSet.getString("content"));
                //System.out.println("BBBBBBBBBBBBBBBBB"+pom.getContent()+" "+pom.getTitle());
                kategorije.add(pom);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        for(int i=0;i<news.size();i++){
            for(int j=0;j< kategorije.size();j++){
                if(news.get(i).getKategorija()==kategorije.get(j).getId()){
                    news.get(i).setKategorijaOBJ(kategorije.get(j));
                }
            }
        }
        //autori
        List<User> users = new ArrayList<>();

        connection = null;
        statement = null;
        resultSet = null;
        try {
            connection = this.newConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from korisnici");
            while (resultSet.next()) {
                User pom=new User(resultSet.getInt("id"),resultSet.getString("email"),resultSet.getString("ime"),resultSet.getString("prezime"),
                        resultSet.getString("tip_korisnika"),resultSet.getString("status_korisnika"),"");
                users.add(pom);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }
        for(int i=0;i<news.size();i++){
            for(int j=0;j< users.size();j++){
                if(news.get(i).getAutor_id()==users.get(j).getId()){
                    news.get(i).setUserOBJ(users.get(j));
                }
            }
        }

        //System.out.println("+++ MOJE NOVINE SVE +++ "+news);
        return news;
    }
    @Override
    public Integer max() {
        int count=0;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT COUNT(*) AS count FROM news");

            if(resultSet.next()) count=resultSet.getInt("count");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        //System.out.println("+++ MOJE NOVINE SVE +++ "+news);
        return count;
    }
    @Override
    public List<News> topTenNews() {
        List<News> news = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = this.newConnection();

            statement = connection.createStatement();
            //SELECT column FROM table LIMIT 10
            resultSet = statement.executeQuery("select * from (select * from news order by date desc) limit 10");
            while (resultSet.next()) {
                News pom=new News(resultSet.getInt("id"), resultSet.getString("title"), resultSet.getString("content")
                        , resultSet.getInt("autor_id"), resultSet.getInt("clicks"), resultSet.getInt("id_kategorija"),resultSet.getTimestamp("date"));
                news.add(pom);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return news;
    }
    @Override
    public News findNewsById(Integer id) {
        News news = null;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM news where id = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int newId = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String content = resultSet.getString("content");
                int autor = resultSet.getInt("id_autor");
                int clicks = resultSet.getInt("clicks");
                int kategorija = resultSet.getInt("id_kategorija");
                Timestamp date=resultSet.getTimestamp("date");

                news = new News(id, title, content, autor, clicks, kategorija,date);
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }
        //kategorije
        List<Kategorija> kategorije = new ArrayList<>();

        Connection connection1 = null;
        Statement statement1 = null;
        ResultSet resultSet1 = null;

        try {
            connection1 = this.newConnection();
            statement1 = connection1.createStatement();
            resultSet1 = statement1.executeQuery("select * from kategorije");
            while (resultSet1.next()) {
                Kategorija pom=new Kategorija(resultSet1.getInt("id"), resultSet1.getString("title"), resultSet1.getString("content"));
                System.out.println("BBBBBBBBBBBBBBBBB"+pom.getContent()+" "+pom.getTitle());
                kategorije.add(pom);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(statement1);
            this.closeResultSet(resultSet1);
            this.closeConnection(connection1);
        }

        for(int i=0;i< kategorije.size();i++){
            if(kategorije.get(i).getId()==news.getKategorija()){
                news.setKategorijaOBJ(kategorije.get(i));
            }
        }
        //koments
        ArrayList<Coment> coments = new ArrayList<>();

        connection = null;
        preparedStatement = null;
        resultSet = null;
        try {
            connection = this.newConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM comments where id_news = ?");
            preparedStatement.setInt(1, id);
            resultSet=preparedStatement.executeQuery();
            if(resultSet.next()) {
                coments.add(new Coment(resultSet.getInt("id"),resultSet.getString("content"),resultSet.getString("autor_name"),resultSet.getTimestamp("date"),resultSet.getInt("id_news")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }
        System.out.println(coments);
        news.setKomentari(coments);

//autori
        User user = null;

         connection = null;
         preparedStatement = null;
         resultSet = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM korisnici where id = ?");
            preparedStatement.setInt(1, news.getAutor_id());
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                int idU=resultSet.getInt("id");
                String userEmail = resultSet.getString("email");
                String ime = resultSet.getString("ime");
                String prezime = resultSet.getString("prezime");
                String type = resultSet.getString("tip_korisnika");
                String aktivnost = resultSet.getString("status_korisnika");
                String sifra= "";
                user = new User(idU,userEmail,ime,prezime,type,aktivnost,sifra);
                news.setUserOBJ(user);
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return news;
    }
    @Override
    public Integer deleteNewsById(Integer id) {
        int count=0;
        Connection connection = null;
        Statement statement= null;
        ResultSet resultSet=null;
        PreparedStatement preparedStatement = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("DELETE FROM news where id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

            connection = this.newConnection();

            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT COUNT(*) AS count FROM news");
            if (resultSet.next()) {
                count=resultSet.getInt("count");
            }

            preparedStatement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }
        System.out.println("COUNT"+count);
        return count;
    }
    @Override
    public List<News> News_withWord(String in) {
        List<News> news = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM news where content like '%"+in+"%' || where title like '%\"+in+\"%'");
            System.out.println("SELECT * FROM news where content like '%"+in+"%'");

            while (resultSet.next()) {

                News pom=new News(resultSet.getInt("id"), resultSet.getString("title"), resultSet.getString("content")
                        , resultSet.getInt("id_autor"), resultSet.getInt("clicks"), resultSet.getInt("id_kategorija"),resultSet.getTimestamp("date"));
                System.out.println("asdasdas  ==="+in);
                news.add(pom);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return news;
    }

}
