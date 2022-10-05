import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Slf4j

public class DogRepository {
    private static final Logger logger = LoggerFactory.getLogger(DogRepository.class);
    public static Connection getConnection() {

        Connection connection = null;
        String url = "jdbc:postgresql://localhost:5432/animals";
        String user = "postgres";
        String password = "titova92480";

        try {
            connection = DriverManager.getConnection(url, user, password);
            if (connection != null) {
                log.info("Connected to the PostgreSQL server successfully.");
                //System.out.println("Connected to the PostgreSQL server successfully.");
            } else {
                log.info("Failed to make connection!");
                //System.out.println("Failed to make connection!");
            }
        } catch (SQLException sqlException) {
            log.info(sqlException.getMessage(), sqlException);
            //System.out.println(sqlException);
        }
        return connection;
    }

    public static int save(Dog dog) {
        int status = 0;
        try {
            Connection connection = DogRepository.getConnection();
            PreparedStatement ps = connection.prepareStatement("insert into entities(name,breed,owner) values (?,?,?)");
            ps.setString(1, dog.getName());
            ps.setString(2, dog.getBreed());
            ps.setString(3, dog.getOwner());

            logger.debug("Test");
            status = ps.executeUpdate();
            connection.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return status;
    }

    public static int update(Dog dog) {

        int status = 0;

        try {
            Connection connection = DogRepository.getConnection();
            PreparedStatement ps = connection.prepareStatement("update entities set name=?,breed=?,owner=? where id=?");
            ps.setString(1, dog.getName());
            ps.setString(2, dog.getBreed());
            ps.setString(3, dog.getOwner());
            ps.setInt(4, dog.getId());

            status = ps.executeUpdate();
            connection.close();

        } catch (SQLException sqlException) {
            log.debug(sqlException.getMessage(), sqlException);
            //sqlException.printStackTrace();
        }
        return status;
    }

    public static int delete(int id) {

        int status = 0;

        try {
            Connection connection = DogRepository.getConnection();
            PreparedStatement ps = connection.prepareStatement("delete from entities where id=?");
            ps.setInt(1, id);
            status = ps.executeUpdate();

            connection.close();

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return status;
    }

    public static Dog getDogById(int id) {

        var dog = new Dog();

        try {
            Connection connection = DogRepository.getConnection();
            PreparedStatement ps = connection.prepareStatement("select * from entities where id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                dog.setId(rs.getInt(1));
                dog.setName(rs.getString(2));
                dog.setBreed(rs.getString(3));
                dog.setOwner(rs.getString(4));
            }
            connection.close();

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return dog;
    }

    public static List<Dog> getAllDogs() {

        var listDogs = new ArrayList<Dog>();

        try {
            Connection connection = DogRepository.getConnection();
            PreparedStatement ps = connection.prepareStatement("select * from entities");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                var dog = new Dog();
                dog.setId(rs.getInt(1));
                dog.setName(rs.getString(2));
                dog.setBreed(rs.getString(3));
                dog.setOwner(rs.getString(4));
                listDogs.add(dog);
            }
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listDogs;
    }

//    private static Connection Connection(){
//         Connection connection = DogRepository.getConnection();
//         return connection;
//    }

//    private static void SetParams (Dog dog, ResultSet rs) throws SQLException {
//        dog.setId(rs.getInt(1));
//        dog.setName(rs.getString(2));
//        dog.setBreed(rs.getString(3));
//        dog.setOwner(rs.getString(4));
//    }
}
