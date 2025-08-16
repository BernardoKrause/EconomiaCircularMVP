/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author caiof
 */
public class Conduta {
    private Integer id;
    private String descricao;
    private String tipo;
    private String tipoPerfil;
    private Double valorEstrela;

    public String getDescricao() {
        return descricao;
    }

    public String getTipoPerfil() {
        return tipoPerfil;
    }

    public Double getValorEstrela() {
        return valorEstrela;
    }

    public String getTipo() {
        return tipo;
    }
}
