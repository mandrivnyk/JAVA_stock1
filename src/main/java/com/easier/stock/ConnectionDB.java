package com.easier.stock;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionDB {

    public Connection conn;

    public  Connection getConn() {
        return conn;
    }

    public ConnectionDB(int  dbType) throws SQLException {
        switch (dbType) {
            case 1:
                conn = cMysql();
        }

    }

    public static Connection cMysql() throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.1.69/tatonka?user=root&password=kbyerc&useLegacyDatetimeCode=false&serverTimezone=UTC");
        return conn;
    }
}
