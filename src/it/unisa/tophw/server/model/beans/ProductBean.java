package it.unisa.tophw.server.model.beans;

import java.util.ArrayList;

public class ProductBean {

    private int id_prodotto, quantita, perc_sconto;
    private String nome, descrizione_breve, descrizione_estesa, pathImg1, pathImg2, pathImg3;
    private double prezzo ;
    private int id_marca;
    private int id_catalogo;

    public ProductBean() {
        id_prodotto=0;
        nome=descrizione_breve=descrizione_estesa=pathImg1=pathImg2=pathImg3="";
        prezzo=0.0;
        id_marca=0;
        quantita=0;
        perc_sconto=0;
        id_catalogo=0;
    }

    public ProductBean(int id_prodotto, int quantita, String nome, String descrizione_breve, String descrizione_estesa, String pathImg1, String pathImg2, String pathImg3, double prezzo, int marca,int catalogBean) {
        this.id_prodotto = id_prodotto;
        this.quantita = quantita;
        this.nome = nome;
        this.descrizione_breve = descrizione_breve;
        this.descrizione_estesa = descrizione_estesa;
        this.pathImg1 = pathImg1;
        this.pathImg2 = pathImg2;
        this.pathImg3 = pathImg3;
        this.prezzo = prezzo;
        this.id_marca = marca;
        this.id_catalogo=catalogBean;
    }



    public int getPerc_sconto() {
        return perc_sconto;
    }

    public void setPerc_sconto(int perc_sconto) {
        this.perc_sconto = perc_sconto;
    }

    public int getId_prodotto() {
        return id_prodotto;
    }

    public void setId_prodotto(int id_prodotto) {
        this.id_prodotto = id_prodotto;
    }

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione_breve() {
        return descrizione_breve;
    }

    public void setDescrizione_breve(String descrizione_breve) {
        this.descrizione_breve = descrizione_breve;
    }

    public String getDescrizione_estesa() {
        return descrizione_estesa;
    }

    public void setDescrizione_estesa(String descrizione_estesa) {
        this.descrizione_estesa = descrizione_estesa;
    }

    public String getPathImg1() {
        return pathImg1;
    }

    public void setPathImg1(String pathImg1) {
        this.pathImg1 = pathImg1;
    }

    public String getPathImg2() {
        return pathImg2;
    }

    public void setPathImg2(String pathImg2) {
        this.pathImg2 = pathImg2;
    }

    public String getPathImg3() {
        return pathImg3;
    }

    public void setPathImg3(String pathImg3) {
        this.pathImg3 = pathImg3;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

    public int getId_marca() {
        return id_marca;
    }

    public void setId_marca(int id_marca) {
        this.id_marca = id_marca;
    }

    public int getId_catalogo() {
        return id_catalogo;
    }

    public void setId_catalogo(int id_catalogo) {
        this.id_catalogo = id_catalogo;
    }

    @Override
    public String toString() {
        return "ProductBean{" +
                "id_prodotto=" + id_prodotto +
                ", quantita=" + quantita +
                ", perc_sconto=" + perc_sconto +
                ", nome='" + nome + '\'' +
                ", descrizione_breve='" + descrizione_breve + '\'' +
                ", descrizione_estesa='" + descrizione_estesa + '\'' +
                ", pathImg1='" + pathImg1 + '\'' +
                ", pathImg2='" + pathImg2 + '\'' +
                ", pathImg3='" + pathImg3 + '\'' +
                ", prezzo=" + prezzo +
                ", id_marca=" + id_marca +
                ", id_catalogo=" + id_catalogo +
                '}';
    }


}
