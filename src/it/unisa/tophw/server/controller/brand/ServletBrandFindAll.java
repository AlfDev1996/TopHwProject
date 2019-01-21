package it.unisa.tophw.server.controller.brand;

import com.google.gson.Gson;
import it.unisa.tophw.server.model.beans.BrandBean;
import it.unisa.tophw.server.model.management.BrandDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "ServletBrandFindAll")
public class ServletBrandFindAll extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        BrandDAO brandDAO=new BrandDAO();
        ArrayList<BrandBean> brands= brandDAO.doRetrieveAll(null);
        if(brands!=null && brands.size()>0)
        {
            Gson gson= new Gson();
            String jsonArray = gson.toJson(brands);
            response.getWriter().append(jsonArray);
        }
    }
}
