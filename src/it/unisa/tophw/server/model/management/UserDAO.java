package it.unisa.tophw.server.model.management;

import it.unisa.tophw.server.model.beans.UserBean;
import it.unisa.tophw.server.model.connection.DriverManagerConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDAO {

    public synchronized UserBean doRetriveByEmailAndPassword(String email, String password)  {

        Connection conn = null;
        PreparedStatement ps = null;
        UserBean utente =null;
        try {
            conn = (Connection) DriverManagerConnectionPool.getConnection();
            ps=(PreparedStatement) conn.prepareStatement("SELECT * from utente where email = ? and password = ?");
            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet res =ps.executeQuery();

            //Prendo il risultato dalla query

            if(res.next()) {
                utente = new UserBean();
                utente.setId_utente(res.getInt("id_utente"));
                utente.setNome(res.getString("nome"));
                utente.setCognome(res.getString("cognome"));
                utente.setEmail(res.getString("email"));
                utente.setPassword(res.getString("password"));
                utente.setRuolo(res.getString("ruolo"));

                return utente;
            }
        }catch(SQLException ex) {
            ex.printStackTrace();
        }finally{
            try {
                ps.close();
                DriverManagerConnectionPool.releaseConnection((com.mysql.jdbc.Connection) conn);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return null;
    }


    public synchronized UserBean doRetriveByEmail(String email)  {

        Connection conn = null;
        PreparedStatement ps = null;
        UserBean utente =null;
        try {
            conn = DriverManagerConnectionPool.getConnection();
            ps=(PreparedStatement) conn.prepareStatement("SELECT * from utente where email = ?");
            ps.setString(1, email);


            ResultSet res =ps.executeQuery();

            //Prendo il risultato dalla query

            if(res.next()) {
                utente = new UserBean();
                utente.setId_utente(res.getInt("id_utente"));
                utente.setNome(res.getString("nome"));
                utente.setCognome(res.getString("cognome"));
                utente.setEmail(res.getString("email"));
                utente.setPassword(res.getString("password"));
                utente.setRuolo(res.getString("ruolo"));

            }
        }catch(SQLException ex) {
            ex.printStackTrace();
        }finally{
            try {
                ps.close();
                DriverManagerConnectionPool.releaseConnection((com.mysql.jdbc.Connection) conn);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return utente;
    }

    public synchronized ArrayList<UserBean> doRetrieveAll(String orderBy){
        ArrayList<UserBean> utenti = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        UserBean utente = null ;

        String sqlSelect = "select * from utente ";
        if(orderBy!=null && (orderBy.equalsIgnoreCase("id_utente") || orderBy.equalsIgnoreCase("nome") || orderBy.equalsIgnoreCase("cognome") || orderBy.equalsIgnoreCase("email") ) )
            sqlSelect+="order by "+orderBy;

        try {
            connection = (Connection) DriverManagerConnectionPool.getConnection();
            preparedStatement = (PreparedStatement) connection.prepareStatement(sqlSelect);

            ResultSet res = preparedStatement.executeQuery();

            while(res.next()) {

                utente=new UserBean();
                utente.setId_utente(res.getInt("id_utente"));
                utente.setNome(res.getString("nome"));
                utente.setCognome(res.getString("cognome"));
                utente.setEmail(res.getString("email"));
                utente.setPassword(res.getString("password"));
                utente.setRuolo(res.getString("ruolo"));
                utenti.add(utente);

            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            try {
                preparedStatement.close();
                DriverManagerConnectionPool.releaseConnection((com.mysql.jdbc.Connection) connection);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return utenti;
    }

    public synchronized boolean doSave(UserBean utente) {
        if(utente!=null)
        {
            Connection connection = null;
            PreparedStatement preparedStatement = null;
            String sqlInsert = "Insert into utente (nome,cognome,email,password,ruolo) values (?,?,?,?,?) ";

            try {

                connection = (Connection) DriverManagerConnectionPool.getConnection();
                preparedStatement = (PreparedStatement) connection.prepareStatement(sqlInsert);
                preparedStatement.setString(1, utente.getNome());
                preparedStatement.setString(2, utente.getCognome());
                preparedStatement.setString(3, utente.getEmail());
                preparedStatement.setString(4, utente.getPassword());
                preparedStatement.setString(5, utente.getRuolo());
                preparedStatement.executeUpdate();



            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }finally{
                try {
                    preparedStatement.close();
                    DriverManagerConnectionPool.releaseConnection((com.mysql.jdbc.Connection) connection);
                } catch (SQLException e) {
                    // TODO Auto-generated catch block

                    e.printStackTrace();
                    return false;
                }
            }
            return true;
        }
        return false;

    }

    public synchronized UserBean doRetriveById(int id)  {
        Connection conn = null;
        PreparedStatement ps = null;
        UserBean utente = null;
        try {
            conn = (Connection) DriverManagerConnectionPool.getConnection();
            ps=(PreparedStatement) conn.prepareStatement("SELECT * from utente where id_utente=?");
            ps.setInt(1, id);

            ResultSet res =ps.executeQuery();
            //Prendo il risultato dalla query

            if(res.next()) {
                utente=new UserBean();
                utente.setId_utente(res.getInt("id_utente"));
                utente.setNome(res.getString("nome"));
                utente.setCognome(res.getString("cognome"));
                utente.setEmail(res.getString("email"));
                utente.setPassword(res.getString("password"));
                utente.setRuolo(res.getString("ruolo"));
                return utente;
            }
        }catch(SQLException ex) {
            ex.printStackTrace();
        }finally{
            try {
                ps.close();
                DriverManagerConnectionPool.releaseConnection((com.mysql.jdbc.Connection) conn);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return null;
            }
        }
        return null;

    }

    public synchronized boolean doDelete(int id_utente) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sqlDelete = "delete from utente where id_utente = ? ";
        int res=0;

        try {
            connection = (Connection) DriverManagerConnectionPool.getConnection();
            preparedStatement=(PreparedStatement) connection.prepareStatement(sqlDelete);
            preparedStatement.setInt(1, id_utente);

            res = preparedStatement.executeUpdate();


        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            try {
                preparedStatement.close();

                DriverManagerConnectionPool.releaseConnection((com.mysql.jdbc.Connection) connection);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }


        return (res!=0);

    }

    public synchronized boolean doUpdate(UserBean utente) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int res=0;
        String sqlUpdate = "UPDATE utente SET nome = ? , cognome = ? , email = ? , password = ? , ruolo = ?   where id_utente = ?";
        try {
            connection = (Connection) DriverManagerConnectionPool.getConnection();
            preparedStatement=(PreparedStatement) connection.prepareStatement(sqlUpdate);

            preparedStatement.setString(1, utente.getNome());
            preparedStatement.setString(2, utente.getCognome());
            preparedStatement.setString(3, utente.getEmail());
            preparedStatement.setString(4, utente.getPassword());
            preparedStatement.setString(5, utente.getRuolo());
            preparedStatement.setInt(6, utente.getId_utente());

            res = preparedStatement.executeUpdate();


        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            try {
                preparedStatement.close();
                DriverManagerConnectionPool.releaseConnection((com.mysql.jdbc.Connection) connection);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return (res!=0);
    }

}
