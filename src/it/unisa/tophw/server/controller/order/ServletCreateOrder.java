package it.unisa.tophw.server.controller.order;

import it.unisa.tophw.server.model.beans.*;
import it.unisa.tophw.server.model.management.OrderDAO;
import it.unisa.tophw.server.model.management.OrderVoiceDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

@WebServlet(name = "ServletCreateOrder")
public class ServletCreateOrder extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession currentSession = request.getSession();
        UserBean utente = (UserBean) currentSession.getAttribute("utente");
        String error ="";

        CartBean carrello = (CartBean) currentSession.getAttribute("carrello");

        OrderBean ordine= new OrderBean();

        if(carrello!=null && carrello.getProdotti()!=null && carrello.getProdotti().size()>0) {
            if ( utente!=null && utente.getId_utente()!=0 && utente.getEmail()!=null)
            {
                Calendar currenttime = Calendar.getInstance();
                java.sql.Date sqldate = new java.sql.Date((currenttime.getTime()).getTime());
                ordine.setData_creazione(sqldate);
                ordine.setStato("Creato");
                ordine.setTotale(Float.parseFloat(carrello.getPrezzoTotale()+""));
                ordine.setUtente(utente);
                OrderDAO ordineDao=new OrderDAO();
                int id_ordine = ordineDao.doSave(ordine);
                if(id_ordine!=-1)
                {
                    ordine.setId_ordine(id_ordine);
                    ArrayList<OrderVoiceBean> vociOrdine = new ArrayList<>();
                    OrderVoiceDAO voceOrdineDao=new OrderVoiceDAO();
                    for (ProductBean prodotto : carrello.getProdotti()) {
                        OrderVoiceBean voceOrdine = new OrderVoiceBean();
                        voceOrdine.setOrdine(ordine);
                        voceOrdine.setProdotto(prodotto);
                        voceOrdine.setPrezzo_unitario(prodotto.getPrezzo());
                        voceOrdine.setPrezzo_totale(prodotto.getPrezzo()*prodotto.getQuantita());
                        voceOrdine.setQuantita(prodotto.getQuantita());
                        voceOrdineDao.doSave(voceOrdine);
                        ordine.getVociOrdine().add(voceOrdine);
                        error="Grazie, il tuo ordine e' stato ricevuto ed e' in fase di lavorazione";
                    }


                }

            }
            else
                error+="errore utente ";
        }
        else
            error+="Non ci sono articoli nel carrello";
        if(carrello!=null && carrello.getProdotti()!=null && carrello.getProdotti().size()>0){
            carrello.getProdotti().clear();

            request.setAttribute("ordine", ordine);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp?error="+error);
            dispatcher.forward(request, response);
        }
    }

}
