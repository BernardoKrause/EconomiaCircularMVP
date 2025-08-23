/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author caiof
 */

public class Material {
    private Integer id;
    private String tipo;
    private Double fatorEmissao;
    private Double percentualItem;
    
    public Material(String tipo, Double fator, Double percentual){
        this.tipo = tipo;
        this.fatorEmissao = fator;
        this.percentualItem = percentual;
    }
    
    public Material(Integer id, String tipo, Double fator, Double percentual){
        this.id = id;
        this.tipo = tipo;
        this.fatorEmissao = fator;
        this.percentualItem = percentual;
    }
    
    public Integer getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }

    public Double getFatorEmissao() {
        return fatorEmissao;
    }

    public Double getPercentualItem() {
        return percentualItem;
    }

    public void setPercentualItem(Double percentualItem) {
        this.percentualItem = percentualItem;
    }
}
