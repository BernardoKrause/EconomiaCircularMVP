/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author berna
 */
public class Denuncia {
    private Integer id;
    private String idC;
    private String descricao;
    private String status;
    private Comprador comprador;
    private Vendedor vendedor;
    private Transacao transacao;
    
    public Denuncia (Integer id, String idC, String descricao, String status, Comprador comprador, Vendedor vendedor, Transacao transacao) {
        this.id = id;
        this.idC = idC;
        this.descricao = descricao;
        this.status = status;
        this.comprador = comprador;
        this.vendedor = vendedor;
        this.transacao = transacao;
    }
    
    public Denuncia (String idC, String descricao, String status, Comprador comprador, Vendedor vendedor) {
        this.idC = idC;
        this.descricao = descricao;
        this.status = status;
        this.comprador = comprador;
        this.vendedor = vendedor;
    }
    
    public Integer getId() {
        return this.id;
    }
    
    public String getIdC() {
        return this.idC;
    }
    
    public String getDescricao() {
        return this.descricao;
    }
    
    public String getStatus() {
        return this.status;
    }
    
    public Comprador getComprador() {
        return this.comprador;
    }
    
    public Vendedor getVendedor() {
        return this.vendedor;
    }

    public Transacao getTransacao() {
        return transacao;
    }
}
