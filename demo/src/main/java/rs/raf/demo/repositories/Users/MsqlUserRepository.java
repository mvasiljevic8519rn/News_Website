package rs.raf.demo.repositories.Users;

import org.apache.commons.codec.digest.DigestUtils;
import rs.raf.demo.entities.Subject;
import rs.raf.demo.entities.User;
import rs.raf.demo.repositories.MySqlAbstractRepository;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class MsqlUserRepository extends MySqlAbstractRepository implements UserRepository {
    @Override
    public User findUser(String email) {
        User user = null;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM korisnici where email = ?");
            preparedStatement.setString(1, email);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                int id=resultSet.getInt("id");
                String userEmail = resultSet.getString("email");
                String ime = resultSet.getString("ime");
                String prezime = resultSet.getString("prezime");
                String type = resultSet.getString("tip_korisnika");
                String aktivnost = resultSet.getString("status_korisnika");
                String sifra= resultSet.getString("sifra");
                user = new User(id,userEmail,ime,prezime,type,aktivnost,sifra);
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
        return user;
    }

    public User getUser(Integer id) {
        User user = null;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM korisnici where id = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                int USERid=resultSet.getInt("id");
                String userEmail = resultSet.getString("email");
                String ime = resultSet.getString("ime");
                String prezime = resultSet.getString("prezime");
                String type = resultSet.getString("tip_korisnika");
                String aktivnost = resultSet.getString("status_korisnika");
                //String sifra= resultSet.getString("sifra");
                user = new User(USERid,userEmail,ime,prezime,type,aktivnost);
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
        return user;
    }

    public boolean checkEmail(String Email){
        int count=0;
        //boolean state=false;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT COUNT(*) AS Mycount FROM korisnici WHERE email = ?");
            preparedStatement.setString(1, Email);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                count=resultSet.getInt("Mycount");
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
        if(count==0){
            return true;
        }
        return false;
    }
    @Override
    public void ActivateDeactivate(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        //ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            //preparedStatement = connection.prepareStatement("SELECT * FROM korisnici where id = ?");
            preparedStatement = connection.prepareStatement("UPDATE korisnici SET status_korisnika = CASE " +
                                                                "    WHEN status_korisnika = 'aktivan' THEN 'neaktivan' " +
                                                                "    WHEN status_korisnika = 'neaktivan' THEN 'aktivan' " +
                                                                "    ELSE status_korisnika  END " +
                                                                "    WHERE id = ? AND tip_korisnika <> 'admin' ");

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
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
    }
    @Override
    public User addUser(User user) {

        String secretKey = "secret";
        String message = "123";

        String hashedPassword = DigestUtils.sha256Hex(user.getHashedPassword());
        System.out.println("SIFRA"+hashedPassword);



        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();
            String[] generatedColumns = {"id"};

            preparedStatement = connection.prepareStatement("INSERT INTO korisnici (email, ime, prezime, tip_korisnika,status_korisnika,sifra) VALUES(?,?,?,?,?,?)");
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getIme());
            preparedStatement.setString(3,user.getPrezime());
            preparedStatement.setString(4,user.getTip());
            preparedStatement.setString(5,"aktivan");
            preparedStatement.setString(6,hashedPassword);

            preparedStatement.executeUpdate();
//            resultSet = preparedStatement.getGeneratedKeys();
//            if (resultSet.next()) {
//                user.setId(resultSet.getInt(1));
//            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            //this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }
        System.out.println(user);

        return user;
    }

    @Override
    public User editUser(User user) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        //ResultSet resultSet = null;

        try {
            connection = this.newConnection();
            String[] generatedColumns = {"id"};

            preparedStatement = connection.prepareStatement("UPDATE korisnici set email =?, ime=?, prezime=?, tip_korisnika=? WHERE  id = ?");
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getIme());
            preparedStatement.setString(3,user.getPrezime());
            preparedStatement.setString(4,user.getTip());
            preparedStatement.setInt(5,user.getId());

            preparedStatement.executeUpdate();
//            resultSet = preparedStatement.getGeneratedKeys();
//            if (resultSet.next()) {
//                user.setId(resultSet.getInt(1));
//            }
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
        System.out.println(user);

        return user;
    }

    @Override
    public void deleteUser(String email) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("DELETE FROM korisnici where email = ?");
            preparedStatement.setString(1, email);
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
    public List<User> allUsers() {
        List<User> users = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from korisnici");
            while (resultSet.next()) {
                User pom=new User(resultSet.getInt("id"),resultSet.getString("email"),resultSet.getString("ime"),resultSet.getString("prezime"),
                        resultSet.getString("tip_korisnika"),resultSet.getString("status_korisnika"),resultSet.getString("sifra"));
                users.add(pom);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return users;
    }
}
