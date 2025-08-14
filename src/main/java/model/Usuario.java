/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author berna
 */
public class Usuario {
    private String id;
    private String nome;
    private String email;
    private String telefone;
    private LocalDateTime dataCriacaoDaConta;
    private String senha;
    private boolean admin;
    private boolean autenticado;
    private List<Perfil> perfis;
    
    public Usuario(String email, String senha) {     
        
        if (email == null) {
            throw new IllegalArgumentException("Email precisa ser informado.");
        }
        
        if (senha == null) {
            throw new IllegalArgumentException("Senha precisa ser informada.");
        }
        
        this.email = email;
        this.senha = senha;
        this.admin = false;
        this.autenticado = false;
        this.perfis = new ArrayList<>();
        this.dataCriacaoDaConta = LocalDateTime.now();      
    } 
    
    public Usuario(String nome, String email, String telefone, String senha) {    
        
        if (nome == null) {
            throw new IllegalArgumentException("Nome precisa ser informado.");
        }
        
        if (email == null) {
            throw new IllegalArgumentException("Email precisa ser informado.");
        }
        
        if (senha == null) {
            throw new IllegalArgumentException("Senha precisa ser informada.");
        }
        
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;     
        this.senha = senha;
        this.admin = false;
        this.autenticado = false;
        this.perfis = new ArrayList<>();
        this.dataCriacaoDaConta = LocalDateTime.now();        
    } 
    
    
    public Usuario(String id, String nome, String email, String telefone, String senha) {    
        
        if (nome == null) {
            throw new IllegalArgumentException("Nome precisa ser informado.");
        }
        
        if (email == null) {
            throw new IllegalArgumentException("Email precisa ser informado.");
        }
        
        if (senha == null) {
            throw new IllegalArgumentException("Senha precisa ser informada.");
        }
        
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone; 
        this.senha = senha;
    } 
    
    public Usuario(String nome, String email, String telefone, String senha, boolean isAdmin) {    
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.senha = senha;
        this.admin = isAdmin;         
        this.autenticado = false;
        this.perfis = new ArrayList<>();
        this.dataCriacaoDaConta = LocalDateTime.now();
    } 

    public Usuario(String id, String nome, String email, String telefone, boolean isAdmin, LocalDateTime dataCriacaoDaConta) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.admin = isAdmin;
        this.dataCriacaoDaConta = dataCriacaoDaConta;
    }
    
    public Usuario(String id, String nome, String email, String senha, String telefone, LocalDateTime dataCraicaoDaConta, boolean isAdmin) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.telefone = telefone;
        this.dataCriacaoDaConta = dataCriacaoDaConta;
        this.admin = isAdmin;
    }
    
    public String getId() {
        return this.id;
    }
    
    public String getNome(){
        return this.nome;
    }
    
    public String getTelefone(){
        return this.telefone;
    }
    
    public String getEmail() {
        return this.email;
    }

    public String getSenha() {
        return this.senha;
    }
    
    public LocalDateTime getDataCriacao(){
        return this.dataCriacaoDaConta;
    }
    
    public List<Perfil> getPerfis(){
        return this.perfis;
    }
    
    public Optional<Perfil> getPerfilVendedor() {
        if (perfis==null){
            return Optional.empty();
        }
        for (Perfil perfil : perfis) {
            if (perfil.getId().startsWith("V")) {
                return Optional.of(perfil);    
            }
        }
        
        return Optional.empty();
    }
    
       public Optional<Perfil> getPerfilComprador() {
        if (perfis==null){
            return Optional.empty();
        }
        for (Perfil perfil : perfis) {
            if (perfil.getId().startsWith("C")) {
                return Optional.of(perfil);    
            }
        }
        
        return Optional.empty();
    }
       
    public void setId(Integer id){
        this.id=String.valueOf(id);
    }
    
    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
    
    public void setAutenticado(boolean autenticado) {
        this.autenticado = autenticado;
    }
    
    public void addPerfil(Perfil perfil) {
        this.perfis.add(perfil);
    }
    
    public boolean isAutenticado() {
        return this.autenticado;
    }
    
    public boolean isAdmin() {
        return this.admin;
    }
    
    public void copy(Usuario usuario){
        this.id = usuario.getId();
        this.nome = usuario.getNome();
        this.telefone = usuario.getTelefone();
        this.email=usuario.getEmail();
        this.admin = usuario.isAdmin();
        this.autenticado=usuario.isAutenticado();
        this.perfis=usuario.getPerfis();
        this.dataCriacaoDaConta=usuario.getDataCriacao();
    }
}
