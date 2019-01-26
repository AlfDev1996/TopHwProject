package it.unisa.tophw.server.controller.cart;

import it.unisa.tophw.server.model.beans.CartBean;
import it.unisa.tophw.server.model.beans.ProductBean;
import it.unisa.tophw.server.model.management.ProductDAO;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "ServletUpdateProductInCart")
public class ServletUpdateProductInCart extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ProductBean prodotto = new ProductBean();
        String error="";




        if(request.getParameter("arrayProdottiModificaJson")!= null && !request.getParameter("arrayProdottiModificaJson").equals(""))
        {
            JSONParser parser = new JSONParser();
            try {
                HttpSession currentSession = request.getSession();
                CartBean carrello = new CartBean();

                if(currentSession.getAttribute("carrello")!=null )
                {
                    carrello = (CartBean) currentSession.getAttribute("carrello");
                    carrello.getProdotti().forEach(productBean -> {
                        System.out.println(productBean.getId_prodotto()+"-------"+productBean.getQuantita());
                    });

                    JSONArray prodottiJson= (JSONArray) parser.parse(request.getParameter("arrayProdottiModificaJson"));
                    for(Object o: prodottiJson){
                        if ( o instanceof JSONObject ) {
                            JSONObject obj = ((JSONObject)o);

                            ProductBean productBean = new ProductBean();
                            System.out.println(obj.get("quantita")+"<<<<<<<<<<<-------");
                            if(obj.get("id")+""!=null)
                                productBean.setId_prodotto(Integer.parseInt(obj.get("id")+""));
                            ProductDAO productDAO =new ProductDAO();
                            productBean=productDAO.doRetriveById(productBean.getId_prodotto());

                            if(productDAO!=null){
                                carrello.updateProdotto(Integer.parseInt(obj.get("quantita")+""),productBean );
                            }


                        }
                    }
                }

            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }


        }



        response.getWriter().append(error+"");
        request.setAttribute("error", error);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {




    }
}
