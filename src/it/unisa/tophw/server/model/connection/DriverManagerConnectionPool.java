package it.unisa.tophw.server.model.connection;


import com.mysql.jdbc.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class DriverManagerConnectionPool {

    private static List<Connection> freeDbConnections;

    static {
        freeDbConnections = new LinkedList<Connection>();
        try {
            Class.forName("com.mysql.jdbc.Driver");

        } catch (ClassNotFoundException e) {
            System.out.println("DB driver not found:"+ e.getMessage());
        }
    }

    private static synchronized Connection createDBConnection() throws SQLException {
        Connection newConnection = null;
        String ip = "localhost";
        String port = "3306";
        String db = "tophw";
        String username = "root";
        String password = "";

        String url = "jdbc:mysql://"+ip+":"+port+"/"+ db;
        System.out.println(url+"<---- url db");
        newConnection = (Connection) DriverManager.getConnection(url, username, password);


        newConnection.setAutoCommit(true);

        System.out.println("Connessione al db eseguita");
        return newConnection;
    }


    public static synchronized Connection getConnection() throws SQLException {
        Connection connection;

        if (!freeDbConnections.isEmpty()) {
            connection = (Connection) freeDbConnections.get(0);
            freeDbConnections.remove(0);

            try {
                if (connection.isClosed())
                    connection = getConnection();
            } catch (SQLException e) {
                connection.close();
                connection = getConnection();
            }
        } else {
            connection = createDBConnection();
        }

        return connection;
    }

    public static synchronized void releaseConnection(Connection connection) throws SQLException {
        connection = (com.mysql.jdbc.Connection) connection;
        if(connection != null) freeDbConnections.add(connection);
    }

}
