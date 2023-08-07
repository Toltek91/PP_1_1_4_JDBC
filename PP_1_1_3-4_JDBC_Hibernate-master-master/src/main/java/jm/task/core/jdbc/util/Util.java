package jm.task.core.jdbc.util;


import com.mysql.cj.MysqlConnection;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;


public class Util {

    private static final String url = "jdbc:mysql://localhost:3306/users";
    private static final String user = "admin";
    private static final String password = "admin";



    public  static  Connection getConnection()  {
        Connection con = null;
        try {
            con = DriverManager.getConnection(url, user, password);
            if (!con.isClosed()) {
                System.out.println("Соединение с БД установлено");

            }
        } catch (SQLException s) {
            System.err.println("Не удалось загрузить класс драйвера БД");
        }
        return con;
    }

}





