package it.unisa.tophw.server.model.beans;

public class BrandBean {

    private int id_marca;
    private String nome;

    public BrandBean() {
        this.nome="";
        this.id_marca=0;
    }


    public BrandBean(int id_marca, String nome) {
        this.id_marca = id_marca;
        this.nome = nome;
    }

    public int getIdMarca() {
        return id_marca;
    }


    public void setIdMarca(int id_marca) {
        this.id_marca = id_marca;
    }


    public String getNome() {
        return nome;
    }


    public void setNome(String nome) {
        this.nome = nome;
    }





    @Override
    public String toString() {
        return "marca : [id_marca=" + id_marca + ", nome=" + nome + "]";
    }


}
