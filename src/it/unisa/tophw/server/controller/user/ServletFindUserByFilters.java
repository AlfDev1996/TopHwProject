package it.unisa.tophw.server.controller.user;

import it.unisa.tophw.server.model.beans.UserBean;
import it.unisa.tophw.server.model.management.UserDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "ServletFindUserByFilters")
public class ServletFindUserByFilters extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String nome = "", cognome="", email="";
        boolean error=false;
        String msgOutput="";
        nome = (request.getParameter("nome")!=null && request.getParameter("nome").length()>0) ? request.getParameter("nome") : null;
        cognome = (request.getParameter("cognome")!=null && request.getParameter("cognome").length()>0) ? request.getParameter("cognome") : null;
        email = (request.getParameter("email")!=null && request.getParameter("email").length()>0) ? request.getParameter("email") : null;

        UserBean userBean = new UserBean();
        UserDAO userDAO = new UserDAO();

        if(nome!=null)
            userBean.setNome(nome);
        else
            userBean.setNome(null);

        if(cognome!=null)
            userBean.setCognome(cognome);
        else
            userBean.setCognome(null);

        if(email!=null)
            userBean.setEmail(email);
        else
            userBean.setEmail(null);

        ArrayList<UserBean> users = new ArrayList<UserBean>();
        if(userBean!=null){
            users = userDAO.doRetriveByFilters(userBean,null);
        }

        request.setAttribute("utenti", users);
        request.setAttribute("selectedTab", "y");
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/adminPanel.jsp");
        dispatcher.forward(request, response);

    }
}
