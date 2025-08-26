/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import factory.repository.RepositoryFactoryBD;
import factory.repository.SeletorRepositoryFactory;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Comprador;
import model.Item;
import model.Oferta;
import repository.IItemRepository;
import repository.IOfertaRepository;
import repository.IPerfilRepository;

/**
 *
 * @author caiof
 */
public class OfertaService {
    private IOfertaRepository ofertaRepository;
    private IPerfilRepository compradorRepository;
    private IItemRepository itemRepository;
    
    public OfertaService(){
       ofertaRepository = SeletorRepositoryFactory.obterInstancia().criarOfertaRepository();
       compradorRepository = SeletorRepositoryFactory.obterInstancia().criarPerfilCompradorRepository();
       
    }
    
    public void criar(Oferta oferta) throws SQLException{
        Comprador comprador = oferta.getComprador();
        comprador.addOferta(oferta);
        Item item= oferta.getItem();
        item.addOferta(oferta);
        itemRepository.atualizarItem(item);
        ofertaRepository.adicionarOferta(oferta);
        compradorRepository.atualizarPerfil(comprador);
    }
    
    public List<Oferta> getOfertasPorItem(Item item) throws SQLException {
        try {
            return ofertaRepository.buscarPorItem(item.getId());
        } catch (SQLException ex) {
            Logger.getLogger(RepositoryFactoryBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null; 
    }
}
