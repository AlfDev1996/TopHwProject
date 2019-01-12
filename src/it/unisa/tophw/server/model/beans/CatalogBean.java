package it.unisa.tophw.server.model.beans;

import java.util.ArrayList;

public class CatalogBean {

    private int id_catalogo;
    private String nomeCatalogo, descrizioneCatalogo;
    private ArrayList<ProductBean> prodottiCatalogo;

    public CatalogBean(int id_catalogo, String nomeCatalogo, String descrizioneCatalogo, ArrayList<ProductBean> prodottiCatalogo) {
        this.id_catalogo = id_catalogo;
        this.nomeCatalogo = nomeCatalogo;
        this.descrizioneCatalogo = descrizioneCatalogo;
        this.prodottiCatalogo = prodottiCatalogo;
    }

    public CatalogBean(){
        this.id_catalogo=0;
        this.nomeCatalogo="";
        this.descrizioneCatalogo="";
        this.prodottiCatalogo=new ArrayList<>();

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
