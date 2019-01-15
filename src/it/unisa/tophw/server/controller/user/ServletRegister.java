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

@WebServlet(name = "/ServletRegister")
public class ServletRegister extends HttpServlet {

    String error="";
    UserDAO dao = new UserDAO();


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



        String email= request.getParameter("email");
        String nome=request.getParameter("name");
        String cognome=request.getParameter("cognome");
        String pass= request.getParameter("password");
        String username = request.getParameter("username");
        String dominio = email.substring(email.indexOf("@"), email.length());

        UserBean user = new UserBean();
        user.setCognome(request.getParameter("cognome"));
        user.setNome(request.getParameter("nome"));
        user.setEmail(request.getParameter("email"));
        user.setPassword(request.getParameter("password"));

        if(dominio.contains("tophw.it"))
            user.setRuolo("Admin");
        else
            user.setRuolo("User");



        if(verifica(request.getParameter("email"))) {
            dao.doSave(user);
            String success = "Complimenti "+user.getNome()+", ti sei registrato con successo. Accedi per iniziare ad acquistare";
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/login.jsp?errore="+success);
            dispatcher.forward(request, response);

        }
        else
        {

            String success = "Ops, la mail da te inserita : "+email+" ci risulta gia registrata, prova ad accedere.";
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/login.jsp?errore="+success);
            dispatcher.forward(request, response);

        }

    }


    protected boolean verifica(String mail) {
        UserBean tmp = null;

        tmp = dao.doRetriveByEmail(mail);
        if(tmp==null) return true;
        else{
            error+= "Utente gia presente";

            return false;}
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
