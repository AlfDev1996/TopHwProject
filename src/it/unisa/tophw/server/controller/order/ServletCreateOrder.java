package it.unisa.tophw.server.controller.order;

import it.unisa.tophw.server.model.beans.*;
import it.unisa.tophw.server.model.management.OrderDAO;
import it.unisa.tophw.server.model.management.OrderVoiceDAO;
import it.unisa.tophw.server.model.management.ProductDAO;

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
import java.util.HashMap;

@WebServlet(name = "ServletCreateOrder")
public class ServletCreateOrder extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        boolean error=false;
        HttpSession currentSession = request.getSession();
        UserBean utente = (UserBean) currentSession.getAttribute("utente");
        String msgOutput="";
        RequestDispatcher dispatcher = null;
        if(utente!=null && utente.getIndirizzo()!=null && utente.getIndirizzo().getId_indirizzo()>0){

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
                            if(prodotto!=null && prodotto.getId_prodotto()>0){
                                //Scalo la quantita' dal prodotto
                                ProductDAO productDAO = new ProductDAO();
                                boolean resUpdateProdotto = productDAO.decreaseQuantityProduct(voceOrdine);
                                if(!resUpdateProdotto){
                                    System.out.println("Update prodotto non effettuata");
                                }
                            }
                            voceOrdine.setPrezzo_unitario(prodotto.getPrezzo());
                            voceOrdine.setPrezzo_totale(prodotto.getPrezzo()*prodotto.getQuantita());
                            voceOrdine.setQuantita(prodotto.getQuantita());
                            voceOrdineDao.doSave(voceOrdine);
                            ordine.getVociOrdine().add(voceOrdine);
                            msgOutput="Grazie, il tuo ordine e' stato ricevuto ed e' in fase di lavorazione";
                        }
                    }

                }
                else
                    msgOutput+="errore utente ";
            }
            else
                msgOutput+="Non ci sono articoli nel carrello";

            request.getSession().setAttribute("carrello", new CartBean());
            request.getSession().setAttribute("hashMapCart", new HashMap<Integer, ProductBean>());

            if(carrello!=null && carrello.getProdotti()!=null && carrello.getProdotti().size()>0){
                carrello.getProdotti().clear();

                request.setAttribute("ordine", ordine);
                dispatcher = getServletContext().getRequestDispatcher("/riepilogo_ordine.jsp?msgOutput="+error);


            }

        }else{
            dispatcher = getServletContext().getRequestDispatcher("/checkout.jsp?msgOutput=IndirizzoNonInserito");

        }


        dispatcher.forward(request, response);

    }

}
