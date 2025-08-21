/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author berna
 */
public abstract class Perfil {
    protected Integer id;
    protected String sistemId;
    protected Usuario usuario;
    protected Reputacao reputacao;

    public Perfil(Integer id) {
        this.id = id;
    }
    
    public Perfil(Integer id, String sistemId) {
        this.id = id;
        this.sistemId = sistemId;
    }
    
    public Perfil(Integer id, String sistemId, Reputacao reputacao) {
        this.id = id;
        this.sistemId = sistemId;
        this.reputacao = reputacao;
    }
    
    public Integer getId() {
        return this.id;
    }
    
    public String getSistemId() {
        return this.sistemId;
    }
    
    public Reputacao getReputacao() {
        return this.reputacao;
    }
    
    public Usuario getUsuario(){
        return this.usuario;
    }
    
    public void setUsuario(Usuario usuario){
        this.usuario=usuario;
    }
    
    public void setReputacao(Reputacao reputacao){
        this.reputacao=reputacao;
    }

    public boolean isVendedor() {
        return usuario.getPerfilVendedor().get().equals(this);
    }
    
    public boolean isComprador() {
        return usuario.getPerfilVendedor().get().equals(this);
    }
    
    abstract void setSystemId(int id);
}
