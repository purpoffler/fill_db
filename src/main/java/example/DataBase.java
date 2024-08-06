package example;
import java.sql.*;

public class DataBase {
    private  String url = "jdbc:postgresql://localhost:5432/postgresdb";
    private  String user_db = "postgres";
    private  String password_db = "password";


 public User select(String login) {
        String query = "SELECT  * FROM t1 JOIN t2 using(login) WHERE t1.login = '" + login + "'";
        User user = null;
        Statement st = null;
        ResultSet rs = null;
        Connection con = null;

        try {
            con = DriverManager.getConnection(url, user_db, password_db);
            st = con.createStatement();
            rs = st.executeQuery(query);

            System.out.println("Подключение успешно, метод select выполняется");


            if (rs.next()) {
                user = new User(
                        rs.getString("login"),
                        rs.getString("password"),
                        rs.getString("date"),
                        rs.getString("email")
                );

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (st != null) st.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return user;
    }


    public int insert(User user) throws SQLException{


        int numb = 0;

        String query_t1 = "INSERT INTO t1 (login, password, date) VALUES (?, ?, ?)";
        String query_t2 = "INSERT INTO  t2 (login, email) VALUES (?, ?)";

     try(Connection connection = DriverManager.getConnection(url, user_db, password_db);
         PreparedStatement ps_t1 = connection.prepareStatement(query_t1);
         PreparedStatement ps_t2 = connection.prepareStatement(query_t2);
         ){
         System.out.println("Подключение успешно, метод insert выполняется");


             ps_t1.setString(1, user.login);
             ps_t1.setString(2, user.password);
             ps_t1.setTimestamp(3, user.date);

            numb += ps_t1.executeUpdate();

             ps_t2.setString(1, user.login);
             ps_t2.setString(2, user.email);

            numb += ps_t2.executeUpdate();
     }
    return numb;
    }







}
