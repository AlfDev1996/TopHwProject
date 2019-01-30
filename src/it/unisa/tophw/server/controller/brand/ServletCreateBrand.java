package it.unisa.tophw.server.controller.brand;

import it.unisa.tophw.server.model.beans.BrandBean;
import it.unisa.tophw.server.model.beans.BrandBean;
import it.unisa.tophw.server.model.management.BrandDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ServletCreateBrand")
public class ServletCreateBrand extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        boolean error=false;
        String msgOutput="";
        String nomeBrand = "";

        nomeBrand= ( request.getParameter("namebrand")!=null && request.getParameter("namebrand").length()>0 ) ? request.getParameter("namebrand") : "";

        BrandBean brandBean=new BrandBean();
        BrandDAO brandDAO = new BrandDAO();

        if(nomeBrand!=null && nomeBrand.length()>0)
            brandBean.setNome(nomeBrand);
        else{
            error=true;
            msgOutput="Nome Brand obbligatorio !";
        }
        if(!error){

            //Salvo il brand
            boolean res = brandDAO.doSave(brandBean);
            if(!res){
                error=true;
                msgOutput="Errore durante il Salvataggio del Brand";
            }else{
                msgOutput="Salvataggio Brand effettuato con successo";
            }

        }else{
            msgOutput="Errore ";
        }


        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/adminPanel.jsp?msgOutputCreateBrand=" + msgOutput + "");
        dispatcher.forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
