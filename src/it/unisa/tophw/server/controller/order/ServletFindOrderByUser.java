package it.unisa.tophw.server.controller.order;

import it.unisa.tophw.server.model.beans.OrderBean;
import it.unisa.tophw.server.model.beans.UserBean;
import it.unisa.tophw.server.model.management.OrderDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "ServletFindOrderByUser")
public class ServletFindOrderByUser extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession currentSession = request.getSession();
        UserBean utente = (UserBean) currentSession.getAttribute("utente");
        String error ="";
            if(utente!=null && utente.getId_utente()!=0)
            {
                ArrayList<OrderBean> ordini = new ArrayList<>();
                OrderDAO ordineDao=new OrderDAO();
                ordini = ordineDao.doRetrieveByUtente(utente);

                request.setAttribute("ordini", ordini);
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/lista_ordini.jsp?error="+error);
                dispatcher.forward(request, response);
            }
            else
            {
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
                dispatcher.forward(request, response);
            }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
