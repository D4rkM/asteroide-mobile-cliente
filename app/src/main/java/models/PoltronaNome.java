package models;

/**
 * Created by 16254868 on 21/05/2018.
 */

public class PoltronaNome {
    private String nome;
    private int poltrona;

    public PoltronaNome(){

    }

    public PoltronaNome(String nome, int poltrona) {
        this.nome = nome;
        this.poltrona = poltrona;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getPoltrona() {
        return poltrona;
    }

    public void setPoltrona(int poltrona) {
        this.poltrona = poltrona;
    }
}
