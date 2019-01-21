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

@WebServlet(name = "ServletUpdateAddressUser")
public class ServletUpdateAddressUser extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        JSONParser parser = new JSONParser();
        String msgOutput="";
        boolean error=false;
        try {
            JSONObject addressJs = (JSONObject) parser.parse(request.getParameter("addressJs"));
            if(addressJs!=null){

                String email = null;
                int id_utente=0;

                AddressBean addressToUpdate = new AddressBean();
                addressToUpdate.setVia((String) addressJs.get("via"));

                String civico = addressJs.get("civico")!=null && addressJs.get("civico").toString()!=null && addressJs.get("civico").toString().length()>0 && !addressJs.get("civico").toString().equalsIgnoreCase("null") ? addressJs.get("civico").toString() : "";
                if(civico.length()>0)
                    addressToUpdate.setCivico(Integer.parseInt(civico));
                else
                    addressToUpdate.setCivico(0);

                String cap = addressJs.get("cap")!=null && addressJs.get("cap").toString()!=null && addressJs.get("cap").toString().length()>0 && !addressJs.get("cap").toString().equalsIgnoreCase("null") ? addressJs.get("cap").toString() : "";
                if(cap.length()>0)
                    addressToUpdate.setCap(Integer.parseInt(cap));
                else
                    addressToUpdate.setCap(0);


                addressToUpdate.setComune((String) addressJs.get("comune"));
                addressToUpdate.setNazione((String) addressJs.get("nazione"));
                addressToUpdate.setProvincia((String) addressJs.get("provincia"));


                if(addressJs.get("id_utente")!=null && addressJs.get("id_utente").toString()!=null && addressJs.get("id_utente").toString().length()>0){
                    id_utente=Integer.parseInt(addressJs.get("id_utente").toString());
                }else{
                    if(addressJs.get("email_utente")!=null && addressJs.get("email_utente").toString()!=null && addressJs.get("email_utente").toString().length()>0){
                        email=addressJs.get("email_utente").toString();
                    }
                }
                UserBean userBean = new UserBean();
                UserDAO userDAO = new UserDAO();
                if(id_utente==0 ){
                    if(email!=null){
                        userBean = userDAO.doRetriveByEmail(email);
                        if(userBean!=null && userBean.getId_utente()>0){
                            ;
                        }else{
                            msgOutput="Riferimenti utente errati, errore Email";
                            error=true;
                        }
                    }else{
                        msgOutput="Mancano i riferimenti utente";
                        error=true;
                    }
                }else{
                    userBean=userDAO.doRetriveById(id_utente);
                    if(userBean!=null && userBean.getId_utente()>0){
                        ;
                    }else{
                        msgOutput="Riferimenti utente errati, errore Id";
                        error=true;
                    }
                }


                if(!error && userBean!=null && userBean.getId_utente()>0){
                    //Salvataggio e modifica Indirizzo
                    AddressBean addressBean = new AddressBean();
                    AddressDAO addressDAO = new AddressDAO();

                    addressBean= addressDAO.doRetriveByUser(userBean);

                    if(addressBean==null)
                        addressBean=new AddressBean();

                    if(addressToUpdate!=null){
                        if(addressToUpdate.getVia()!=null && addressToUpdate.getVia().length()>0)
                            addressBean.setVia(addressToUpdate.getVia());
                        if( addressToUpdate.getCivico()>0)
                            addressBean.setCivico(addressToUpdate.getCivico());
                        if(addressToUpdate.getCap()>0)
                            addressBean.setCap(addressToUpdate.getCap());
                        if(addressToUpdate.getComune()!=null && addressToUpdate.getComune().length()>0)
                            addressBean.setComune(addressToUpdate.getComune());
                        if(addressToUpdate.getNazione()!=null && addressToUpdate.getNazione().length()>0)
                            addressBean.setNazione(addressToUpdate.getNazione());
                        if(addressToUpdate.getProvincia()!=null && addressToUpdate.getProvincia().length()>0)
                            addressBean.setProvincia(addressToUpdate.getProvincia());
                    }


                    if(addressBean!=null && addressBean.getId_indirizzo()>0){
                        //Modifica Indirizzo
                        if(addressToUpdate!=null){
                            boolean resUpdate = addressDAO.doUpdate(addressBean);
                            if(!resUpdate){
                                error=true;
                                msgOutput="Non e' stato possibile modificare l'indirizzo, contattare l'assistenza per eventuale risoluzione";
                            }
                            else{
                                msgOutput="Modifica indirizzo effettuata con successo!";
                                userBean.setIndirizzo(addressBean);
                            }
                         }
                    }else{
                        //Salvataggio Nuovo indirizzo
                        addressBean.setId_utente(userBean.getId_utente());
                        boolean resSave=addressDAO.doSave(addressBean);
                        if(!resSave){
                            error=true;
                            msgOutput="Non e' stato possibile salvare l'indirizzo, contattare l'assistenza per eventuale risoluzione";
                        }
                        else{
                            msgOutput="Salvataggio indirizzo effettuato con successo!";
                            userBean.setIndirizzo(addressBean);
                        }

                    }

                }else{
                    error=true;
                    msgOutput="Errore, non c'è un riferimento all'utente";
                }

                if(userBean!=null && userBean.getId_utente()>0){
                    HttpSession session = request.getSession();
                    session.setAttribute("utente", userBean);
                }else{
                    HttpSession session = request.getSession();
                    session.setAttribute("utente", null);
                }


            }else{
                //Json nullo
                error=true;
                msgOutput="Dati inconsistenti";
            }

            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/user.jsp?msgOutput="+msgOutput);
            dispatcher.forward(request, response);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}
