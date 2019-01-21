package it.unisa.tophw.server.controller.catalog;

import com.google.gson.Gson;
import it.unisa.tophw.server.model.beans.BrandBean;
import it.unisa.tophw.server.model.beans.CatalogBean;
import it.unisa.tophw.server.model.management.BrandDAO;
import it.unisa.tophw.server.model.management.CatalogDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "ServletCatalogFindAll")
public class ServletCatalogFindAll extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        CatalogDAO catalogDAO=new CatalogDAO();
        ArrayList<CatalogBean> catalogBeans= catalogDAO.doRetrieveAll(null);
        if(catalogBeans!=null && catalogBeans.size()>0)
        {
            Gson gson= new Gson();
            String jsonArray = gson.toJson(catalogBeans);
            response.getWriter().append(jsonArray);
        }
    }
}
