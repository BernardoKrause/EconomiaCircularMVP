/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.teste;

import dao.sqlite.ItemDAOSQLite;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import model.Item;
import model.Material;
import model.Vendedor;
import repository.IItemRepository;

/**
 *
 * @author caiof
 */
public class ItemRepositoryTeste implements IItemRepository{
    private List<Item> itensPublicados;
    private static ItemRepositoryTeste instancia;
    
    private ItemRepositoryTeste() {
        itensPublicados=new ArrayList<>();
    }
    
    public static ItemRepositoryTeste getInstancia(){
        if(instancia==null){
            instancia = new ItemRepositoryTeste();
        }
        return instancia;
    }
    
    @Override
    public List<Item> buscarTodos(){
        return itensPublicados;
    }

    public List<Item> buscarPorVendedor(Vendedor vendedor){
        List<Item> lista=new ArrayList<>();
        for(Item item : itensPublicados){
            if(item.getVendedor()==vendedor){
                lista.add(item);
            }
        }
        return lista;
    }
    
    @Override
    public Optional<Item> buscarPorId(Integer id) throws SQLException{
        for(Item item : itensPublicados){
            if(item.getId() == id){
                return Optional.of(item);
            }
        }
        return Optional.empty();
    }
    
    @Override
    public Integer buscarQuantidadeItens() throws SQLException {
        return itensPublicados.size();
    }
    
    @Override
    public void adicionarItem(Item item) throws SQLException{
        itensPublicados.add(item);
    }

    @Override
    public List<String> buscarTipos() {
        List<String> tiposDefeito = new ArrayList<>();
        tiposDefeito.add("vestuario");
        tiposDefeito.add("calcado");
        tiposDefeito.add("bolsas e mochilas");
        tiposDefeito.add("bijuterias e acessorios");
        
        return tiposDefeito;
    }

    @Override
    public List<Material> buscarMateriais() {
        List<Material> lista = new ArrayList<>();

        lista.add(new Material("Algodão", 10.0, 10.0));
        lista.add(new Material("Poliéster", 10.0, 10.0));
        lista.add(new Material("Couro", 10.0, 10.0));
        lista.add(new Material("Metal", 10.0, 10.0));
        lista.add(new Material("Plástico", 10.0, 10.0));
        lista.add(new Material("Outros", 10.0, 10.0));

        return lista;
    }

    @Override
    public Double buscarFatorEmissaoMaterial(String nomeMaterial) {
        Double fator = 4.0;
        
        switch (nomeMaterial){
            case "Algodão" -> fator=5.2;
            case "Poliéster" -> fator=9.5;
            case "Couro" -> fator=14.8;
            case "Metal" -> fator=8.6;
            case "Plástico" -> fator=3.1;
        }
        
        return fator;
    }

    @Override
    public void atualizarItem(Item item) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void excluirItem(Item item) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}

