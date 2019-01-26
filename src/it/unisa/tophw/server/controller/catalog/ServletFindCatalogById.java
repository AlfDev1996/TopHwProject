package it.unisa.tophw.server.controller.catalog;

import com.google.gson.Gson;
import it.unisa.tophw.server.model.beans.CatalogBean;
import it.unisa.tophw.server.model.beans.ProductBean;
import it.unisa.tophw.server.model.management.CatalogDAO;
import it.unisa.tophw.server.model.management.ProductDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

@WebServlet(name = "ServletFindCatalogById")
public class ServletFindCatalogById extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int id = 0;

        if(request.getParameter("id_catalogo")!=null)
            id= Integer.parseInt(request.getParameter("id_catalogo"));

        CatalogBean catalogBean = new CatalogBean();
        CatalogDAO catalogDAO = new CatalogDAO();

        catalogBean=catalogDAO.doRetriveByKey(id);

        if(catalogBean!=null)
        {
            Gson gson= new Gson();
            String json = gson.toJson(catalogBean);
            response.getWriter().append(json);
        }


        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/product.jsp");
        dispatcher.forward(request, response);
    }
}
