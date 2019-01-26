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
import java.util.ArrayList;

@WebServlet(name = "ServletFindProductFromCatalog")
public class ServletFindProductFromCatalog extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ProductDAO prodDao = new ProductDAO();
        int id_catalogo = Integer.parseInt(request.getParameter("id_catalogo")+"");
        if(id_catalogo>0){
            ArrayList<ProductBean> prodotti = prodDao.doRetriveByCatalog(id_catalogo);


            request.setAttribute("prodotti", prodotti);

            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/allproduct.jsp");
            dispatcher.forward(request, response);
        }

    }
}
