package models;

import java.io.Serializable;

/**
 * Created by 16254868 on 19/04/2018.
 */

public class Usuario implements Serializable{

    int id;
    String nome;
    String usuario;
    String cpf;
    String email;
    String sexo;
    String datanasc;
    String telefone;
    String celular;
    String rg;

    public Usuario(){

    }

    public Usuario(int id, String nome, String usuario, String cpf,
                   String email, String datanasc, String sexo, String telefone, String celular,
                   String rg){

        this.id = id;
        this.nome = nome;
        this.usuario = usuario;
        this.cpf = cpf;
        this.email = email;
        this.datanasc = datanasc;
        this.sexo = sexo;
        this.telefone = telefone;
        this.celular = celular;
        this.rg = rg;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getDatanasc() {
        return datanasc;
    }

    public void setDatanasc(String datanasc) {
        this.datanasc = datanasc;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }
}
