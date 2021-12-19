package ConnectionTest;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionDB {

    private final String url;
    private final String user;
    private final String password;

    private Connection postgresConnection;

    public ConnectionDB(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    public void init() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            postgresConnection = DriverManager.getConnection(url, user, password);
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ResultSet resultSetQuery(String query) {
        try {
            Statement stmt = postgresConnection.createStatement();
            return stmt.executeQuery(query);
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void prepareSetQuery(String sql, String username, String email, String telephone, String group, String password) {
        try {
            PreparedStatement pst = postgresConnection.prepareStatement(sql);
            pst.setString(1, username);
            pst.setString(2, email);
            pst.setString(3, telephone);
            pst.setString(4, group);
            pst.setString(5, password);
            pst.execute();
            pst.close();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public void prepareQuery(String sql) {
        try {
            PreparedStatement pst = postgresConnection.prepareStatement(sql);
            pst.execute();
            pst.close();
        } catch(SQLException e) {
            e.printStackTrace();
        }

    }

}
