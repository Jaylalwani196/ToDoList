package com.example.to_doattack;

public class Mydoes {
    String titledoes ;
    String datedoes ;
    String descdoes;
    String id;



    public Mydoes() {
    }

    public Mydoes(String titledoes, String datedoes, String descdoes, String keydoes , String id) {
        this.titledoes = titledoes;
        this.datedoes = datedoes;
        this.descdoes = descdoes;
        this.id = id;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitledoes() {
        return titledoes;
    }

    public void setTitledoes(String titledoes) {
        this.titledoes = titledoes;
    }

    public String getDatedoes() {
        return datedoes;
    }

    public void setDatedoes(String datedoes) {
        this.datedoes = datedoes;
    }

    public String getDescdoes() {
        return descdoes;
    }

    public void setDescdoes(String descdoes) {
        this.descdoes = descdoes;
    }
}
