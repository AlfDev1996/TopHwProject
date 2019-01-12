package it.unisa.tophw.server.model.beans;

import java.security.ProtectionDomain;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

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

    public void addProduct(ProductBean prodotto){
        if(prodotto!=null)
        {
            if(this.prodotti!=null && this.prodotti.size()>0){

                this.prodotti.stream().forEach(productBean -> {
                    if(productBean.getId_prodotto()==prodotto.getId_prodotto() && productBean.getNome().equalsIgnoreCase(prodotto.getNome())){
                        productBean.setQuantita(productBean.getQuantita() !=0 ? (productBean.getQuantita()+prodotto.getQuantita()) : ( 0+prodotto.getQuantita() ) );
                        return ;
                    }
                });
            }else{
                this.prodotti.add(prodotto);
                return;
            }


        }
        prodotti.add(prodotto);
    }

    public void updateProdotto(int quantita, ProductBean prodotto) {

        if(this.prodotti!=null && this.prodotti.size()>0){

            this.prodotti.stream().forEach(productBean -> {
                if(productBean.getId_prodotto()==prodotto.getId_prodotto() && productBean.getNome().equalsIgnoreCase(prodotto.getNome())){
                    productBean.setQuantita(productBean.getQuantita() !=0 ? (productBean.getQuantita()+quantita) : ( 0+quantita ) );
                    return ;
                }
            });

        }else{
            return ;
        }
    }

    public void deleteProdotto(ProductBean prodotto){

        if(this.prodotti!=null && this.prodotti.size()>0){
            Iterator it = this.prodotti.iterator();
            while(it.hasNext()){
                ProductBean prod = (ProductBean) it.next();
                if(prod.getId_prodotto()==prodotto.getId_prodotto())
                    it.remove();
                    return ;
            }
        }else{
            return ;
        }

    }

    public void deleteAllProducts(){
        this.prodotti.clear();
    }




}
