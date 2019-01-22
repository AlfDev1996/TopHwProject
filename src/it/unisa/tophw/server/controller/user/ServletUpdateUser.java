package it.unisa.tophw.server.controller.user;



import it.unisa.tophw.server.model.beans.AddressBean;
import it.unisa.tophw.server.model.beans.UserBean;
import it.unisa.tophw.server.model.management.AddressDAO;
import it.unisa.tophw.server.model.management.UserDAO;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet(name = "ServletUpdateUser")
public class ServletUpdateUser extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JSONParser parser = new JSONParser();
        String msgOutput="";
        boolean error=false;
        try {

            JSONObject utenteJs= (JSONObject) parser.parse(request.getParameter("utenteJs"));
            if(utenteJs!=null){
                String email = utenteJs.get("email")!=null ? (String) utenteJs.get("email") : "";
                String password = utenteJs.get("password")!=null ? (String) utenteJs.get("password") : "";
                if(email.length()>0 && password.length()>0){
                    UserBean utente = new UserBean();
                    UserDAO utenteDAO=new UserDAO();
                    utente = utenteDAO.doRetriveByEmail(email);
                    if(utente!=null){
                        String nome = utenteJs.get("nome")!=null && utenteJs.get("nome").toString().length()>0 ? (String) utenteJs.get("nome") : null;
                        String cognome = utenteJs.get("cognome")!=null && utenteJs.get("cognome").toString().length()>0 ? (String) utenteJs.get("cognome") : null;
                        //utente.setEmail(email);
                        utente.setPassword(password);
                        if(nome!=null)
                            utente.setNome(nome);
                        if(cognome!=null)
                            utente.setCognome(cognome);

                        boolean res=false;
                        if(utente!=null && utente.getId_utente()>0)
                            res = utenteDAO.doUpdate(utente);
                        if(res) {
                            msgOutput = "ok";

                            //Inizializzo l'indirizzo se esiste
                            if(utente.getId_utente()>0){
                                AddressBean addressBean=null;
                                AddressDAO addressDAO = new AddressDAO();

                                addressBean = addressDAO.doRetriveByUser(utente);
                                if(addressBean!=null){
                                    utente.setIndirizzo(addressBean);
                                }
                            }


                            HttpSession session = request.getSession();
                            session.setAttribute("utente", utente);
                        }

                        else
                            msgOutput="Non e' stato possibile modificare i dati";

                    }else{
                        msgOutput="Dati incorretti";
                        error=true;
                    }
                }else{
                    msgOutput="Email o password nulli";
                    error=true;
                }

            }else{
                //Json nullo
                msgOutput="Dati inconsistenti!";
            }


        } catch (ParseException e) {
            e.printStackTrace();
        }

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/user.jsp?msgOutput="+msgOutput);
        dispatcher.forward(request, response);
    }
}
