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
    private String idC;
    private String descricao;
    private String status;
    private Comprador comprador;
    private Vendedor vendedor;
    
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
}
