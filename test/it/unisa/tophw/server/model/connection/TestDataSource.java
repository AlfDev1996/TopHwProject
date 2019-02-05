package it.unisa.tophw.server.model.connection;

import junit.framework.TestCase;
import junit.framework.TestSuite;


import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TestDataSource extends TestCase {
    public void testAccesso() throws SQLException {
        com.mysql.jdbc.Connection x = null;
        String ip = "localhost";
        String port = "3306";
        String db = "tophw";
        String username = "root";
        String password = "";

        String url = "jdbc:mysql://"+ip+":"+port+"/"+ db;
        System.out.println(url+"<---- url db");
        x = (com.mysql.jdbc.Connection) DriverManager.getConnection(url, username, password);

        x.setAutoCommit(false);
        PreparedStatement s=x.prepareStatement("Insert into utente(nome,cognome,email,password,ruolo,nazionalita) values ('alfonso','zambelli','alf@gmail.com','pass','Admin','Italia')");
        s.executeUpdate();
//		x.rollback();
        x.commit();
        PreparedStatement l=x.prepareStatement("Select * from utente");
        ResultSet test=l.executeQuery();
        test.next();
        System.out.println(""+test.getString("nome"));
    }


    public static TestSuite suite() {
        return new TestSuite(TestDataSource.class);
    }

    public static void main(String args[]) {
        junit.textui.TestRunner.run(suite());
    }
}
