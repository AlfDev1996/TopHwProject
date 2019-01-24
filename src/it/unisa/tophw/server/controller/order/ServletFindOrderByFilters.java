package it.unisa.tophw.server.controller.order;

import it.unisa.tophw.server.model.beans.OrderBean;
import it.unisa.tophw.server.model.beans.UserBean;
import it.unisa.tophw.server.model.management.OrderDAO;
import it.unisa.tophw.server.model.management.UserDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@WebServlet(name = "ServletFindOrderByFilters")
public class ServletFindOrderByFilters extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String stringData = (request.getParameter("data_ordine")!=null && request.getParameter("data_ordine").length()>0) ? request.getParameter("data_ordine").toString() : null;
        Integer id_utente= (request.getParameter("id_utente")!=null && request.getParameter("id_utente").length()>0) ? Integer.parseInt(request.getParameter("id_utente").toString()) : null;

        OrderBean orderBean=new OrderBean();
        OrderDAO orderDAO=new OrderDAO();

        if(stringData!=null && stringData.length()>0){
            Date data_ordine = new Date();
            SimpleDateFormat formatter=new SimpleDateFormat("dd/MM/yyyy");
            try {
                data_ordine= formatter.parse(stringData);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if(data_ordine!=null)
                orderBean.setData_creazione(data_ordine);
            else
                orderBean.setData_creazione(null);
        }else{
            orderBean.setData_creazione(null);
        }

        if(id_utente!=null && id_utente>0){

            UserBean userBean=new UserBean();
            UserDAO userDAO=new UserDAO();
            userBean=userDAO.doRetriveById(id_utente);
            if(userBean!=null)
                orderBean.setUtente(userBean);
            else
                orderBean.setUtente(null);
        }else{
            orderBean.setUtente(null);
        }

        ArrayList<OrderBean> orders = new ArrayList<OrderBean>();
        if(orderBean!=null){
            orders = orderDAO.doRetriveByFilters(orderBean);
        }

        request.setAttribute("ordini", orders);

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/adminPanel.jsp");
        dispatcher.forward(request, response);



    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
