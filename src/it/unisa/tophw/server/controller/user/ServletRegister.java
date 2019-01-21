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



    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String msgOutput = "";
        boolean error=false;
        UserDAO dao = new UserDAO();
        String email= request.getParameter("email")!=null ? request.getParameter("email") : "";
        String nome=request.getParameter("name")!=null ? request.getParameter("name") : "" ;
        String cognome=request.getParameter("cognome")!=null ? request.getParameter("cognome") : "";
        String pass= request.getParameter("password")!=null ? request.getParameter("password") : "";
        String dominio = email.substring(email.indexOf("@"),email.length());
        UserBean user = new UserBean();
        if(!email.isEmpty() && !nome.isEmpty() && !cognome.isEmpty() && !pass.isEmpty() && !error){
            user.setCognome(cognome);
            user.setNome(nome);
            user.setEmail(email);
            user.setPassword(pass);
            if(dominio.contains("tophw.it"))
                user.setRuolo("Admin");
            else
                user.setRuolo("User");
        }else{
            msgOutput="Parametro nullo";
            error=true;
        }


        if(!error){
            if(verifica(request.getParameter("email"),dao,msgOutput)) {
                dao.doSave(user);
                msgOutput = "Complimenti "+user.getNome()+", ti sei registrato con successo. Accedi per iniziare ad acquistare";
            }else{
                error=true;
                msgOutput="Ops, la mail da te inserita : "+email+" ci risulta gia registrata, prova ad accedere.";
            }
        }

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/login.jsp?msgOutput="+msgOutput);
        dispatcher.forward(request, response);

    }


    protected boolean verifica(String mail, UserDAO dao, String error) {
        UserBean tmp = new UserBean();
        tmp = dao.doRetriveByEmail(mail);
        if(tmp==null) return true;
        else{
            return false;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
