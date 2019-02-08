package it.unisa.tophw.server.controller.cart;

import it.unisa.tophw.server.model.beans.CartBean;
import it.unisa.tophw.server.model.management.ProductDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "ServletDeleteProductFromCart")
public class ServletDeleteProductFromCart extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        if(request.getParameter("prodottiDaRimuovere")!= null && !request.getParameter("prodottiDaRimuovere").equals(""))
        {
            ProductDAO pdao = new ProductDAO();
            String appId= request.getParameter("prodottiDaRimuovere");
            String[] ids = appId.split(",");
            System.out.println(ids);

            HttpSession currentSession = request.getSession();
            CartBean carrello = new CartBean();

            if(currentSession.getAttribute("carrello")!=null )
                carrello = (CartBean) currentSession.getAttribute("carrello");
            if(ids.length>0){
                    for(int i =0;i<ids.length;++i){
                        carrello.deleteProdotto(pdao.doRetriveById(Integer.parseInt(ids[i])));



                    }


            }


        }

    }
}
