package com.com.unisc.unisceventclient.classes;

/**
 * Created by FAGNER on 24/06/2015.
 */
public class PessoaMO {

    private long ID;
    private String Nome;
    private String Email;
    private long Matricula;

    private LoginMO Login;


    public long getMatricula() {
        return Matricula;
    }

    public void setMatricula(long matricula) {
        Matricula = matricula;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public LoginMO getLogin() {
        return Login;
    }

    public void setLogin(LoginMO login) {
        Login = login;
    }
}
