package model;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author berna
 */
public class SolicitacaoPerfil {
    
    private Integer id;
    private Integer idUsuario;
    private Usuario usuario;
    private String status;
    
    public SolicitacaoPerfil(Integer id, String status, Integer idUsuario) {
        this.id = id;
        this.status = status;
        this.idUsuario = idUsuario;
    }
    
    public SolicitacaoPerfil(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }
    
    public SolicitacaoPerfil(String status) {
        this.status = status;
    }

    public SolicitacaoPerfil(String a, Integer idUsuario) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public Integer getId() {
        return this.id;
    }
    
    public String getStatus() {
        return this.status;
    }
    
    public Integer getIdUsuario() {
        return this.idUsuario;
    }
    
    public void setStatus(String status){
        this.status=status;
    }
}
