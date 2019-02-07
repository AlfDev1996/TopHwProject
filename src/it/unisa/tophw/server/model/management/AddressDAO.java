package it.unisa.tophw.server.model.management;

import it.unisa.tophw.server.model.beans.AddressBean;
import it.unisa.tophw.server.model.beans.UserBean;
import it.unisa.tophw.server.model.connection.DriverManagerConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddressDAO {

    public synchronized boolean doSave(AddressBean addressBean) {
        if(addressBean!=null && addressBean.getId_utente()>0)
        {
            Connection connection = null;
            PreparedStatement preparedStatement = null;
            String sqlInsert = "Insert into indirizzo (via,civico,cap,comune,nazione,provincia,id_utente) values (?,?,?,?,?,?,?) ";

            try {

                connection = (Connection) DriverManagerConnectionPool.getConnection();
                preparedStatement = (PreparedStatement) connection.prepareStatement(sqlInsert);
                preparedStatement.setString(1, addressBean.getVia());
                preparedStatement.setInt(2, addressBean.getCivico());
                preparedStatement.setInt(3, addressBean.getCap());
                preparedStatement.setString(4, addressBean.getComune());
                preparedStatement.setString(5, addressBean.getNazione());
                preparedStatement.setString(6, addressBean.getProvincia());
                preparedStatement.setInt(7, addressBean.getId_utente());
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

    public synchronized boolean doUpdate(AddressBean addressBean) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int res=0;
        String sqlUpdate = "UPDATE indirizzo SET via = ? , civico= ? , cap= ? , comune= ? , nazione= ?, provincia= ?   where id_indirizzo = ?";
        try {
            connection = (Connection) DriverManagerConnectionPool.getConnection();
            preparedStatement=(PreparedStatement) connection.prepareStatement(sqlUpdate);

            preparedStatement.setString(1, addressBean.getVia());
            preparedStatement.setInt(2, addressBean.getCivico());
            preparedStatement.setInt(3, addressBean.getCap());
            preparedStatement.setString(4, addressBean.getComune());
            preparedStatement.setString(5, addressBean.getNazione());
            preparedStatement.setString(6, addressBean.getProvincia());
            preparedStatement.setInt(7,addressBean.getId_indirizzo());
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

    public synchronized boolean doDelete(int id_indirizzo) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sqlDelete = "delete from indirizzo where id_indirizzo = ? ";
        int res=0;

        try {
            connection = (Connection) DriverManagerConnectionPool.getConnection();
            preparedStatement=(PreparedStatement) connection.prepareStatement(sqlDelete);
            preparedStatement.setInt(1, id_indirizzo);

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

    public synchronized AddressBean doRetriveByUser(UserBean userBean)  {

        Connection conn = null;
        PreparedStatement ps = null;
        AddressBean addressBean=null;

        try {
            if(userBean!=null && userBean.getId_utente()>0){

                conn = DriverManagerConnectionPool.getConnection();
                ps=(PreparedStatement) conn.prepareStatement("SELECT * from indirizzo where id_utente= ?");
                int id_utente = userBean.getId_utente();
                ps.setInt(1, id_utente);


                ResultSet res =ps.executeQuery();

                //Prendo il risultato dalla query

                if(res.next()) {
                    addressBean = new AddressBean();
                    addressBean.setId_indirizzo(res.getInt("id_indirizzo"));
                    addressBean.setVia(res.getString("via"));
                    addressBean.setCivico(res.getInt("civico"));
                    addressBean.setCap(res.getInt("cap"));
                    addressBean.setComune(res.getString("comune"));
                    addressBean.setNazione(res.getString("nazione"));
                    addressBean.setProvincia(res.getString("provincia"));
                    addressBean.setId_utente(res.getInt("id_utente"));

                    return addressBean;

                }
            }else{
                return null;
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
        return addressBean;
    }

}
