/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.teste;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Oferta;
import repository.IOfertaRepository;

/**
 *
 * @author caiof
 */
public class OfertaRepositoryTeste implements IOfertaRepository{
    private static OfertaRepositoryTeste instancia;
    private List<Oferta> ofertasPublicadas;
    
    private OfertaRepositoryTeste(){
        ofertasPublicadas=new ArrayList<>();
    }
    
    public static OfertaRepositoryTeste getInstancia(){
        if(instancia==null){
            instancia = new OfertaRepositoryTeste();
        }
        return instancia;
    }

    @Override
    public void adicionarOferta(Oferta oferta) {
        oferta.setID(ofertasPublicadas.size());
        this.ofertasPublicadas.add(oferta);
    }

    @Override
    public void atualizarOferta(Oferta oferta) {
        for(Oferta o : ofertasPublicadas){
            if(o.getId().equals(oferta.getId())){
                o=oferta;
            }
        }
    }

    @Override
    public List<Oferta> buscarTodas() {
        return ofertasPublicadas;
    }

    @Override
    public List<Oferta> buscarPorItem(Integer id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
}
