package it.unisa.tophw.server.controller.brand;

import it.unisa.tophw.server.model.beans.BrandBean;
import it.unisa.tophw.server.model.beans.CatalogBean;
import it.unisa.tophw.server.model.management.BrandDAO;
import it.unisa.tophw.server.model.management.CatalogDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ServletDeleteBrand")
public class ServletDeleteBrand extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int id_brand=0;
        boolean error=false;
        String msgOutput="";
        id_brand = Integer.parseInt((request.getParameter("selectMarca")!=null && request.getParameter("selectMarca").length()>0) ? request.getParameter("selectMarca").toString() : "0");

        if(!(id_brand>0)){
            error=true;
            msgOutput="Non c'è riferimento al brand";
        }

        if(!error){
            BrandBean brandBean = new BrandBean();
            BrandDAO brandDAO=new BrandDAO();

            if(brandDAO.doRetriveByKey(id_brand)!=null){
                boolean res = brandDAO.doDelete(id_brand);
                if(!res){
                    error=true;
                    msgOutput="Non e' stato possibile rimuovere il brand";
                }else{
                    msgOutput="Rimozione brand eseguita con successo";
                }
            }else{
                error=true;
                msgOutput="Brand inesistente";
            }

        }

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/adminPanel.jsp?msgOutput=" + msgOutput + "");
        dispatcher.forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
