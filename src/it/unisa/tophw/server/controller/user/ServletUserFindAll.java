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

@WebServlet(name = "ServletUserFindAll")
public class ServletUserFindAll extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ArrayList<UserBean> users=new ArrayList<>();
        UserDAO userDAO=new UserDAO();

        users = userDAO.doRetrieveAll(null);

        request.setAttribute("utenti",users);


        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/adminPanel.jsp");
        dispatcher.forward(request, response);

    }
}
