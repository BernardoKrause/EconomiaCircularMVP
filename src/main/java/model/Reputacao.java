/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author berna
 */
public class Reputacao {
    private Integer id;
    private Double estrelas;
    private Double beneficio;
    private String nivel;
    
    public Reputacao(Double estrelas, Double beneficio, String nivel) {
        this.estrelas = estrelas;
        this.beneficio = beneficio;
        this.nivel = nivel;
    }
    
    public Integer getId() {
        return this.id;
    }
    
    public void setIdReputacao(Integer id) {
        this.id = id;
    }
}
