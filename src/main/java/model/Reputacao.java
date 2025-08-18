/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author berna
 */
public class Reputacao {
    private Integer id;
    private Double estrelas;
    private Double beneficio;
    private String nivel;
    private List<Conduta> condutas;
    
    public Reputacao(Double estrelas, Double beneficio, String nivel) {
        this.estrelas = estrelas;
        this.beneficio = beneficio;
        this.nivel = nivel;
        this.condutas = new ArrayList<>();
    }

    public Reputacao(Integer id){
        this.id = id;
        estrelas = 0.0;
        beneficio = 0.0;
        nivel = "PRATA";
        condutas = new ArrayList<>();
    }

    public Reputacao(double aDouble, double aDouble0, String string, List<Conduta> condutas) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public Integer getId() {
        return this.id;
    }

    public Double getBeneficio() {
        return beneficio;
    }

    public Double getEstrelas() {
        return estrelas;
    }

    public String getNivel() {
        return nivel;
    }

    public List<Conduta> getCondutas() {
        return condutas;
    }

    public void setIdReputacao(Integer id) {
        this.id = id;
    }

    public void setBeneficio(Double beneficio) {
        this.beneficio = beneficio;
    }

    public void setEstrelas(Double estrelas) {
        this.estrelas = estrelas;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }
    
    public void setCondutas(List<Conduta> condutas){
        this.condutas=condutas;
    }

    public void addConduta(Conduta conduta) {
        condutas.add(conduta);
    }

    public void copy(Reputacao reputacao) {
        this.estrelas = reputacao.estrelas;
        this.beneficio = reputacao.beneficio;
        this.nivel = reputacao.nivel;
    }
}
