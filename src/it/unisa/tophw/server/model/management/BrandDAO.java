package it.unisa.tophw.server.model.management;

import it.unisa.tophw.server.model.beans.BrandBean;
import it.unisa.tophw.server.model.connection.DriverManagerConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BrandDAO {
    public synchronized BrandBean doRetriveByKey(int id) {

        Connection conn = null;
        PreparedStatement ps = null;
        BrandBean mar = new BrandBean();
        try {
            conn =(Connection) DriverManagerConnectionPool.getConnection();
            ps=(PreparedStatement) conn.prepareStatement("SELECT * FROM marca WHERE id_marca=?");
            ps.setInt(1,id);


            ResultSet rs= ps.executeQuery();

            if(rs.next()) {
                mar.setIdMarca(rs.getInt("id_marca"));
                mar.setNome(rs.getString("nome"));
                return mar;
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
        return mar;
    }


    public synchronized BrandBean doRetriveBynome(String nome) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        BrandBean mar = null;
        try {

            connection= (Connection) DriverManagerConnectionPool.getConnection();
            preparedStatement= (PreparedStatement) connection.prepareStatement("SELECT * FROM marca WHERE nome =?");
            preparedStatement.setString(1, nome);

            ResultSet rs= preparedStatement.executeQuery();


            if(rs.next()) {
                mar = new BrandBean();
                mar.setIdMarca(rs.getInt("id_marca"));
                mar.setNome(rs.getString("nome"));
                return mar;
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

        return mar;
    }



    public synchronized boolean doSave(BrandBean marca) {

        if(marca!=null&& marca.getNome()!=null && !marca.getNome().equals("")) {
            Connection connection=null;
            PreparedStatement preparedStatement=null;
            String sqlInsert="Insert into marca (nome) values (?)";


            try {

                connection=(Connection) DriverManagerConnectionPool.getConnection();
                preparedStatement = (PreparedStatement) connection.prepareStatement(sqlInsert);
                preparedStatement.setString(1, marca.getNome());
                preparedStatement.executeUpdate();

                return true;

            }catch(SQLException e) {
                e.printStackTrace();
                return false;

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
        return false;
    }


    public synchronized boolean doDelete(int id_marca) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sqlDelete = "delete from marca where id_marca = ? ";
        int res=0;

        try {

            connection = (Connection) DriverManagerConnectionPool.getConnection();
            preparedStatement=(PreparedStatement) connection.prepareStatement(sqlDelete);
            preparedStatement.setInt(1, id_marca);

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


    public synchronized ArrayList<BrandBean> doRetrieveAll(String orderBy){

        ArrayList<BrandBean> marche=new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement =null;
        BrandBean tmpMarca = null;

        String sqlSelect ="select * from marca";
        if(orderBy!=null && (orderBy.equals("id_marca")||orderBy.equals("nome")))
            sqlSelect+="order by "+orderBy;
        try {
            connection = (Connection) DriverManagerConnectionPool.getConnection();
            preparedStatement = (PreparedStatement) connection.prepareStatement(sqlSelect);


            ResultSet rs = preparedStatement.executeQuery();


            while(rs.next()) {
                tmpMarca = new BrandBean();
                tmpMarca.setIdMarca(rs.getInt("id_marca"));
                tmpMarca.setNome(rs.getString("nome"));
                marche.add(tmpMarca);

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

        return marche;


    }


    public synchronized boolean doUpdate(BrandBean marca) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int res=0;

        String sqlUpdate = "UPDATE marca SET nome = ?  WHERE id_marca = ?";

        try {

            connection = (Connection) DriverManagerConnectionPool.getConnection();
            preparedStatement=(PreparedStatement) connection.prepareStatement(sqlUpdate);
            preparedStatement.setString(1, marca.getNome());
            preparedStatement.setInt(2, marca.getIdMarca());


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
