package it.unisa.tophw.server.model.beans;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicBoolean;

public class CartBean {

    private UserBean utente;// utenteBean
    ArrayList<ProductBean> prodotti =null;
    private double prezzoTotale;

    public CartBean(UserBean utente, ArrayList<ProductBean> prodotti) {
        this.utente= utente;
        this.prodotti = prodotti;
    }
    public CartBean() {
        prodotti = new ArrayList<>();
        utente=new UserBean();
    }

    public UserBean getUtente() {
        return utente;
    }

    public void setUtente(UserBean utente) {
        this.utente = utente;
    }

    public ArrayList<ProductBean> getProdotti() {
        return prodotti;
    }

    public void setProdotti(ArrayList<ProductBean> prodotti) {
        this.prodotti = prodotti;
    }

    public double getPrezzoTotale() {
        return prezzoTotale;
    }

    public void setPrezzoTotale(double prezzoTotale) {
        this.prezzoTotale = prezzoTotale;
    }

    public int addProduct(ProductBean prodotto){

        if(prodotto!=null)
        {
            if(this.prodotti!=null && this.prodotti.size()>0){
                for( ProductBean productBean : this.prodotti){
                    if(productBean.getId_prodotto()==prodotto.getId_prodotto())
                    {
                        //productBean.setQuantita(productBean.getQuantita()+prodotto.getQuantita());
                        productBean.setQuantita(prodotto.getQuantita());
                        calcolaPrezzoCarrello();
                        return productBean.getQuantita();
                    }
                }
                this.prodotti.add(prodotto);
                calcolaPrezzoCarrello();
                return prodotto.getQuantita();
            }else{
                this.prodotti.add(prodotto);
                calcolaPrezzoCarrello();
                return prodotto.getQuantita();
            }
        }
        return 0;
    }

    public void calcolaPrezzoCarrello() {
        this.prezzoTotale=0;
        if(this!=null && this.prodotti!=null && this.prodotti.size()>0){
            for ( ProductBean p : this.prodotti ){
                if(p.getPrezzo()!=0 && p.getQuantita()!=0){
                    this.prezzoTotale+= (p.getPrezzo()*p.getQuantita());
                }
            }
        }else{
            this.prezzoTotale=0;
        }

    }

    public boolean check(ProductBean productBean, ProductBean productNew){
        if(productBean.getId_prodotto()==productNew.getId_prodotto()){
            productBean.setQuantita(productBean.getQuantita()+productNew.getQuantita());
            return true;
        }
        return false;

    }

    public String updateProdotto(int quantita, ProductBean prodotto) {

        if(this.prodotti!=null && this.prodotti.size()>0){

            this.prodotti.stream().forEach(productBean -> {
                if(productBean.getId_prodotto()==prodotto.getId_prodotto()){
                    productBean.setQuantita(quantita);
                }
            });

        }else{
            return "Lista Vuota";
        }

        return "ok";
    }

    public String deleteProdotto(ProductBean prodotto){

        if(this.prodotti!=null && this.prodotti.size()>0){
            Iterator it = this.prodotti.iterator();
            while(it.hasNext()){
                ProductBean prod = (ProductBean) it.next();
                if(prod.getId_prodotto()==prodotto.getId_prodotto())
                    it.remove();
            }
        }else{
            return "Lista vuota";
        }
        return "ok";
    }

    public void deleteAllProducts(){
        this.prodotti.clear();
    }




}
