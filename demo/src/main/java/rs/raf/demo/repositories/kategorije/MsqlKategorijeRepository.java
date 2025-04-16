package rs.raf.demo.repositories.kategorije;

import rs.raf.demo.entities.Kategorija;
import rs.raf.demo.entities.Subject;
import rs.raf.demo.repositories.MySqlAbstractRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MsqlKategorijeRepository extends MySqlAbstractRepository implements  KategorijeRepository{
    @Override
    public Integer max() {
        int count=0;
        List<Kategorija> kategorije = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT COUNT(*) AS count FROM kategorije");
            if (resultSet.next()) {
                count=resultSet.getInt("count");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return count;
    }
    @Override
    public Kategorija addKategorija(Kategorija kategorija) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            String[] generatedColumns = {"id"};
            preparedStatement = connection.prepareStatement("SELECT COUNT(title) AS count FROM kategorije where title = ?");
            preparedStatement.setString(1, kategorija.getTitle());
            resultSet = preparedStatement.executeQuery();
            int count=0;
            if(resultSet.next()) {
                count = resultSet.getInt("count");
            }

            System.out.println("AAAA"+count);

            resultSet=null;
            preparedStatement=null;
            connection=null;
            if(count==0) {
                connection = this.newConnection();

                preparedStatement = connection.prepareStatement("INSERT INTO kategorije (title, content) VALUES(?, ?)", generatedColumns);
                preparedStatement.setString(1, kategorija.getTitle());
                preparedStatement.setString(2, kategorija.getContent());
                preparedStatement.executeUpdate();
                resultSet = preparedStatement.getGeneratedKeys();

                if (resultSet.next()) {
                    kategorija.setId(resultSet.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }
        return  kategorija;
    }
    @Override
    public Integer deleteKategorija(Integer id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Statement statement=null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT COUNT(id_kategorija) AS count FROM news where id_kategorija = ?");
            preparedStatement.setInt(1,id);
            resultSet = preparedStatement.executeQuery();
            int count=0;
            if(resultSet.next()) {
                count = resultSet.getInt("count");
            }

            System.out.println("moj count kategorija news AAAA = "+count);
            resultSet.close();


            connection = null;
            preparedStatement = null;

            if(count==0){
                connection = this.newConnection();

                preparedStatement = connection.prepareStatement("DELETE FROM kategorije where id = ?");
                preparedStatement.setInt(1, id);
                preparedStatement.executeUpdate();

                preparedStatement.close();
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //this.closeResultSet(resultSet);
            this.closeStatement(preparedStatement);
            this.closeConnection(connection);
        }
        int count=0;
        List<Kategorija> kategorije = new ArrayList<>();

         connection = null;
         statement = null;
         resultSet = null;
        try {
            connection = this.newConnection();

            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT COUNT(*) AS count FROM kategorije");
            if (resultSet.next()) {
                count=resultSet.getInt("count");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return count;
    }
    @Override
    public String getKategorijaName(Integer id) {
        return null;
    }
    @Override
    public List<Kategorija> getKategorije() {
        List<Kategorija> kategorije = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
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

        return kategorije;    }

    @Override
    public List<Kategorija> getKategorije(Integer id) {
        List<Kategorija> kategorije = new ArrayList<>();

        int limit=2;
        id=id-1;

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from kategorije LIMIT 2 OFFSET "+id*limit);
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

        return kategorije;    }

    @Override
    public Kategorija replaceKategorija(Kategorija kategorija) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("UPDATE kategorije SET title = ?, content = ? WHERE id = ?");
            preparedStatement.setString(1, kategorija.getTitle());
            preparedStatement.setString(2, kategorija.getContent());
            preparedStatement.setInt(3, kategorija.getId());
            preparedStatement.executeUpdate();


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
        //Kategorija kk=kategorija;
        //System.out.println("\nPROBICA "+kk.getId()+kk.getTitle()+kk.getContent());


        return kategorija;

    }
    @Override
    public Kategorija getKategorija(Integer id) {
        Kategorija kategorija = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM kategorije where id = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                int kategorijaId = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String content = resultSet.getString("content");
                kategorija = new Kategorija(kategorijaId, title, content);
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
        return kategorija;
    }
    @Override
    public List<String> getAllKategorijaNames() {
        List<String> kategorijaNames = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            statement = connection.createStatement();
            resultSet = statement.executeQuery("select title from kategorije");
            while (resultSet.next()) {
                String pom=resultSet.getString("title");
                kategorijaNames.add(pom);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return kategorijaNames;

    }




}
