package it.unisa.tophw.server.model.management;

import it.unisa.tophw.server.model.beans.OrderBean;
import it.unisa.tophw.server.model.beans.PaymentBean;
import it.unisa.tophw.server.model.connection.DriverManagerConnectionPool;

import java.sql.*;

public class PaymentDAO {

    public synchronized PaymentBean doRetriveByOrder(OrderBean order) {

        Connection conn = null;
        PreparedStatement ps = null;
        PaymentBean payment = new PaymentBean();
        try {
            if(order==null || order.getId_ordine()==0){
                return null;
            }
            conn =(Connection) DriverManagerConnectionPool.getConnection();
            ps=(PreparedStatement) conn.prepareStatement("SELECT * FROM pagamento WHERE id_ordine=?");
            ps.setInt(1,order.getId_ordine());


            ResultSet rs= ps.executeQuery();

            if(rs.next()) {
                payment.setId_pagamento(rs.getInt("id_pagamento"));
                payment.setCvv(rs.getInt("cvv"));
                payment.setData_pagamento(rs.getDate("data_pagamento"));
                payment.setImporto(rs.getDouble("importo"));
                payment.setIntestatario(rs.getString("intestatario"));
                payment.setNumero_carta(rs.getLong("numero_carta"));
                payment.setScadenza(rs.getString("scadenza"));

                return payment;
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
        return payment;
    }



    public synchronized void doSave(PaymentBean payment) {

        if(payment!=null && payment.getId_ordine()>0 ) {
            Connection connection=null;
            PreparedStatement preparedStatement=null;
            String sqlInsert="Insert into pagamento (intestatario,scadenza,cvv,numero_carta,importo,data_pagamento,id_ordine) values (?,?,?,?,?,?)";


            try {

                connection=(Connection) DriverManagerConnectionPool.getConnection();
                preparedStatement = (PreparedStatement) connection.prepareStatement(sqlInsert);
                preparedStatement.setString(1, payment.getIntestatario());
                preparedStatement.setString(2,payment.getScadenza());
                preparedStatement.setInt(3,payment.getCvv());
                preparedStatement.setLong(4,payment.getNumero_carta());
                preparedStatement.setDouble(5,payment.getImporto());
                preparedStatement.setDate(6, (Date) payment.getData_pagamento());
                preparedStatement.setInt(7,payment.getId_ordine());
                preparedStatement.executeUpdate();



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

    }


}
