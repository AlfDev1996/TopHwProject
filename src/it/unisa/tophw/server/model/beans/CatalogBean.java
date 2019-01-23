package it.unisa.tophw.server.model.beans;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.stream.Collectors;

public class CatalogBean {

    private int id_catalogo;
    private String nomeCatalogo, descrizioneCatalogo;
    private ArrayList<ProductBean> prodottiCatalogo;
    private int sconto;

    public CatalogBean(int id_catalogo, String nomeCatalogo, String descrizioneCatalogo, ArrayList<ProductBean> prodottiCatalogo,int sconto) {
        this.id_catalogo = id_catalogo;
        this.nomeCatalogo = nomeCatalogo;
        this.descrizioneCatalogo = descrizioneCatalogo;
        this.prodottiCatalogo = prodottiCatalogo;
        this.sconto=sconto;
    }

    public CatalogBean(){
        this.id_catalogo=0;
        this.nomeCatalogo="";
        this.descrizioneCatalogo="";
        this.prodottiCatalogo=new ArrayList<>();
        this.sconto=0;

    }

    public int getId_catalogo() {
        return id_catalogo;
    }

    public void setId_catalogo(int id_catalogo) {
        this.id_catalogo = id_catalogo;
    }

    public String getNomeCatalogo() {
        return nomeCatalogo;
    }

    public void setNomeCatalogo(String nomeCatalogo) {
        this.nomeCatalogo = nomeCatalogo;
    }

    public String getDescrizioneCatalogo() {
        return descrizioneCatalogo;
    }

    public void setDescrizioneCatalogo(String descrizioneCatalogo) {
        this.descrizioneCatalogo = descrizioneCatalogo;
    }

    public ArrayList<ProductBean> getProdottiCatalogo() {
        return prodottiCatalogo;
    }

    public void setProdottiCatalogo(ArrayList<ProductBean> prodottiCatalogo) {
        this.prodottiCatalogo = prodottiCatalogo;
    }

    public int getSconto() {
        return sconto;
    }

    public void setSconto(int sconto) {
        this.sconto = sconto;
    }

    public String addProduct(ProductBean prodotto){

        if(prodotto!=null){
            if(this.prodottiCatalogo!=null && this.prodottiCatalogo.size()>0){
                this.prodottiCatalogo.forEach(productBean -> {
                    if(productBean.getId_prodotto()==prodotto.getId_prodotto()){
                        return ;
                    }else{
                        this.prodottiCatalogo.add(prodotto);
                    }
                });
            }else{
                this.prodottiCatalogo=new ArrayList<>();
                this.prodottiCatalogo.add(prodotto);
                return "ok";
            }
        }
        return "ok";
    }

    public String deleteProduct(ProductBean prodotto){
        if(prodotto!=null){
            if(this.prodottiCatalogo!=null && this.prodottiCatalogo.size()>0){
                Iterator it=this.prodottiCatalogo.iterator();
                while (it.hasNext()){
                    ProductBean p= (ProductBean) it.next();
                    if(p.getId_prodotto()==prodotto.getId_prodotto())
                        it.remove();
                }
            }else{
                return "lista Vuota";
            }
        }else
            return "prodotto Nullo";

        return "ok";
    }

    @Override
    public String toString() {
        return "CatalogBean{" +
                "id_catalogo=" + id_catalogo +
                ", nomeCatalogo='" + nomeCatalogo + '\'' +
                ", descrizioneCatalogo='" + descrizioneCatalogo + '\'' +
                ", prodottiCatalogo=" + prodottiCatalogo +
                '}';
    }
}
