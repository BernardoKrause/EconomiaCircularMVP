/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 *
 * @author caiof
 */
public class Oferta {
    private Integer id;
    private Item item;
    private Comprador comprador;
    private Double valorOferta;
    private LocalDateTime dataOferta;
    private String status;
    
    public Oferta(Item item, Comprador comprador, Double valorOferta){
        this.item=item;
        this.comprador=comprador;
        this.valorOferta=valorOferta;
        this.dataOferta=LocalDateTime.now();
        this.status="W";
    }

    public Oferta(Integer id, Timestamp dataOferta, Double valorOferta, String status, Item item, Comprador comprador) {
        this.id = id;
        this.item=item;
        this.comprador=comprador;
        this.valorOferta=valorOferta;
        this.dataOferta=dataOferta.toLocalDateTime();
        this.status=status;
    }
    
    public Integer getId(){
        return id;
    }
    
    public Item getItem(){
        return item;
    }
    
    public Comprador getComprador(){
        return comprador;
    }
    
    public Double getValor(){
        return valorOferta;
    }
    
    public LocalDateTime getData(){
        return dataOferta;
    }
    
    public String getStatus(){
        return status;
    }
    
    public void setAceito(){
        this.status="A";
    }
    
    public void setNegado(){
        this.status="D";
    }
    
    public void setID(Integer id){
        this.id=id;
    }

}
