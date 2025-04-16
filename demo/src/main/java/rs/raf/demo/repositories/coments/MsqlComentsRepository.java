package rs.raf.demo.repositories.coments;

import rs.raf.demo.entities.Coment;
import rs.raf.demo.entities.Kategorija;
import rs.raf.demo.entities.Subject;
import rs.raf.demo.repositories.MySqlAbstractRepository;

import javax.xml.stream.events.Comment;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MsqlComentsRepository extends MySqlAbstractRepository implements ComentsRepository {

    @Override
    public Integer max(Integer id) {
        int count=0;

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT COUNT(*) AS count FROM comments where id_news ="+id);
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
    public Coment addComent(Coment coment) {
        java.util.Date currentDate = new Date();
        Timestamp timestamp = new Timestamp(currentDate.getTime());
        coment.setDate(timestamp);

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();
            String[] generatedColumns = {"id"};

            preparedStatement = connection.prepareStatement("INSERT INTO comments (id_news,content,autor_name,date) VALUES(?,?,?,?)", generatedColumns);
            preparedStatement.setInt(1, coment.getNewsId());
            preparedStatement.setString(2, coment.getContent());
            preparedStatement.setString(3, coment.getAutor());
            preparedStatement.setTimestamp(4,coment.getDate() );

            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                coment.setId(resultSet.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return coment;
//        System.out.println(coment);
//        System.out.println("bla bla bla blas");
//        return null;
    }

    @Override
    public void deleteComent(Integer comentId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("DELETE FROM comments where id = ?");
            preparedStatement.setInt(1, comentId);
            preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeConnection(connection);
        }

    }

    @Override
    public void deleteAllComents(Integer newsID) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("DELETE FROM comments where id_news = ?");
            preparedStatement.setInt(1, newsID);
            preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeConnection(connection);
        }

    }

    @Override
    public Coment getComentWihtId(Integer comentId) {
        Coment coment= null;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM comments where id = ?");
            preparedStatement.setInt(1, comentId);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                int Id = resultSet.getInt("id");
                String content = resultSet.getString("content");
                String autor = resultSet.getString("autor");
                int news_id = resultSet.getInt("id_news");
                Timestamp date=resultSet.getTimestamp("date");

                coment = new Coment(Id,content,autor,date);

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

        return coment;
    }

    @Override
    public List<Coment> getAllComentFromNewsWithId(Integer newsId,Integer page) {

        List<Coment> coments = new ArrayList<>();
        int limit=2;
        page=page-1;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM comments WHERE id_news = ? ORDER BY date DESC LIMIT 2 OFFSET ?");
            preparedStatement.setInt(1, newsId);
            preparedStatement.setInt(2, page*limit);

            resultSet=preparedStatement.executeQuery();
            while(resultSet.next()) {
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
        return coments;

    }
}
