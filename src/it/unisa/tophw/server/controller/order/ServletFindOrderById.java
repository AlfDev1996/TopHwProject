package it.unisa.tophw.server.controller.order;

import it.unisa.tophw.server.model.beans.OrderBean;
import it.unisa.tophw.server.model.management.OrderDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ServletFindOrderById")
public class ServletFindOrderById extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String id_ordine = request.getParameter("id_ordine");

        int id= Integer.parseInt(id_ordine);
        OrderDAO ordineDao=new OrderDAO();
        OrderBean ordine = ordineDao.doRetriveById(id);

        if(ordine!=null)
        {
            ordine.inizializzaVociOrdine();
            request.setAttribute("ordine", ordine);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/riepilogo_ordine.jsp");
            dispatcher.forward(request, response);
        }
    }
}
