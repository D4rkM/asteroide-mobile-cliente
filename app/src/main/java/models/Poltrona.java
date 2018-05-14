package models;

/**
 * Created by 16254868 on 10/05/2018.
 */

public class Poltrona {

    private int[] numeroPoltrona;
    private int[] cor;

    public Poltrona(){
    }

    public Poltrona(int[] numeroPoltrona, int[] cor) {
        this.cor = cor;
        this.numeroPoltrona = numeroPoltrona;
    }

    public int[] getCor() {
        return cor;
    }

    public void setCor(int[] cor) {
        this.cor = cor;
    }

    public int[] getNumeroPoltrona() {
        return numeroPoltrona;
    }

    public void setNumeroPoltrona(int[] numeroPoltrona) {
        this.numeroPoltrona = numeroPoltrona;
    }
}
