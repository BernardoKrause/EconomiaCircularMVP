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
    
    public Conduta(Integer id, String tipo, String descricao, String tipoPerfil, Double valorEstrelas) {
        this.id = id;
        this.tipo = tipo;
        this.descricao = descricao;
        this.tipoPerfil = tipoPerfil;
        this.valorEstrela = valorEstrelas;
    }

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

    @Override
    public String toString() {
        return (tipo + '\'' +
                ", descricao='" + descricao + '\'' +
                ", estrelas ganhas=" + valorEstrela);
    }
}
