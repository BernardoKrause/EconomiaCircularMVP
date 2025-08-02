/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author berna
 */
public class Usuario {
    private String email;
    private String senha;
    private boolean admin;
    private boolean autenticado;
    
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
    } 
    
    public String getEmail() {
        return this.email;
    }
    
    public String getSenha() {
        return this.senha;
    }
    
    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
    
    public void setAutenticado(boolean autenticado) {
        this.autenticado = autenticado;
    }
    
    public boolean isAutenticado() {
        return this.autenticado;
    }
    
    public boolean isAdmin() {
        return this.admin;
    }
}
