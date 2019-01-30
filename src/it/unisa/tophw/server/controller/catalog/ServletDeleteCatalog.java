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

@WebServlet(name = "ServletDeleteCatalog")
public class ServletDeleteCatalog extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int id_catalogo=0;
        boolean error=false;
        String msgOutput="";
        id_catalogo = Integer.parseInt((request.getParameter("selectCatalog")!=null && request.getParameter("selectCatalog").length()>0) ? request.getParameter("selectCatalog").toString() : "0");

        if(!(id_catalogo>0)){
            error=true;
            msgOutput="Non c'è riferimento al catalogo";
        }

        if(!error){
            CatalogBean catalogBean = new CatalogBean();
            CatalogDAO catalogDAO=new CatalogDAO();

            if(catalogDAO.doRetriveByKey(id_catalogo)!=null){
                boolean res = catalogDAO.doDelete(id_catalogo);
                if(!res){
                    error=true;
                    msgOutput="Non e' stato possibile rimuovere il catalogo";
                }else{
                    msgOutput="Rimozione catalogo eseguita con successo";
                }
            }else{
                error=true;
                msgOutput="Catalogo inesistente";
            }

        }

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/adminPanel.jsp?msgOutputDeleteCatalog=" + msgOutput + "");
        dispatcher.forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
