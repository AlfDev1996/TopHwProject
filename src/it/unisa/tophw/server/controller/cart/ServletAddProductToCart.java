package it.unisa.tophw.server.controller.cart;

import it.unisa.tophw.server.model.beans.CartBean;
import it.unisa.tophw.server.model.beans.ProductBean;
import it.unisa.tophw.server.model.beans.UserBean;
import it.unisa.tophw.server.model.management.ProductDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "ServletAddProductToCart")
public class ServletAddProductToCart extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        if(request.getParameter("id_prodotto")!= null && Integer.parseInt( request.getParameter("id_prodotto"))>0){
            int id = Integer.parseInt( request.getParameter("id_prodotto")) ;
            ProductBean prodotto = new ProductBean();
            ProductDAO prodDao = new ProductDAO();
            prodotto = prodDao.doRetriveById(id);
            if(request.getParameter("quantita")!=null)
            prodotto.setQuantita(Integer.parseInt(request.getParameter("quantita")));


            HttpSession currentSession = request.getSession();
            CartBean carrello = new CartBean();

            UserBean utente = (UserBean) currentSession.getAttribute("utente");

            if(utente!=null)
                carrello.setUtente(utente);

        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
