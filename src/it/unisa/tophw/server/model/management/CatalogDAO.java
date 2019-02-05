package it.unisa.tophw.server.model.management;

import it.unisa.tophw.server.model.beans.CatalogBean;
import it.unisa.tophw.server.model.connection.DriverManagerConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CatalogDAO {


    public synchronized CatalogBean doRetriveByKey(int id) {

        Connection conn = null;
        PreparedStatement ps = null;
        CatalogBean catalogBean = new CatalogBean();
        try {
            conn =(Connection) DriverManagerConnectionPool.getConnection();
            ps=(PreparedStatement) conn.prepareStatement("SELECT * FROM catalogo WHERE id_catalogo=?");
            ps.setInt(1,id);


            ResultSet rs= ps.executeQuery();

            if(rs.next()) {
                catalogBean.setId_catalogo(rs.getInt("id_catalogo"));
                catalogBean.setNomeCatalogo(rs.getString("nome"));
                catalogBean.setDescrizioneCatalogo(rs.getString("descrizione"));
                return catalogBean;
            }


        }catch(SQLException e) {

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
        return catalogBean;
    }


    public synchronized CatalogBean doRetriveBynome(String nome) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        CatalogBean catalogBean = new CatalogBean();
        try {

            connection= (Connection) DriverManagerConnectionPool.getConnection();
            preparedStatement= (PreparedStatement) connection.prepareStatement("SELECT * FROM catalogo WHERE nome =?");
            preparedStatement.setString(1, nome);

            ResultSet rs= preparedStatement.executeQuery();


            if(rs.next()) {
                catalogBean.setId_catalogo(rs.getInt("id_catalogo"));
                catalogBean.setNomeCatalogo(rs.getString("nome"));
                catalogBean.setDescrizioneCatalogo(rs.getString("descrizione"));
                return catalogBean;
            }


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

        return catalogBean;
    }



    public synchronized boolean doSave(CatalogBean catalogBean) {

        if(catalogBean!=null && catalogBean.getNomeCatalogo()!=null && !catalogBean.getNomeCatalogo().equals("")) {
            Connection connection=null;
            PreparedStatement preparedStatement=null;
            String sqlInsert="Insert into catalogo (nome,descrizione,sconto) values (?,?,?)";


            try {

                connection=(Connection) DriverManagerConnectionPool.getConnection();
                preparedStatement = (PreparedStatement) connection.prepareStatement(sqlInsert);
                preparedStatement.setString(1, catalogBean.getNomeCatalogo());
                preparedStatement.setString(2,catalogBean.getDescrizioneCatalogo());
                preparedStatement.setInt(3,catalogBean.getSconto());
                preparedStatement.executeUpdate();

                return true;
            }catch(SQLException e) {
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
        }
        return false;

    }


    public synchronized boolean doDelete(int id_catalogo) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sqlDelete = "delete from catalogo where id_catalogo = ? ";
        int res=0;

        try {

            connection = (Connection) DriverManagerConnectionPool.getConnection();
            preparedStatement=(PreparedStatement) connection.prepareStatement(sqlDelete);
            preparedStatement.setInt(1, id_catalogo);

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


    public synchronized ArrayList<CatalogBean> doRetrieveAll(String orderBy){

        ArrayList<CatalogBean> cataloghi=new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement =null;
        CatalogBean catalogBean = null;

        String sqlSelect ="select * from catalogo";
        if(orderBy!=null && (orderBy.equals("id-catalogo")||orderBy.equals("nome")))
            sqlSelect+="order by "+orderBy;
        try {
            connection = (Connection) DriverManagerConnectionPool.getConnection();
            preparedStatement = (PreparedStatement) connection.prepareStatement(sqlSelect);


            ResultSet rs = preparedStatement.executeQuery();


            while(rs.next()) {
                catalogBean = new CatalogBean();
                catalogBean.setId_catalogo(rs.getInt("id_catalogo"));
                catalogBean.setNomeCatalogo(rs.getString("nome"));
                catalogBean.setDescrizioneCatalogo(rs.getString("descrizione"));
                catalogBean.setSconto(rs.getInt("sconto"));
                cataloghi.add(catalogBean);

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

        return cataloghi;


    }


    public synchronized boolean doUpdate(CatalogBean catalogBean) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int res=0;

        String sqlUpdate = "UPDATE catalogo SET nome = ? , descrizione = ?, sconto= ? WHERE id_catalogo = ?";

        try {

            connection = (Connection) DriverManagerConnectionPool.getConnection();
            preparedStatement=(PreparedStatement) connection.prepareStatement(sqlUpdate);
            preparedStatement.setString(1, catalogBean.getNomeCatalogo());
            preparedStatement.setString(2, catalogBean.getDescrizioneCatalogo());
            preparedStatement.setInt(3,catalogBean.getSconto());
            preparedStatement.setInt(4,catalogBean.getId_catalogo());


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

}
