/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package strategy.sistemaResputacao;

import factory.repository.SeletorRepositoryFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import model.Conduta;
import model.Perfil;
import strategy.sistemaResputacao.insignias.InsigniaVendedorPorItem;

/**
 *
 * @author caiof
 */
public class MetodoInsignia implements IMetodoReputacao{    
    protected String nomeConduta;
    
    public MetodoInsignia(){
    }
    
    @Override
    public Optional<Conduta> aplicarReputacao(Perfil perfil){       
        List<MetodoInsignia> tiposInsignias = new ArrayList<>();
        tiposInsignias.add(new InsigniaVendedorPorItem());
        for(MetodoInsignia regra : tiposInsignias){
            for(Integer cont=0; cont<regra.getRepeticoes(); cont++){
                if(regra.seAplica(perfil)){
                    return Optional.of(new Conduta(nomeConduta, getTipoConduta(), "Vendedor", 0.2));
                }
            }
        }
        return Optional.empty();
    }
    
    @Override
    public String getTipoConduta(){
        return("Insignia");
    }
    
    @Override
    public boolean seAplica(Perfil perfil){
        throw new RuntimeException("metodo não pode ser executado!");
    }
    
    @Override
    public Integer getRepeticoes(){
        throw new RuntimeException("metodo não pode ser executado!");
    }
}
