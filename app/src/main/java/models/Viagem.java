package models;

/**
 * Created by 16254868 on 10/04/2018.
 */

public class Viagem {

    private int id;
    private String valor;
    private String data;
    private String horaPartida;
    private String horaChegada;
    private String imagem;
    private String descricao;
    private String km;
    private String sugestao;
    private String nome;

    public Viagem(){}

    public Viagem(int id, String valor, String nome, String data, String horaPartida, String horaChegada, String descricao,
                  String imagem) {
        this.id = id;
        this.valor = valor;
        this.nome = nome;
        this.data = data;
        this.horaPartida = horaPartida;
        this.horaChegada = horaChegada;
        this.descricao = descricao;
        this.imagem = imagem;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHoraPartida() {
        return horaPartida;
    }

    public void setHoraPartida(String horaPartida) {
        this.horaPartida = horaPartida;
    }

    public String getHoraChegada() {
        return horaChegada;
    }

    public void setHoraChegada(String horaChegada) {
        this.horaChegada = horaChegada;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getKm() {
        return km;
    }

    public void setKm(String km) {
        this.km = km;
    }

    public String getSugestao() {
        return sugestao;
    }

    public void setSugestao(String sugestao) {
        this.sugestao = sugestao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
