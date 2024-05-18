import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class second {

    public void postQuery(String surname, String name, String classNumber, String classLiter, Connection conn) {
        try {
            // Создаем запрос на вставку новой строки в базу данных
            String postQuery = "INSERT INTO users_db (surname, name, class, liter) VALUES (?, ?, ?, ?)";

            // Подготавливаем запрос с параметрами
            PreparedStatement pstmt = conn.prepareStatement(postQuery);
            pstmt.setString(1, surname);
            pstmt.setString(2, name);
            pstmt.setString(3, classNumber);
            pstmt.setString(4, classLiter);

            // Выполняем запрос на добавление новой строки
            int rowsAffected = pstmt.executeUpdate();

            // Проверяем, была ли успешно добавлена новая строка
            if (rowsAffected > 0) {
                System.out.println("Новая строка успешно добавлена в базу данных.");
            } else {
                System.out.println("Ошибка при добавлении новой строки в базу данных.");
            }

            // Закрываем ресурсы
            pstmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        ArrayList<User> users = new ArrayList<>();

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        String inName = null, inSurname = null, inClassNumber = null, inClassLiter = null;


        try {
            // Подключение к базе данных
            String dbURL = "jdbc:postgresql://localhost:5432/postgres";
            String user = "postgres";
            String pass = "postgres9k";

            conn = DriverManager.getConnection(dbURL, user, pass);

            if (conn != null) {
                System.out.println("Connected to database\n TEST 1 SUCCESSFULL");

                // Создание запроса SQL
                stmt = conn.createStatement();
                String getQuery = "SELECT * FROM users_db";

                rs = stmt.executeQuery(getQuery);
                while (rs.next()) {
                    String surname = rs.getString("surname");
                    String name = rs.getString("name");
                    String classNumber = rs.getString("class");
                    String classLiter = rs.getString("liter");



                    System.out.println("surname " + surname + ", Name: " + name + " Class: " + classNumber + classLiter);
                }



            }
        }

        catch (SQLException ex) {
            ex.printStackTrace();
        }

        finally {
            // Закрытие ресурсов
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

}
