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
import javax.xml.ws.spi.http.HttpContext;
import java.io.IOException;
import java.util.HashMap;

@WebServlet(name = "ServletAddProductToCart")
public class ServletAddProductToCart extends HttpServlet {

    static HashMap<Integer, ProductBean> hmProductInCart;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id=0;
        int quantita =0;
        HttpSession currentSession = request.getSession();
        hmProductInCart = (HashMap<Integer, ProductBean>) currentSession.getAttribute("hashMapCart");

        CartBean carrello = new CartBean();
        carrello = (CartBean) currentSession.getAttribute("carrello");
        if(carrello==null)
            carrello=new CartBean();
        if(hmProductInCart==null)
            hmProductInCart=new HashMap<Integer, ProductBean>();

        if(request.getParameter("id_prodotto")!=null)
            id= Integer.parseInt(request.getParameter("id_prodotto"));
        if(request.getParameter("quantita")!=null)
            quantita= Integer.parseInt(request.getParameter("quantita"));


        ProductBean prodotto = new ProductBean();

        if(quantita>0 && id>0) {

            ProductDAO prodDao = new ProductDAO();
            if(request.getAttribute("prodotto")!=null)
                prodotto= (ProductBean) request.getAttribute("prodotto");
            else
                prodotto = prodDao.doRetriveById(id);

            int qtaRealeProdotto = prodotto.getQuantita();
            int newQta =0;

            if(prodotto.getId_prodotto()>0 && hmProductInCart!=null){
                ProductBean pMap = hmProductInCart.get(prodotto.getId_prodotto());
                if(pMap!=null){
                    pMap.setQuantita(pMap.getQuantita()+quantita);
                    newQta=carrello.addProduct(pMap);
                    prodotto.setQuantita(newQta);
                }
                else{
                    prodotto.setQuantita(quantita);
                    ProductBean tempProd = new ProductBean(prodotto);
                    hmProductInCart.put(tempProd.getId_prodotto(),tempProd);
                    carrello.addProduct(tempProd);
                }
            }
            UserBean utente = (UserBean) currentSession.getAttribute("utente");
            if (utente != null) {
                carrello.setUtente(utente);
            }
            currentSession.setAttribute("carrello", carrello);

            if(hmProductInCart.get(prodotto.getId_prodotto())!=null){
                qtaRealeProdotto = qtaRealeProdotto - hmProductInCart.get(prodotto.getId_prodotto()).getQuantita();
                prodotto.setQuantita(qtaRealeProdotto);
            }

            if(hmProductInCart!=null){
                currentSession.setAttribute("hashMapCart",hmProductInCart);
            }

        }
    if(prodotto!=null)
        request.setAttribute("prodotto", prodotto);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/product.jsp");
        dispatcher.forward(request, response);
    }

}
