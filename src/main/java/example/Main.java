package example;

import java.sql.SQLException;


public class Main {
    public static void main(String[] args) throws SQLException {

        DataBase db = new DataBase();
        User user = new User("userJava", "exampleJava", "2024-08-07 00:00:00", "userJava@example.com");

        String userGet = String.valueOf(db.select("user4"));
        System.out.println(userGet);

        int numbLine = db.insert(user);
        System.out.println(numbLine);
    }
}