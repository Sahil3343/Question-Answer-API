package com.sahilmahajan.applanding.Repo;

import java.sql.Connection;
import java.sql.SQLException;

public class SQLInstance {
    private final SQLConnection connect = new SQLConnection("jdbc:sqlserver://applanding.database.windows.net;database=applanding;encrypt=true;", "", "");
    private final Connection con = connect.GetConnection();

    public SQLInstance() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
    }

    public Connection getCon() {
        return con;
    }

    public SQLConnection getConnect() {
        return connect;
    }

}
