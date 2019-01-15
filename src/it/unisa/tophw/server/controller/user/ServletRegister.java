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


    System.out.println("Ciao");
        String email= request.getParameter("email");
        String nome=request.getParameter("name");
        String cognome=request.getParameter("cognome");
        String pass= request.getParameter("password");
        //String dominio = email.substring(email.indexOf("@"),email.length());
    String dominio ="";
        UserBean user = new UserBean();
        user.setCognome(cognome);
        user.setNome(nome);
        user.setEmail(email);
        user.setPassword(pass);


        if(dominio.contains("tophw.it"))
            user.setRuolo("Admin");
        else
            user.setRuolo("User");
    System.out.println(user.toString());


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
