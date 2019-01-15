package it.unisa.tophw.server.controller.product;

import it.unisa.tophw.server.model.beans.ProductBean;
import it.unisa.tophw.server.model.management.ProductDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ServletDeleteProduct")
public class ServletDeleteProduct extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String nome = request.getParameter("Nome_Prodotto").trim();
        int idProdotto = Integer.parseInt( !request.getParameter("id_prodotto").isEmpty() ? request.getParameter("id_prodotto") : "0");

        ProductBean prodotto=new ProductBean();
        ProductDAO prodottoDAO=new ProductDAO();
        if(idProdotto>0){
            prodotto= prodottoDAO.doRetriveById(idProdotto);
        }else{
            prodotto = prodottoDAO.doRetriveByNome(nome);
        }

        boolean res = false;
        if(prodotto!=null && prodotto.getId_prodotto()>0){
            res = prodottoDAO.doDelete(prodotto.getId_prodotto());
        }

        String msgOutput="";
        if(res){
            msgOutput="ok";
        }else{
            msgOutput="Errore durante l'eliminazione del prodotto";
        }

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/ModificaArticolo.jsp?msgOutput="+msgOutput+"");
        dispatcher.forward(request, response);


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
