/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.teste;

import dao.ItemDAOSQLite;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import model.Item;
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
    public Optional<List<Item>> BuscarPorVendedor(Vendedor vendedor){
        List<Item> lista=new ArrayList<>();
        for(Item item : itensPublicados){
            if(item.getVendedor()==vendedor){
                lista.add(item);
            }
        }
        return Optional.of(lista);
    }
    
    @Override
    public Optional<Item> BuscarPorId(Integer id) throws SQLException{
        for(Item item : itensPublicados){
            if(item.getId() == id){
                return Optional.of(item);
            }
        }
        return Optional.empty();
    }
    
    @Override
    public Integer getQuantidadeItens() throws SQLException {
        return itensPublicados.size();
    }
    
    @Override
    public void salvarItem(Item item) throws SQLException{
        itensPublicados.add(item);
    }

    @Override
    public Optional<List<String>> getTiposItem() {
        List<String> tiposDefeito = new ArrayList<>();
        tiposDefeito.add("vestuario");
        tiposDefeito.add("calcado");
        tiposDefeito.add("bolsas e mochilas");
        tiposDefeito.add("bijuterias e acessorios");
        
        return Optional.of(tiposDefeito);
    }
}

