package it.unisa.tophw.server.controller.user;

import it.unisa.tophw.server.model.beans.UserBean;
import it.unisa.tophw.server.model.management.UserDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.Console;
import java.io.IOException;

@WebServlet(name = "ServletLogin")
public class ServletLogin extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String msgOutput = "";
        UserBean utenteSession  = (UserBean) request.getSession().getAttribute("utente");

        if(utenteSession==null){
            String emailParameter = request.getParameter("email");
            if(emailParameter==null || emailParameter.isEmpty())
                emailParameter="";
            String passwordParameter= request.getParameter("pass");
            if(passwordParameter==null || passwordParameter.isEmpty())
                passwordParameter="";

            UserBean userToLogin = null;

            UserDAO userDAO=new UserDAO();
            userToLogin = userDAO.doRetriveByEmail(emailParameter);
            if(userToLogin==null) {
                msgOutput = "Email non presente";
            }else {
                if(!userToLogin.getPassword().equals(passwordParameter))
                    msgOutput="Password errata";
            }

            if(msgOutput=="") {
                System.out.println("Login effettuato con successo");
                HttpSession session = request.getSession();
                session.setAttribute("utente", userToLogin);

                msgOutput="ok";
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
                dispatcher.forward(request, response);
            }else {
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/login.jsp?msgOutput="+msgOutput);
                dispatcher.forward(request, response);
            }
        }else{

            //Entra con la sessione
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
            dispatcher.forward(request, response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
