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
    protected String idC;
    protected Double beneficioClimatico;
    protected Usuario usuario;
    protected Reputacao reputacao;
    
    public Perfil(Integer id) {
        this.id = id;
    }
    
    public Perfil(String idC) {
        this.idC = idC;
    }
    
    public Perfil(Integer id, String idC) {
        this.id = id;
        this.idC = idC;
    }
    
    public Perfil(Integer id, String idC, Reputacao reputacao) {
        this.id = id;
        this.idC = idC;
        this.reputacao = reputacao;
    }
    
    public Integer getId() {
        return this.id;
    }
    
    public String getIdC() {
        return this.idC;
    }
    
    public Double getBeneficioClimatico() {
        return this.beneficioClimatico;
    }
    
    public Reputacao getReputacao() {
        return this.reputacao;
    }
    
    public void setBeneficioClimatico(Double beneficioClimatico) {
        this.beneficioClimatico = beneficioClimatico;
    }
    
    public Usuario getUsuario(){
        return this.usuario;
    }
    
    public void setUsuario(Usuario usuario){
        this.usuario=usuario;
    }
    
}
