package rs.raf.demo.repositories.tags;

import rs.raf.demo.entities.Subject;
import rs.raf.demo.entities.Tag;
import rs.raf.demo.repositories.MySqlAbstractRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MsqlTagRepository extends MySqlAbstractRepository implements TagRepository {
    @Override
    public List<Tag> tagsFromNews(int id) {
        List<Tag> tags = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Statement statement = null;

        ResultSet resultSet = null;
        try {
            connection = this.newConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from tags where id_news = "+id);

            while (resultSet.next()) {
                Tag pom=new Tag(resultSet.getInt("id_news"), resultSet.getString("name"));
                tags.add(pom);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }
        System.out.println("");
        return tags;
    }

    @Override
    public void deleteTagsFromNews(int id) {

    }

    @Override
    public void addTags(int id, String tags) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {

                connection = this.newConnection();
                preparedStatement = connection.prepareStatement("INSERT INTO tags (id_news, name) VALUES(?, ?)");
                preparedStatement.setInt(1,id );
                preparedStatement.setString(2,tags );
                preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }
    }

}
