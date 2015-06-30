package com.com.unisc.unisceventclient.classes;

/**
 * Created by FAGNER on 24/06/2015.
 */
public class PessoaMO {


    //[{"cod_pessoa":"28","nome_pessoa":"a","email":"a","senha":"a","scr_foto":null,"matricula":"4"}]

    private long cod_pessoa;
    private String nome_pessoa;
    private String email;
    private long senha;
    private long matricula;

    private LoginMO Login;


    public long getMatricula() {
        return matricula;
    }

    public void setMatricula(long matricula) {
        this.matricula = matricula;
    }

    public long getSenha() {
        return senha;
    }

    public void setSenha(long senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome_pessoa() {
        return nome_pessoa;
    }

    public void setNome_pessoa(String nome_pessoa) {
        this.nome_pessoa = nome_pessoa;
    }

    public long getCod_pessoa() {
        return cod_pessoa;
    }

    public void setCod_pessoa(long cod_pessoa) {
        this.cod_pessoa = cod_pessoa;
    }

    public LoginMO getLogin() {
        return Login;
    }

    public void setLogin(LoginMO login) {
        Login = login;
    }
}
