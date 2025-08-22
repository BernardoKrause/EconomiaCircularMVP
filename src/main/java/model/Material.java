/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author caiof
 */

// implementar o dao de material, o percentual item tem que ficar na tabela item_materiais
public class Material {
    private String tipo;
    private Double fatorEmissao;
    private Double percentualItem;
    
    public Material(String tipo, Double fator, Double percentual){
        this.tipo=tipo;
        this.fatorEmissao=fator;
        this.percentualItem=percentual;
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
