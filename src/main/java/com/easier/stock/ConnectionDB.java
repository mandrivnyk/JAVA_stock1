package com.easier.stock;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionDB {

    public Connection conn;
    private static ConnectionDB dbIsntance;

    public ConnectionDB(int  dbType) throws SQLException {
        switch (dbType) {
            case 1:
                conn = cMysql();
        }

    }

    public  Connection getConn() {
        return conn;
    }

    public static ConnectionDB getInstance() throws SQLException {
        if(dbIsntance==null){
            dbIsntance= new ConnectionDB(1);
        }
        return dbIsntance;
    }



    public static Connection cMysql() throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.1.69/tatonka?user=root&password=kbyerc&useLegacyDatetimeCode=false&serverTimezone=UTC");
        return conn;
    }
}
