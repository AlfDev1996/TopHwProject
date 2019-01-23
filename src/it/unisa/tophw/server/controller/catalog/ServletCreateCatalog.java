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

@WebServlet(name = "ServletCreateCatalog")
public class ServletCreateCatalog extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        boolean error=false;
        String msgOutput="";
        String nomeCatalogo = "", descrCatalogo="",  stringSconto="";
        int sconto=0;

        nomeCatalogo= ( request.getParameter("namecatalogo")!=null && request.getParameter("namecatalogo").length()>0 ) ? request.getParameter("namecatalogo") : "";
        descrCatalogo = (request.getParameter("desc_catalogo")!=null && request.getParameter("desc_catalogo").length()>0 ) ? request.getParameter("desc_catalogo") : "";
        stringSconto = (request.getParameter("scontocatalogo")!=null && request.getParameter("scontocatalogo").length()>0) ? request.getParameter("scontocatalogo") : "";

        if(stringSconto!=null && stringSconto.length()>0 )
            sconto=Integer.parseInt(stringSconto);

        CatalogBean catalogBean=new CatalogBean();
        CatalogDAO catalogDAO = new CatalogDAO();

        if(nomeCatalogo!=null && nomeCatalogo.length()>0)
            catalogBean.setNomeCatalogo(nomeCatalogo);
        else{
            error=true;
            msgOutput="Nome catalogo obbligatorio !";
        }
        if(!error){

            if(descrCatalogo!=null && descrCatalogo.length()>0)
                catalogBean.setDescrizioneCatalogo(descrCatalogo);
            if(sconto>0)
                catalogBean.setSconto(sconto);

            //Salvo il catalogo
            boolean res = catalogDAO.doSave(catalogBean);
            if(!res){
                error=true;
                msgOutput="Errore durante il Salvataggio del catalogo";
            }else{
                msgOutput="Salvataggio Catalogo effettuato con successo";
            }

        }else{
            msgOutput="Errore ";
        }


        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/adminPanel.jsp?msgOutput=" + msgOutput + "");
        dispatcher.forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
