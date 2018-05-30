package models;

/**
 * Created by 16254868 on 10/04/2018.
 */

public class Viagem {

    private int id;
    private String valor;
    private String horaPartida;
    private String horaChegada;
    private String dataPartida;
    private String dataChegada;
    private String imagem;
    private String km;
    private String enderecoChegada;
    private String enderecoSaida;
    private String sugestao;
    private String origem;
    private String destino;
    private String tipo_classe;
    private String poltronas;

    public Viagem(){}

    public Viagem(int id, String valor, String origem, String destino, String horaPartida, String horaChegada, String dataPartida, String dataChegada, String km,
                  String enderecoChegada, String enderecoSaida,
                  String imagem, String tipo_classe, String poltronas) {
        this.id = id;
        this.valor = valor;
        this.origem = origem;
        this.destino = destino;
        this.horaPartida = horaPartida;
        this.horaChegada = horaChegada;
        this.dataPartida = dataPartida;
        this.dataChegada = dataChegada;
        this.km = km;
        this.enderecoChegada = enderecoChegada;
        this.enderecoSaida = enderecoSaida;
        this.imagem = imagem;
        this.tipo_classe = tipo_classe;
        this.poltronas = poltronas;
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

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getTipo_classe() {
        return tipo_classe;
    }

    public void setTipo_classe(String tipo_classe) {
        this.tipo_classe = tipo_classe;
    }

    public String getPoltronas() {
        return poltronas;
    }

    public void setPoltronas(String poltronas) {
        this.poltronas = poltronas;
    }

    public String getEnderecoChegada() {
        return enderecoChegada;
    }

    public void setEnderecoChegada(String enderecoChegada) {
        this.enderecoChegada = enderecoChegada;
    }

    public String getEnderecoSaida() {
        return enderecoSaida;
    }

    public void setEnderecoSaida(String enderecoSaida) {
        this.enderecoSaida = enderecoSaida;
    }

    public String getDataPartida() {
        return dataPartida;
    }

    public void setDataPartida(String dataPartida) {
        this.dataPartida = dataPartida;
    }

    public String getDataChegada() {
        return dataChegada;
    }

    public void setDataChegada(String dataChegada) {
        this.dataChegada = dataChegada;
    }


}
