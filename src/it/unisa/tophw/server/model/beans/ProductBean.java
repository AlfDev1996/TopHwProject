package it.unisa.tophw.server.model.beans;

import java.util.ArrayList;

public class ProductBean {

    private int id_prodotto, quantita, perc_sconto;
    private String nome, descrizione_breve, descrizione_estesa, pathImg1, pathImg2, pathImg3;
    private double prezzo ;
    private BrandBean marca;

    public ProductBean() {
        id_prodotto=0;
        nome=descrizione_breve=descrizione_estesa=pathImg1=pathImg2=pathImg3="";
        prezzo=0.0;
        marca=new BrandBean();
        quantita=0;
        perc_sconto=0;
    }

    public ProductBean(int id_prodotto, int quantita, String nome, String descrizione_breve, String descrizione_estesa, String pathImg1, String pathImg2, String pathImg3, double prezzo, BrandBean marca) {
        this.id_prodotto = id_prodotto;
        this.quantita = quantita;
        this.nome = nome;
        this.descrizione_breve = descrizione_breve;
        this.descrizione_estesa = descrizione_estesa;
        this.pathImg1 = pathImg1;
        this.pathImg2 = pathImg2;
        this.pathImg3 = pathImg3;
        this.prezzo = prezzo;
        this.marca = marca;
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

    public BrandBean getMarca() {
        return marca;
    }

    public void setMarca(BrandBean marca) {
        this.marca = marca;
    }

    @Override
    public String toString() {
        return "ProdottoBean{" +
                "id_prodotto=" + id_prodotto +
                ", quantita=" + quantita +
                ", nome='" + nome + '\'' +
                ", descrizione_breve='" + descrizione_breve + '\'' +
                ", descrizione_estesa='" + descrizione_estesa + '\'' +
                ", pathImg1='" + pathImg1 + '\'' +
                ", pathImg2='" + pathImg2 + '\'' +
                ", pathImg3='" + pathImg3 + '\'' +
                ", prezzo=" + prezzo +
                ", marca=" + marca +
                '}';
    }
}
