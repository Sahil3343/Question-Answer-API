package com.sahilmahajan.applanding.Repo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLConnection {

    String url;
    String username;
    String password;


    //Storing the DB details in variables
    public SQLConnection(String url, String username, String password) {
        super();
        this.url = url;
        this.username = username;
        this.password = password;
    }

    //Establishing a connection with the database
    public Connection GetConnection() throws ClassNotFoundException, SQLException, IllegalAccessException, InstantiationError, InstantiationException {
        Connection con = null;
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
        con = DriverManager.getConnection(url, username, password);
        //System.out.println("|----------Connected to DB----------|");
        return con;
    }

    //Closing the connection with the database
    public void CloseConnection(Connection con, Statement statement) throws SQLException {
        //System.out.println("|----------Connection Closed----------|");
        statement.close();
        con.close();
    }
}

