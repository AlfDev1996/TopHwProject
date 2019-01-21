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
        boolean error = false;
        UserBean utenteSession  = (UserBean) request.getSession().getAttribute("utente");
        String emailParameter = request.getParameter("email");
        String passwordParameter= request.getParameter("pass");

        if(utenteSession==null){
            if(emailParameter==null || emailParameter.isEmpty())
                emailParameter="";

            if(passwordParameter==null || passwordParameter.isEmpty())
                passwordParameter="";

            UserBean userToLogin = null;

            UserDAO userDAO=new UserDAO();
            userToLogin = userDAO.doRetriveByEmail(emailParameter);
            if(userToLogin==null) {
                msgOutput = "Email non presente";
                error=true;
            }else {
                if(!userToLogin.getPassword().equals(passwordParameter)){
                    msgOutput="Password errata";
                    error=true;
                }

            }
            if(!error) {
                msgOutput="Login effettuato con successo";
                HttpSession session = request.getSession();
                session.setAttribute("utente", userToLogin);
            }else {
                error=true;
                msgOutput="Login incorretta";
            }
        }else{
            if(  ( emailParameter==null || emailParameter.isEmpty() ) || ( passwordParameter==null || passwordParameter.isEmpty() ) )
                ;
            else{
                HttpSession session = request.getSession();
                if(session!=null)
                    session.invalidate();
            }
        }
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp?msgOutput="+msgOutput);
       if(error)
           dispatcher = getServletContext().getRequestDispatcher("/login.jsp?msgOutput="+msgOutput);
       dispatcher.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
