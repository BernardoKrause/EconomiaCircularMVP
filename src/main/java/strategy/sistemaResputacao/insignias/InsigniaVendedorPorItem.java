/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package strategy.sistemaResputacao.insignias;

import factory.repository.SeletorRepositoryFactory;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Conduta;
import model.Perfil;
import model.Vendedor;
import repository.IItemRepository;
import strategy.sistemaResputacao.MetodoInsignia;

/**
 *
 * @author caiof
 */
public class InsigniaVendedorPorItem extends MetodoInsignia{
    private Map<String, Integer> regraConduta;
    private IItemRepository itemRepo;
    private Map<String, Integer> tipoConduta;
    
    public InsigniaVendedorPorItem(){
        super();
        itemRepo = SeletorRepositoryFactory.obterInstancia().criarItemRepository();
        regraConduta = new HashMap<>();
        regraConduta.put("Primeiro Anuncio", 1);
    }

    @Override
    public boolean seAplica(Perfil perfil) {
        List<Conduta> condutas = perfil.getReputacao().getCondutas();
        boolean aplica=false;
        for(String tipo : regraConduta.keySet()){
            for(Conduta condutaAnalisada : condutas){
                if(condutaAnalisada.getDescricao().equalsIgnoreCase(tipo) && itemRepo.BuscarPorVendedor((Vendedor) perfil).get().size() > tipoConduta.get(tipo)){
                    aplica=false;
                    break;
                }
            }
            nomeConduta=tipo;
            aplica=true;
        }
        return aplica;
    }
    
    @Override
    public Integer getRepeticoes(){
        return regraConduta.size();
    }
    
}
