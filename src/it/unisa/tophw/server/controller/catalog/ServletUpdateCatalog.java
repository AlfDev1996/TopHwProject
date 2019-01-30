package it.unisa.tophw.server.controller.catalog;

import it.unisa.tophw.server.model.beans.CatalogBean;
import it.unisa.tophw.server.model.management.CatalogDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ServletUpdateCatalog")
public class ServletUpdateCatalog extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String  newNomeCatalogo="" , descCatalogo="", stringSconto="";
        int id_current_catalog=0;
        String msgOutput=""; boolean error=false;
        int sconto=0;
        id_current_catalog= Integer.parseInt((request.getParameter("selectCatalog")!=null && request.getParameter("selectCatalog").length()>0 ) ? request.getParameter("selectCatalog").toString() : "0");
        newNomeCatalogo= ( request.getParameter("mod_namecatalog")!=null && request.getParameter("mod_namecatalog").length()>0 ) ? request.getParameter("mod_namecatalog") : "";
        descCatalogo = (request.getParameter("mod_descrizionecatalogo")!=null && request.getParameter("mod_descrizionecatalogo").length()>0 ) ? request.getParameter("mod_descrizionecatalogo") : "";
        stringSconto = (request.getParameter("modscontocatalogo")!=null && request.getParameter("modscontocatalogo").length()>0) ? request.getParameter("modscontocatalogo") : "0";

        if(stringSconto!=null && stringSconto.length()>0 )
            sconto=Integer.parseInt(stringSconto);

        if(id_current_catalog>0){

            CatalogDAO catalogDAO=new CatalogDAO();
            CatalogBean catalogBean = new CatalogBean();

            catalogBean = catalogDAO.doRetriveByKey(id_current_catalog);

            if(catalogBean!=null && catalogBean.getId_catalogo()>0){

                if(newNomeCatalogo!=null && newNomeCatalogo.length()>0)
                    catalogBean.setNomeCatalogo(newNomeCatalogo.trim());
                if(descCatalogo!=null && descCatalogo.length()>0)
                    catalogBean.setDescrizioneCatalogo(descCatalogo.trim());
                if(sconto>0)
                    catalogBean.setSconto(sconto);

                boolean res = catalogDAO.doUpdate(catalogBean);
                if(!res){
                    error=true;
                    msgOutput="Non e' stato possibile effettuare la modifica";
                }else{
                    msgOutput="Modifica effettuata con successo";
                }
            }else{
                error=true;
                msgOutput="Catalogo non esistente, non è possibile effettuare la modifica";
            }

        }else{
            error=true;
            msgOutput="Non c'è riferimento al catalogo!";
        }

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/adminPanel.jsp?msgOutputUpdateCatalog=" + msgOutput + "");
        dispatcher.forward(request, response);



    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
