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
    protected String id;
    protected Double beneficioClimatico;
    protected Usuario usuario;
    protected Reputacao reputacao;
    
    public Perfil(String id) {
        this.id = id;
    }
    
    public String getId() {
        return this.id;
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
