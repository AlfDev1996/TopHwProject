package it.unisa.tophw.server.model.management;


import it.unisa.tophw.server.model.beans.OrderBean;
import it.unisa.tophw.server.model.beans.ProductBean;
import it.unisa.tophw.server.model.beans.UserBean;
import it.unisa.tophw.server.model.connection.DriverManagerConnectionPool;

import java.sql.*;
import java.util.ArrayList;

public class OrderDAO {


    public synchronized OrderBean doRetriveById(int id)  {

        Connection conn = null;
        PreparedStatement ps = null;
        OrderBean ordine=null;
        try {
            conn = (Connection) DriverManagerConnectionPool.getConnection();
            ps=(PreparedStatement) conn.prepareStatement("SELECT * from ordine where id_ordine=?");
            ps.setInt(1, id);



            ResultSet rs = ps.executeQuery();


            if(rs.next()) {
                ordine = new OrderBean();
                ordine.setData_creazione( rs.getDate("data_ordine") );
                ordine.setId_ordine(rs.getInt("id_ordine"));
                ordine.setStato(rs.getString("stato"));
                ordine.setTotale(rs.getFloat("totale"));


                int id_utente= rs.getInt("id_utente") !=0 ? rs.getInt("id_utente"):0;

                if(id_utente!=0) {
                    UserDAO UserDAO= new UserDAO();
                    UserBean utente = UserDAO.doRetriveById(id_utente);
                    if(utente!=null &&utente.getId_utente()>0)
                        ordine.setUtente(utente);
                    else
                        ordine.setUtente(null);

                }

                return ordine;


            }


        }catch (SQLException e) {
            e.printStackTrace();
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

    public synchronized ArrayList<OrderBean> doRetriveByStato(String stato)  {
        Connection conn = null;
        PreparedStatement ps = null;
        OrderBean ordine = null;
        ArrayList<OrderBean> ordini = new ArrayList<>();
        try {
            conn = (Connection) DriverManagerConnectionPool.getConnection();
            ps=(PreparedStatement) conn.prepareStatement("SELECT * from ordine where stato = ? ");
            ps.setString(1, stato);

            ResultSet res =ps.executeQuery();

            while(res.next()) {
                ordine= new OrderBean();
                ordine.setData_creazione(res.getDate("data_ordine"));
                ordine.setId_ordine(res.getInt("id_ordine"));
                ordine.setStato(res.getString("stato"));
                ordine.setTotale(res.getFloat("totale"));


                int id_utente= res.getInt("id_utente") !=0 ? res.getInt("id_utente"):0;

                if(id_utente!=0) {
                    UserDAO UserDAO= new UserDAO();
                    UserBean utente = UserDAO.doRetriveById(id_utente);
                    if(utente!=null && utente.getId_utente()>0)
                        ordine.setUtente(utente);
                    else
                        ordine.setUtente(null);

                }
                ordini.add(ordine);


            }


        }catch (SQLException e) {
            e.printStackTrace();
        }finally{
            try {
                ps.close();
                DriverManagerConnectionPool.releaseConnection((com.mysql.jdbc.Connection) conn);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return ordini;
    }

    public synchronized ArrayList<OrderBean> doRetriveByData_creazione(String Data_creazione)  {
        Connection conn = null;
        PreparedStatement ps = null;
        OrderBean ordine = null;
        ArrayList<OrderBean> ordini = new ArrayList<>();
        try {
            conn = (Connection) DriverManagerConnectionPool.getConnection();
            ps=(PreparedStatement) conn.prepareStatement("SELECT * from ordine where data_ordine = ? ");
            ps.setString(1, Data_creazione);

            ResultSet res =ps.executeQuery();

            while(res.next()) {
                ordine= new OrderBean();
                ordine.setData_creazione(res.getDate("data_ordine"));
                ordine.setStato(res.getString("stato"));
                ordine.setTotale(res.getFloat("totale"));

                int id_utente= res.getInt("id_utente") !=0 ? res.getInt("id_utente"):0;

                if(id_utente!=0) {
                    UserDAO UserDAO= new UserDAO();
                    UserBean utente = UserDAO.doRetriveById(id_utente);
                    if(utente!=null &&utente.getId_utente()>0)
                        ordine.setUtente(utente);
                    else
                        ordine.setUtente(null);

                }
                ordini.add(ordine);


            }


        }catch (SQLException e) {
            e.printStackTrace();
        }finally{
            try {
                ps.close();
                DriverManagerConnectionPool.releaseConnection((com.mysql.jdbc.Connection) conn);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return ordini;
    }



    public synchronized ArrayList<OrderBean> doRetrieveAll(String orderBy){

        ArrayList<OrderBean> ordini =new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        OrderBean ordine = null;

        String sqlSelect = "select * from ordine";
        if(orderBy!=null && (orderBy.equalsIgnoreCase("id_ordine") || orderBy.equalsIgnoreCase("data_creazione") || orderBy.equalsIgnoreCase("stato")|| orderBy.equalsIgnoreCase("indirizzo")|| orderBy.equalsIgnoreCase("totale")|| orderBy.equalsIgnoreCase("id_utente") ) )
            sqlSelect+="order by "+orderBy;

        try {
            connection = (Connection) DriverManagerConnectionPool.getConnection();
            preparedStatement = (PreparedStatement) connection.prepareStatement(sqlSelect);

            ResultSet res = preparedStatement.executeQuery();

            while(res.next()) {
                ordine= new OrderBean();
                ordine.setId_ordine(res.getInt("id_ordine"));
                ordine.setData_creazione(res.getDate("data_ordine"));
                ordine.setStato(res.getString("stato"));
                ordine.setTotale(res.getFloat("totale"));
                int id_utente= res.getInt("id_utente") !=0 ? res.getInt("id_utente"):0;

                if(id_utente!=0) {
                    UserDAO UserDAO= new UserDAO();
                    UserBean utente = UserDAO.doRetriveById(id_utente);
                    if(utente!=null &&utente.getId_utente()>0)
                        ordine.setUtente(utente);
                    else
                        ordine.setUtente(null);

                }
                ordini.add(ordine);


            }




        }catch(SQLException e) {
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

        return ordini;
    }



    public synchronized ArrayList<OrderBean> doRetrieveByProdotto(ProductBean prodotto){
        ArrayList<OrderBean> ordini =null;
        if(prodotto!=null) {

            Connection connection = null;
            PreparedStatement preparedStatement = null;
            OrderBean ordine = null;
            String sqlSelect="select ord.* "
                    + "from ordine ord join voce_ordine voceord on voceord.id_ordine = ord.id_ordine "
                    + "join variante_prodotto varprod on voceord.id_variante_prodotto=varprod.id_variante_prodotto "
                    + "join prodotto prod on varprod.id_prodotto=prod.id where prod.id= ?";


            try {
                connection = (Connection) DriverManagerConnectionPool.getConnection();
                preparedStatement = (PreparedStatement) connection.prepareStatement(sqlSelect);
                preparedStatement.setInt(1, prodotto.getId_prodotto());

                ResultSet res = preparedStatement.executeQuery();

                while(res.next()) {
                    ordine= new OrderBean();
                    ordine.setData_creazione(res.getDate("data_ordine"));
                    ordine.setStato(res.getString("stato"));
                    ordine.setTotale(res.getFloat("totale"));

                    int id_utente= res.getInt("id_utente") !=0 ? res.getInt("id_utente"):0;

                    if(id_utente!=0) {
                        UserDAO UserDAO= new UserDAO();
                        UserBean utente = UserDAO.doRetriveById(id_utente);
                        if(utente!=null &&utente.getId_utente()>0)
                            ordine.setUtente(utente);
                        else
                            ordine.setUtente(null);

                    }
                    ordini.add(ordine);
                }


            }catch(SQLException e) {
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


        }
        return ordini;
    }

    public synchronized ArrayList<OrderBean> doRetrieveByUtente(UserBean utente){
        ArrayList<OrderBean> ordini =new ArrayList<>();
        if(utente!=null && utente.getId_utente()!=0) {

            Connection connection = null;
            PreparedStatement preparedStatement = null;
            OrderBean ordine = null;
            String sqlSelect="select * from ordine where id_utente = ? ";


            try {
                connection = (Connection) DriverManagerConnectionPool.getConnection();
                preparedStatement = (PreparedStatement) connection.prepareStatement(sqlSelect);
                preparedStatement.setInt(1, utente.getId_utente());

                ResultSet res = preparedStatement.executeQuery();

                while(res.next()) {
                    ordine= new OrderBean();
                    ordine.setId_ordine(res.getInt("id_ordine"));
                    ordine.setData_creazione(res.getDate("data_ordine"));
                    ordine.setStato(res.getString("stato"));
                    ordine.setTotale(res.getFloat("totale"));

                    ordine.setUtente(utente);

                    ordini.add(ordine);
                }


            }catch(SQLException e) {
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


        }
        return ordini;
    }



    public synchronized int doSave(OrderBean ordine) {
        if(ordine!=null) {
            Connection connection = null;
            PreparedStatement preparedStatement = null;
            String sqlInsert="insert into ordine (data_ordine, stato,totale,id_utente) values(?,?,?,?)";
            int res=0;
            try {

                connection = (Connection) DriverManagerConnectionPool.getConnection();
                preparedStatement = (PreparedStatement) connection.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);


                preparedStatement.setDate(1, (java.sql.Date) ordine.getData_creazione());
                preparedStatement.setString(2, ordine.getStato());
                preparedStatement.setFloat(3, ordine.getTotale());
                preparedStatement .setInt(4, ordine.getUtente().getId_utente());


                res = preparedStatement.executeUpdate();
                ResultSet rs = preparedStatement.getGeneratedKeys();
                if(rs.next())
                    return rs.getInt(1);

            }catch(SQLException e) {

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


        }

        return -1;
    }

    public synchronized boolean doDelete(int id_ordine) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sqlDelete = "delete from ordine where id_ordine = ? ";
        int res=0;

        try {
            connection = (Connection) DriverManagerConnectionPool.getConnection();
            preparedStatement=(PreparedStatement) connection.prepareStatement(sqlDelete);

            preparedStatement.setInt(1, id_ordine);
            res = preparedStatement.executeUpdate();

        }catch(SQLException e) {
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

    public synchronized boolean doUpdate(OrderBean ordine) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int res=0;

        String sqlUpdate = "UPDATE ordine SET data_ordine = ? , stato = ? , indirizzo = ? , totale = ? , id_utente = ? where id_ordine=? ";
        try {
            connection = (Connection) DriverManagerConnectionPool.getConnection();
            preparedStatement=(PreparedStatement) connection.prepareStatement(sqlUpdate);

            preparedStatement.setDate(1, (java.sql.Date) ordine.getData_creazione());
            preparedStatement.setString(2, ordine.getStato());
            preparedStatement.setFloat(4, ordine.getTotale());
            preparedStatement.setInt(5, ordine.getUtente().getId_utente());
            preparedStatement.setInt(6, ordine.getId_ordine());

            res = preparedStatement.executeUpdate();


        }catch (SQLException e) {
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

    public ArrayList<OrderBean> doRetriveByFilters(OrderBean orderBean) {

        ArrayList<OrderBean> ordini =new ArrayList<>();

        if(orderBean!=null ){
            Connection connection = null;
            PreparedStatement preparedStatement = null;
            OrderBean ordine = null;
            String sqlSelect="select * from ordine";
            sqlSelect+=" where id_ordine>0 ";
            boolean existDateFilter=false;

            if(orderBean.getUtente()!=null && orderBean.getUtente().getId_utente()>0){
                sqlSelect+=" and id_utente="+orderBean.getUtente().getId_utente()+"";
            }

            if(orderBean.getData_creazione()!=null){
                sqlSelect+=" and data_ordine = ?";
                existDateFilter=true;
            }

            sqlSelect=" order by data_creazione";

            try {
                connection = (Connection) DriverManagerConnectionPool.getConnection();
                preparedStatement = (PreparedStatement) connection.prepareStatement(sqlSelect);
                if(existDateFilter)
                    preparedStatement.setDate(1, (java.sql.Date) orderBean.getData_creazione());

                ResultSet res = preparedStatement.executeQuery();

                while(res.next()) {
                    ordine= new OrderBean();
                    ordine.setId_ordine(res.getInt("id_ordine"));
                    ordine.setData_creazione(res.getDate("data_ordine"));
                    ordine.setStato(res.getString("stato"));
                    ordine.setTotale(res.getFloat("totale"));

                    ordine.setUtente(orderBean.getUtente());

                    ordini.add(ordine);
                }


            }catch(SQLException e) {
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





        }else{
            return null;
        }




        return ordini;
        }

}
