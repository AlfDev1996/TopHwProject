package it.unisa.tophw.server.model.management;

import it.unisa.tophw.server.model.beans.BrandBean;
import it.unisa.tophw.server.model.beans.CatalogBean;
import it.unisa.tophw.server.model.beans.ProductBean;
import it.unisa.tophw.server.model.connection.DriverManagerConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductDAO {

    public synchronized ProductBean doRetriveById(int id)  {

        Connection conn = null;
        PreparedStatement ps = null;
        ProductBean prodotto = new ProductBean();
        try {

            conn = (Connection) DriverManagerConnectionPool.getConnection();
            ps=(PreparedStatement) conn.prepareStatement("SELECT * from prodotto where id_prodotto=?");
            ps.setInt(1, id);

            ResultSet res =ps.executeQuery();
            //Prendo il risultato dalla query

            if(res.next()) {
                prodotto=new ProductBean();
                prodotto.setId_prodotto(res.getInt("id_prodotto"));
                prodotto.setNome(res.getString("nome"));
                prodotto.setDescrizione_breve(res.getString("descrizione_breve"));
                prodotto.setDescrizione_estesa(res.getString("descrizione_estesa"));
                prodotto.setPrezzo(res.getDouble("prezzo"));
                prodotto.setQuantita(res.getInt("quantita"));
                prodotto.setPerc_sconto(res.getInt("perc_sconto"));
                prodotto.setPathImg1(res.getString("path_img1"));
                prodotto.setPathImg2(res.getString("path_img2"));
                prodotto.setPathImg3(res.getString("path_img3"));

                int id_marca = res.getInt("id_marca") != 0 ? res.getInt("id_marca") : 0;
                if(id_marca!=0)
                {
                    BrandDAO marcaDao= new BrandDAO();
                    BrandBean marca = marcaDao.doRetriveByKey(id_marca);
                    if(marca!=null && marca.getIdMarca()>0)
                        prodotto.setId_marca(marca.getIdMarca());
                    else
                        prodotto.setId_marca(0);
                }

                int id_catalogo = res.getInt("id_catalogo") != 0 ? res.getInt("id_catalogo") : 0;
                if(id_catalogo!=0)
                {
                    CatalogDAO catalogDAO= new CatalogDAO();
                    CatalogBean catalogBean = catalogDAO.doRetriveByKey(id_catalogo);
                    if(catalogBean!=null && catalogBean.getId_catalogo()>0)
                        prodotto.setId_catalogo(catalogBean.getId_catalogo());
                    else
                        prodotto.setId_catalogo(0);
                }
                res.close();
                return prodotto;
            }
        }catch(SQLException ex) {
            ex.printStackTrace();
        } finally{
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

        public synchronized ArrayList<ProductBean> doRetriveByLikeNome(String nome)  {

        Connection conn = null;
        PreparedStatement ps = null;
        ProductBean prodotto =  new ProductBean();
        ArrayList<ProductBean> prodotti = new ArrayList<>();
        try {

            conn = (Connection) DriverManagerConnectionPool.getConnection();
            ps=(PreparedStatement) conn.prepareStatement("SELECT * from prodotto where nome like  ?  ");
            ps.setString(1, "%"+nome+"%");

            ResultSet res =ps.executeQuery();

            //Prendo il risultato dalla query
            while(res.next()) {
                prodotto=new ProductBean();
                prodotto.setId_prodotto(res.getInt("id_prodotto"));
                prodotto.setNome(res.getString("nome"));
                prodotto.setDescrizione_breve(res.getString("descrizione_breve"));
                prodotto.setDescrizione_estesa(res.getString("descrizione_estesa"));
                prodotto.setPrezzo(res.getDouble("prezzo"));
                prodotto.setQuantita(res.getInt("quantita"));
                prodotto.setPerc_sconto(res.getInt("perc_sconto"));
                int id_marca = res.getInt("id_marca") != 0 ? res.getInt("id_marca") : 0;
                if(id_marca!=0)
                {
                    BrandDAO marcaDao= new BrandDAO();
                    BrandBean marca = marcaDao.doRetriveByKey(id_marca);
                    if(marca!=null && marca.getIdMarca()>0)
                        prodotto.setId_marca(marca.getIdMarca());
                    else
                        prodotto.setId_marca(0);
                }

                int id_catalogo = res.getInt("id_catalogo") != 0 ? res.getInt("id_catalogo") : 0;
                if(id_catalogo!=0)
                {
                    CatalogDAO catalogDAO= new CatalogDAO();
                    CatalogBean catalogBean = catalogDAO.doRetriveByKey(id_catalogo);
                    if(catalogBean!=null && catalogBean.getId_catalogo()>0)
                        prodotto.setId_catalogo(catalogBean.getId_catalogo());
                    else
                        prodotto.setId_catalogo(0);
                }
                prodotti.add(prodotto);
            }

            res.close();
        }catch(SQLException ex) {
            ex.printStackTrace();
        }finally{
            try {
                ps.close();
                conn.close();
                DriverManagerConnectionPool.releaseConnection((com.mysql.jdbc.Connection) conn);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return prodotti;
    }

    public synchronized ProductBean doRetriveByNome(String nome)  {

        Connection conn = null;
        PreparedStatement ps = null;
        ProductBean prodotto =  new ProductBean();
        try {

            conn = (Connection) DriverManagerConnectionPool.getConnection();
            ps=(PreparedStatement) conn.prepareStatement("SELECT * from prodotto where nome like  ?  ");
            ps.setString(1, "'"+nome+"'");

            ResultSet res =ps.executeQuery();

            //Prendo il risultato dalla query
            if(res.next()) {
                prodotto=new ProductBean();
                prodotto.setId_prodotto(res.getInt("id_prodotto"));
                prodotto.setNome(res.getString("nome"));
                prodotto.setDescrizione_breve(res.getString("descrizione_breve"));
                prodotto.setDescrizione_estesa(res.getString("descrizione_estesa"));
                prodotto.setPrezzo(res.getDouble("prezzo"));
                prodotto.setQuantita(res.getInt("quantita"));
                prodotto.setPerc_sconto(res.getInt("perc_sconto"));
                int id_marca = res.getInt("id_marca") != 0 ? res.getInt("id_marca") : 0;
                if(id_marca!=0)
                {
                    BrandDAO marcaDao= new BrandDAO();
                    BrandBean marca = marcaDao.doRetriveByKey(id_marca);
                    if(marca!=null && marca.getIdMarca()>0)
                        prodotto.setId_marca(marca.getIdMarca());
                    else
                        prodotto.setId_marca(0);
                }

                int id_catalogo = res.getInt("id_catalogo") != 0 ? res.getInt("id_catalogo") : 0;
                if(id_catalogo!=0)
                {
                    CatalogDAO catalogDAO= new CatalogDAO();
                    CatalogBean catalogBean = catalogDAO.doRetriveByKey(id_catalogo);
                    if(catalogBean!=null && catalogBean.getId_catalogo()>0)
                        prodotto.setId_catalogo(catalogBean.getId_catalogo());
                    else
                        prodotto.setId_catalogo(0);
                }
                res.close();
                return prodotto;
            }

            res.close();
        }catch(SQLException ex) {
            ex.printStackTrace();
        }finally{
            try {
                ps.close();
                conn.close();
                DriverManagerConnectionPool.releaseConnection((com.mysql.jdbc.Connection) conn);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return prodotto;
    }

    public synchronized ArrayList<ProductBean> doRetriveByBrand(BrandBean brand)  {

        Connection conn = null;
        PreparedStatement ps = null;
        ProductBean prodotto =  new ProductBean();
        ArrayList<ProductBean> prodotti = new ArrayList<>();
        try {

            conn = (Connection) DriverManagerConnectionPool.getConnection();
            ps=(PreparedStatement) conn.prepareStatement("SELECT * from prodotto where id_marca = ? ");
            ps.setInt(1, brand.getIdMarca());

            ResultSet res =ps.executeQuery();

            //Prendo il risultato dalla query
            while(res.next()) {
                prodotto=new ProductBean();
                prodotto.setId_prodotto(res.getInt("id_prodotto"));
                prodotto.setNome(res.getString("nome"));
                prodotto.setDescrizione_breve(res.getString("descrizione_breve"));
                prodotto.setDescrizione_estesa(res.getString("descrizione_estesa"));
                prodotto.setPrezzo(res.getDouble("prezzo"));
                prodotto.setQuantita(res.getInt("quantita"));
                prodotto.setPerc_sconto(res.getInt("perc_sconto"));
                int id_marca = res.getInt("id_marca") != 0 ? res.getInt("id_marca") : 0;
                if(id_marca!=0)
                {
                    BrandDAO marcaDao= new BrandDAO();
                    BrandBean marca = marcaDao.doRetriveByKey(id_marca);
                    if(marca!=null && marca.getIdMarca()>0)
                        prodotto.setId_marca(marca.getIdMarca());
                    else
                        prodotto.setId_marca(0);
                }

                int id_catalogo = res.getInt("id_catalogo") != 0 ? res.getInt("id_catalogo") : 0;
                if(id_catalogo!=0)
                {
                    CatalogDAO catalogDAO= new CatalogDAO();
                    CatalogBean catalogBean = catalogDAO.doRetriveByKey(id_catalogo);
                    if(catalogBean!=null && catalogBean.getId_catalogo()>0)
                        prodotto.setId_catalogo(catalogBean.getId_catalogo());
                    else
                        prodotto.setId_catalogo(0);
                }
                prodotti.add(prodotto);
            }

            res.close();
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
        return prodotti;
    }

    public synchronized ArrayList<ProductBean> doRetriveByBestSeller()  {

        Connection conn = null;
        PreparedStatement ps = null;
        ProductBean prodotto =  new ProductBean();
        ArrayList<ProductBean> prodotti = new ArrayList<>();
        try {

            conn = (Connection) DriverManagerConnectionPool.getConnection();
            String sql = "select p.* , sum(voceOrd.quantita) as qtaVenduta from prodotto p join voce_ordine voceOrd on voceOrd.id_prodotto=p.id_prodotto GROUP BY p.id_prodotto order by qtaVenduta DESC ";

            ps=(PreparedStatement) conn.prepareStatement(sql);

            ResultSet res =ps.executeQuery();

            //Prendo il risultato dalla query
            while(res.next()) {
                prodotto=new ProductBean();
                prodotto.setId_prodotto(res.getInt("id_prodotto"));
                prodotto.setNome(res.getString("nome"));
                prodotto.setDescrizione_breve(res.getString("descrizione_breve"));
                prodotto.setDescrizione_estesa(res.getString("descrizione_estesa"));
                prodotto.setPrezzo(res.getDouble("prezzo"));
                prodotto.setQuantita(res.getInt("quantita"));
                prodotto.setPerc_sconto(res.getInt("perc_sconto"));
                int id_marca = res.getInt("id_marca") != 0 ? res.getInt("id_marca") : 0;
                if(id_marca!=0)
                {
                    BrandDAO marcaDao= new BrandDAO();
                    BrandBean marca = marcaDao.doRetriveByKey(id_marca);
                    if(marca!=null && marca.getIdMarca()>0)
                        prodotto.setId_marca(marca.getIdMarca());
                    else
                        prodotto.setId_marca(0);
                }

                int id_catalogo = res.getInt("id_catalogo") != 0 ? res.getInt("id_catalogo") : 0;
                if(id_catalogo!=0)
                {
                    CatalogDAO catalogDAO= new CatalogDAO();
                    CatalogBean catalogBean = catalogDAO.doRetriveByKey(id_catalogo);
                    if(catalogBean!=null && catalogBean.getId_catalogo()>0)
                        prodotto.setId_catalogo(catalogBean.getId_catalogo());
                    else
                        prodotto.setId_catalogo(0);
                }

                prodotti.add(prodotto);
            }

            res.close();
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
        return prodotti;
    }


    public synchronized ArrayList<ProductBean> doRetrieveAll(String orderBy){
        ArrayList<ProductBean> prodotti = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ProductBean prodotto = new ProductBean();

        String sqlSelect = "select * from prodotto ";
        if(orderBy!=null && (orderBy.equalsIgnoreCase("id_prodotto") || orderBy.equalsIgnoreCase("id_marca") || orderBy.equalsIgnoreCase("nome") || orderBy.equalsIgnoreCase("prezzo") ) )
            sqlSelect+="order by "+orderBy;

        try {

            connection = (Connection) DriverManagerConnectionPool.getConnection();

            preparedStatement = (PreparedStatement) connection.prepareStatement(sqlSelect);

            ResultSet res = preparedStatement.executeQuery();

            while(res.next()) {

                prodotto = new ProductBean();
                prodotto.setId_prodotto(res.getInt("id_prodotto"));
                prodotto.setNome(res.getString("nome"));
                prodotto.setDescrizione_breve(res.getString("descrizione_breve"));
                prodotto.setDescrizione_estesa(res.getString("descrizione_estesa"));
                prodotto.setPrezzo(res.getDouble("prezzo"));
                prodotto.setQuantita(res.getInt("quantita"));
                prodotto.setPerc_sconto(res.getInt("perc_sconto"));
                prodotto.setPathImg1(res.getString("path_img1"));
                int id_marca = res.getInt("id_marca") != 0 ? res.getInt("id_marca") : 0;
                if(id_marca!=0)
                {
                    BrandDAO marcaDao= new BrandDAO();
                    BrandBean marca = marcaDao.doRetriveByKey(id_marca);
                    if(marca!=null && marca.getIdMarca()>0)
                        prodotto.setId_marca(marca.getIdMarca());
                    else
                        prodotto.setId_marca(0);
                }

                int id_catalogo = res.getInt("id_catalogo") != 0 ? res.getInt("id_catalogo") : 0;
                if(id_catalogo!=0)
                {
                    CatalogDAO catalogDAO= new CatalogDAO();
                    CatalogBean catalogBean = catalogDAO.doRetriveByKey(id_catalogo);
                    if(catalogBean!=null && catalogBean.getId_catalogo()>0)
                        prodotto.setId_catalogo(catalogBean.getId_catalogo());
                    else
                        prodotto.setId_catalogo(0);
                }
                prodotti.add(prodotto);


            }

            res.close();
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

        return prodotti;
    }

    public synchronized ArrayList<ProductBean> doRetriveByFilters(ProductBean productBean,String orderBy){
        ArrayList<ProductBean> prodotti = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ProductBean prodotto = new ProductBean();

        String sqlSelect = "select * from prodotto ";

        if(productBean!=null){
            sqlSelect+=" where id_prodotto>0 ";
            if(productBean.getNome()!=null)
                sqlSelect+=" and nome like '%"+productBean.getNome()+"%'";
            if(productBean.getPrezzo()!=-1)
                sqlSelect+=" and prezzo > "+productBean.getPrezzo()+" ";
            if(productBean.getQuantita()!=-1)
                sqlSelect+=" and prezzo < "+productBean.getQuantita()+" ";

            if(productBean.getId_marca()!=-1)
                sqlSelect+=" and id_marca = "+productBean.getId_marca()+" ";
        }

        if(orderBy!=null && (orderBy.equalsIgnoreCase("id_prodotto") || orderBy.equalsIgnoreCase("id_marca") || orderBy.equalsIgnoreCase("nome") || orderBy.equalsIgnoreCase("prezzo") ) )
            sqlSelect+="order by "+orderBy;

        try {

            connection = (Connection) DriverManagerConnectionPool.getConnection();

            preparedStatement = (PreparedStatement) connection.prepareStatement(sqlSelect);

            ResultSet res = preparedStatement.executeQuery();

            while(res.next()) {

                prodotto = new ProductBean();
                prodotto.setId_prodotto(res.getInt("id_prodotto"));
                prodotto.setNome(res.getString("nome"));
                prodotto.setDescrizione_breve(res.getString("descrizione_breve"));
                prodotto.setDescrizione_estesa(res.getString("descrizione_estesa"));
                prodotto.setPrezzo(res.getDouble("prezzo"));
                prodotto.setQuantita(res.getInt("quantita"));
                prodotto.setPerc_sconto(res.getInt("perc_sconto"));
                prodotto.setPathImg1(res.getString("path_img1"));
                int id_marca = res.getInt("id_marca") != 0 ? res.getInt("id_marca") : 0;
                if(id_marca!=0)
                {
                    BrandDAO marcaDao= new BrandDAO();
                    BrandBean marca = marcaDao.doRetriveByKey(id_marca);
                    if(marca!=null && marca.getIdMarca()>0)
                        prodotto.setId_marca(marca.getIdMarca());
                    else
                        prodotto.setId_marca(0);
                }

                int id_catalogo = res.getInt("id_catalogo") != 0 ? res.getInt("id_catalogo") : 0;
                if(id_catalogo!=0)
                {
                    CatalogDAO catalogDAO= new CatalogDAO();
                    CatalogBean catalogBean = catalogDAO.doRetriveByKey(id_catalogo);
                    if(catalogBean!=null && catalogBean.getId_catalogo()>0)
                        prodotto.setId_catalogo(catalogBean.getId_catalogo());
                    else
                        prodotto.setId_catalogo(0);
                }
                prodotti.add(prodotto);


            }

            res.close();
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

        return prodotti;
    }

    public synchronized boolean doSave(ProductBean prodotto) {
        if(prodotto!=null)
        {
            Connection connection = null;
            PreparedStatement preparedStatement = null;
            String sqlInsert = "Insert into prodotto (nome,descrizione_breve,descrizione_estesa,prezzo,id_marca,quantita,perc_sconto,id_catalogo,path_img1,path_img2,path_img3) values (?,?,?,?,?,?,?,?,?,?,?) ";
            int res=0;
            try {

                connection = (Connection) DriverManagerConnectionPool.getConnection();

                preparedStatement = (PreparedStatement) connection.prepareStatement(sqlInsert);
                preparedStatement.setString(1, prodotto.getNome());
                preparedStatement.setString(2, prodotto.getDescrizione_breve());
                preparedStatement.setString(3, prodotto.getDescrizione_estesa());
                preparedStatement.setDouble(4, prodotto.getPrezzo());
                preparedStatement.setInt(5, prodotto.getId_marca());
                preparedStatement.setInt(6, prodotto.getQuantita());
                preparedStatement.setInt(7, prodotto.getPerc_sconto());
                preparedStatement.setInt(8, prodotto.getId_catalogo());
                preparedStatement.setString(9, prodotto.getPathImg1());
                preparedStatement.setString(10, prodotto.getPathImg2());
                preparedStatement.setString(11, prodotto.getPathImg3());


                res = preparedStatement.executeUpdate();

            } catch (SQLException e) {
                // TODO Auto-generated catch block
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
        return true;
    }

    public synchronized boolean doDelete(int id_prodotto) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sqlDelete = "DELETE FROM Prodotto WHERE id_prodotto=?";
        int res=0;

        try {
            connection = (Connection) DriverManagerConnectionPool.getConnection();

            preparedStatement=(PreparedStatement) connection.prepareStatement(sqlDelete);
            preparedStatement.setInt(1, id_prodotto);
            System.out.println(sqlDelete);
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

    public synchronized boolean doUpdate(ProductBean prodotto) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int res=0;
        String sqlUpdate = "UPDATE prodotto SET nome = ? , descrizione_breve = ? , descrizione_estesa = ? , prezzo = ? , id_marca = ? ,quantita = ? , perc_sconto = ?, id_catalogo=? , path_img1 = ?, path_img2= ?,path_img3= ? where id_prodotto = ?";
        try {
            connection = (Connection) DriverManagerConnectionPool.getConnection();
            preparedStatement=(PreparedStatement) connection.prepareStatement(sqlUpdate);

            preparedStatement.setString(1, prodotto.getNome());
            preparedStatement.setString(2, prodotto.getDescrizione_breve());
            preparedStatement.setString(3, prodotto.getDescrizione_estesa());
            preparedStatement.setDouble(4, prodotto.getPrezzo());
            preparedStatement.setInt(5, prodotto.getId_marca());
            preparedStatement.setInt(6, prodotto.getQuantita());
            preparedStatement.setInt(7, prodotto.getPerc_sconto());
            preparedStatement.setInt(8, prodotto.getId_catalogo());
            preparedStatement.setString(9, prodotto.getPathImg1());
            preparedStatement.setString(10, prodotto.getPathImg2());
            preparedStatement.setString(11, prodotto.getPathImg3());

            preparedStatement.setInt(12, prodotto.getId_prodotto());

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
