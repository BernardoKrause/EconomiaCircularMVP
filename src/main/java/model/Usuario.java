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
        
        this.perfis = new ArrayList<>();
        this.email = email;
        this.senha = senha;
        this.admin = false;
        this.autenticado = false;                
    } 
    
    public Usuario(String nome, String email, String senha) {     
        
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
        this.senha = senha;
        this.admin = false;
        this.autenticado = false;                
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
    } 
    
    public String getId() {
        return this.id;
    }
    
    public String getEmail() {
        return this.email;
    }
    
    public String getSenha() {
        return this.senha;
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
}
