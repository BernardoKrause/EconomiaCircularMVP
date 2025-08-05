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
    
    public String getId() {
        return this.id;
    }
    
    public Double getBeneficioClimatico() {
        return this.beneficioClimatico;
    }
    
    public void setBeneficioClimatico(Double beneficioClimatico) {
        this.beneficioClimatico = beneficioClimatico;
    }
    
    // falta o get reputacao
}
