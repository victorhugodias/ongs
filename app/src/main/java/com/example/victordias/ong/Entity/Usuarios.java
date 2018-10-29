package com.example.victordias.ong.Entity;

import com.example.victordias.ong.DAO.ConfiguracaoFirebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class Usuarios {

    private String id;
    private String razaoSocial;
    private String nomeFantasia;
    private String cnpj;
    private String telefone;
    private String endereco;
    private String bairro;
    private String municipio;
    private String uf;
    private String cep;
    private String email;
    private String senha;
    private String representanteLegal;

    public Usuarios() {

    }

    public void salvar(){
        DatabaseReference referenciaFirebase = ConfiguracaoFirebase.getFirebase();
        referenciaFirebase.child("ongs-81499").child(String.valueOf(getId())).setValue(this);
    }

    @Exclude
    public Map<String, Object> toMap(){
        HashMap<String, Object> hasMapUsuario = new HashMap<>();
        hasMapUsuario.put("id",getId());
        hasMapUsuario.put("razaoSocial",getRazaoSocial());
        hasMapUsuario.put("nomeFantasia",getNomeFantasia());
        hasMapUsuario.put("cnpj",getCnpj());
        hasMapUsuario.put("telefone",getTelefone());
        hasMapUsuario.put("endereco",getEndereco());
        hasMapUsuario.put("bairro",getBairro());
        hasMapUsuario.put("municipio",getMunicipio());
        hasMapUsuario.put("uf",getUf());
        hasMapUsuario.put("cep",getCep());
        hasMapUsuario.put("email",getEmail());
        hasMapUsuario.put("senha",getSenha());
        hasMapUsuario.put("representanteLegal",getRepresentanteLegal());
        return hasMapUsuario;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getRepresentanteLegal() {
        return representanteLegal;
    }

    public void setRepresentanteLegal(String representanteLegal) {
        this.representanteLegal = representanteLegal;
    }
}
