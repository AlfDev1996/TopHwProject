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

@WebServlet(name = "ServletFindProductById")
public class ServletFindProductById extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = 0;

        if(request.getParameter("id_prodotto")!=null)
            id= Integer.parseInt(request.getParameter("id_prodotto"));

        ProductBean productBean = new ProductBean();
        ProductDAO productDAO = new ProductDAO();

        productBean=productDAO.doRetriveById(id);


        request.setAttribute("prodotto", productBean);

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/product.jsp");
        dispatcher.forward(request, response);


    }
}
