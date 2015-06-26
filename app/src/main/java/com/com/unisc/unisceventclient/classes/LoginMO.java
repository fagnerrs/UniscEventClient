package com.com.unisc.unisceventclient.classes;

/**
 * Created by FAGNER on 24/06/2015.
 */
public class LoginMO {

    private long ID;
    private String NomeUsuario;
    private String Senha;
    private PessoaMO Pessoa;

    public String getSenha() {
        return Senha;
    }

    public void setSenha(String senha) {
        Senha = senha;
    }

    public String getNomeUsuario() {
        return NomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        NomeUsuario = nomeUsuario;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public PessoaMO getPessoa() {
        return Pessoa;
    }

    public void setPessoa(PessoaMO pessoa) {
        Pessoa = pessoa;
    }
}
