package it.unisa.tophw.server.controller.brand;

import it.unisa.tophw.server.model.beans.BrandBean;
import it.unisa.tophw.server.model.management.BrandDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ServletUpdateBrand")
public class ServletUpdateBrand extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String  newNomeBrand="" ;
        int id_current_brand=0;
        String msgOutput=""; boolean error=false;
        id_current_brand= Integer.parseInt((request.getParameter("selectBrand")!=null && request.getParameter("selectBrand").length()>0 ) ? request.getParameter("selectBrand").toString() : "0");
        newNomeBrand= ( request.getParameter("mod_namebrand")!=null && request.getParameter("mod_namebrand").length()>0 ) ? request.getParameter("mod_namebrand") : "";

        if(id_current_brand>0){

            BrandDAO brandDAO=new BrandDAO();
            BrandBean brandBean = new BrandBean();

            brandBean = brandDAO.doRetriveByKey(id_current_brand);

            if(brandBean!=null && brandBean.getIdMarca()>0){

                if(newNomeBrand!=null && newNomeBrand.length()>0)
                    brandBean.setNome(newNomeBrand.trim());
                boolean res = brandDAO.doUpdate(brandBean);
                if(!res){
                    error=true;
                    msgOutput="Non e' stato possibile effettuare la modifica";
                }else{
                    msgOutput="Modifica effettuata con successo";
                }
            }else{
                error=true;
                msgOutput="Brand non esistente, non è possibile effettuare la modifica";
            }

        }else{
            error=true;
            msgOutput="Non c'è riferimento al Brand!";
        }

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/adminPanel.jsp?msgOutput=" + msgOutput + "");
        dispatcher.forward(request, response);


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
