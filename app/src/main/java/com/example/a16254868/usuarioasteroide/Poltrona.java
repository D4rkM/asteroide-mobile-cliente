package com.example.a16254868.usuarioasteroide;

/**
 * Created by 16254868 on 10/05/2018.
 */

public class Poltrona {

    private int imagemPoltrona;
    private int numeroPoltrona;
    private int quantidaPoltronas;

    public Poltrona(){

    }

    public Poltrona(int imagemPoltrona, int numeroPoltrona) {
        this.imagemPoltrona = imagemPoltrona;
        this.numeroPoltrona = numeroPoltrona;
    }

    public int getImagemPoltrona() {
        return imagemPoltrona;
    }

    public void setImagemPoltrona(int imagemPoltrona) {
        this.imagemPoltrona = imagemPoltrona;
    }

    public int getNumeroPoltrona() {
        return numeroPoltrona;
    }

    public void setNumeroPoltrona(int numeroPoltrona) {
        this.numeroPoltrona = numeroPoltrona;
    }
}
