package it.unisa.tophw.server.controller.cart;

import it.unisa.tophw.server.model.beans.CartBean;
import it.unisa.tophw.server.model.beans.ProductBean;
import it.unisa.tophw.server.model.beans.UserBean;
import it.unisa.tophw.server.model.management.ProductDAO;

import javax.servlet.RequestDispatcher;
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





    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id=0;
        int quantita =0;
        if(request.getParameter("id_prodotto")!=null)
            id= Integer.parseInt(request.getParameter("id_prodotto"));
        if(request.getParameter("quantita")!=null)
            quantita= Integer.parseInt(request.getParameter("quantita"));
        ProductBean prodotto = new ProductBean();
        if(quantita>0 && id>0) {


            ProductDAO prodDao = new ProductDAO();
            prodotto = prodDao.doRetriveById(id);
            prodotto.setQuantita(quantita);


            HttpSession currentSession = request.getSession();
            CartBean carrello = new CartBean();

            UserBean utente = (UserBean) currentSession.getAttribute("utente");


            if (utente != null) {
                carrello.setUtente(utente);
            }

            if (currentSession.getAttribute("carrello") != null) {
                carrello = (CartBean) currentSession.getAttribute("carrello");
                carrello.addProduct(prodotto);
                currentSession.setAttribute("carrello", carrello);


            } else {
                carrello.addProduct(prodotto);
                currentSession.setAttribute("carrello", carrello);


            }

        }
    if(prodotto!=null)
        request.setAttribute("prodotto", prodotto);

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/product.jsp");
        dispatcher.forward(request, response);
    }
}
