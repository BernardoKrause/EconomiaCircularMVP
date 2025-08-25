/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import factory.repository.SeletorRepositoryFactory;
import java.sql.SQLException;
import model.Comprador;
import model.Oferta;
import repository.IOfertaRepository;
import repository.IPerfilRepository;

/**
 *
 * @author caiof
 */
public class OfertaService {
    private IOfertaRepository ofertaRepository;
    private IPerfilRepository compradorRepository;
    
    public OfertaService(){
       ofertaRepository = SeletorRepositoryFactory.obterInstancia().criarOfertaRepository();
       compradorRepository = SeletorRepositoryFactory.obterInstancia().criarPerfilCompradorRepository();
    }
    
    public void criar(Oferta oferta) throws SQLException{
        Comprador comprador = oferta.getComprador();
        comprador.addOferta(oferta);
        ofertaRepository.adicionarOferta(oferta);
        compradorRepository.atualizarPerfil(comprador);
    }
}
