/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import model.Item;
import model.Material;

/**
 *
 * @author caiof
 */
public class CalcularGPWService {
    
    public void calcularGPW(Item item){
        calcularGPWBase(item);
        calcularGPWEvitado(item);
    }

    public void calcularGPWBase(Item item) {
        Double somaGPW = 0.0;
        for(Material material : item.getComposicao()){
            somaGPW+=getGPWBaseMaterial(item.getPeso(),material);
        }
        item.setGPWBase(somaGPW);
    }

    public void calcularGPWEvitado(Item item){
        Double gwpReuso = 0.05 * item.getGPWBase();
        Double gwpEvitado = item.getGPWBase() - gwpReuso;

        item.setGPWEvitado(gwpEvitado);
    }

    public Double getGPWBaseMaterial(Double peso,Material material){
        return (peso*material.getPercentualItem()*material.getFatorEmissao());
    }
}
