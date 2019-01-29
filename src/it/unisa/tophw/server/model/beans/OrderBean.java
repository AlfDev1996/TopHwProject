package it.unisa.tophw.server.model.beans;

import it.unisa.tophw.server.model.management.OrderVoiceDAO;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

public class OrderBean {

    private int id_ordine;
    private Date data_creazione;
    private String stato,tipoPagamento;
    private float totale;
    private ArrayList<OrderVoiceBean> vociOrdine;
    private UserBean utente;
    private AddressBean indirizzoSpedizione;

    public OrderBean() {
        this.id_ordine=0;
        this.data_creazione = new Date();
        this.stato="";
        this.totale=0;
        this.utente=new UserBean();
        vociOrdine= new ArrayList<>();
        indirizzoSpedizione=new AddressBean();
        tipoPagamento="";
    }



    public OrderBean(int id_ordine, Date data_creazione, String stato, String indirizzo, float totale, ArrayList<OrderVoiceBean> vociOrdine, UserBean utente,AddressBean indirizzoSpedizione, String tipoPagamento) {
        this.id_ordine = id_ordine;
        this.data_creazione = data_creazione;
        this.stato = stato;
        this.totale = totale;
        this.vociOrdine = vociOrdine;
        this.utente = utente;
        this.indirizzoSpedizione=indirizzoSpedizione;
        this.tipoPagamento=tipoPagamento;
    }

    public int getId_ordine() {
        return id_ordine;
    }

    public void setId_ordine(int id_ordine) {
        this.id_ordine = id_ordine;
    }

    public Date getData_creazione() {
        return data_creazione;
    }

    public void setData_creazione(Date data_creazione) {
        this.data_creazione = data_creazione;
    }

    public String getStato() {
        return stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }

    public String getTipoPagamento() {
        return tipoPagamento;
    }

    public void setTipoPagamento(String tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }

    public AddressBean getIndirizzoSpedizione() {
        return indirizzoSpedizione;
    }

    public void setIndirizzoSpedizione(AddressBean indirizzoSpedizione) {
        this.indirizzoSpedizione = indirizzoSpedizione;
    }

    public float getTotale() {
        return totale;
    }

    public void setTotale(float totale) {
        this.totale = totale;
    }

    public ArrayList<OrderVoiceBean> getVociOrdine() {
        return vociOrdine;
    }

    public void setVociOrdine(ArrayList<OrderVoiceBean> vociOrdine) {
        this.vociOrdine = vociOrdine;
    }

    public UserBean getUtente() {
        return utente;
    }

    public void setUtente(UserBean utente) {
        this.utente = utente;
    }

    public String addVoceOrdine(OrderVoiceBean orderVoice){

        if(orderVoice!=null){
            if(this.vociOrdine!=null && this.vociOrdine.size()>0){
                this.vociOrdine.forEach(orderVoiceBean -> {
                    if(orderVoiceBean.getId_voce_ordine()==orderVoice.getId_voce_ordine()){
                        orderVoiceBean.setQuantita(orderVoiceBean.getQuantita()+orderVoice.getQuantita());
                    }else{
                        this.vociOrdine.add(orderVoice);
                    }
                });
            }else{
                this.vociOrdine=new ArrayList<>();
                this.vociOrdine.add(orderVoice);
                return "ok";
            }
        }
        return "ok";
    }

    public String deleteOrderVoice(OrderVoiceBean orderVoice){
        if(orderVoice!=null){
            if(this.vociOrdine!=null && this.vociOrdine.size()>0){
                Iterator it=this.vociOrdine.iterator();
                while (it.hasNext()){
                    OrderVoiceBean p= (OrderVoiceBean) it.next();
                    if(p.getId_voce_ordine()==orderVoice.getId_voce_ordine())
                        it.remove();
                }
            }else{
                return "lista Vuota";
            }
        }else
            return "Voce  NullA";

        return "ok";
    }



    @Override
    public String toString() {
        return "OrderBean{" +
                "id_ordine=" + id_ordine +
                ", data_creazione=" + data_creazione +
                ", stato='" + stato + '\'' +
                ", tipoPagamento='" + tipoPagamento + '\'' +
                ", totale=" + totale +
                ", vociOrdine=" + vociOrdine +
                ", utente=" + utente +
                ", indirizzoSpedizione=" + indirizzoSpedizione +
                '}';
    }

    public void inizializzaVociOrdine() {

        if(this!=null && this.id_ordine!=0)
        {
            OrderVoiceDAO voceDao=new OrderVoiceDAO();
            this.vociOrdine.addAll(voceDao.doRetriveByOrdine(this.id_ordine));
        }


    }
}
