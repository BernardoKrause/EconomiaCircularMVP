/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package strategy.sistemaResputacao;

import factory.repository.SeletorRepositoryFactory;
import java.util.ArrayList;
import java.util.List;
import model.Conduta;
import model.Perfil;
import strategy.sistemaResputacao.insignias.InsigniaVendedorPorItem;

/**
 *
 * @author caiof
 */
public abstract class MetodoInsignia implements IMetodoReputacao{    
    protected Conduta conduta;
    private List<MetodoInsignia> tiposInsignias;
    protected String nomeConduta;
    
    public MetodoInsignia(){
        tiposInsignias = new ArrayList<>();
        tiposInsignias.add(new InsigniaVendedorPorItem());
    }
    
    @Override
    public void aplicarReputacao(Perfil perfil){
        for(MetodoInsignia metodo : tiposInsignias){
            for(Integer cont=0; cont<metodo.getRepeticoes(); cont++){
                if(metodo.seAplica(perfil)){
                    conduta = new Conduta(nomeConduta, getTipoConduta(), "Vendedor", 0.2);
                    perfil.getReputacao().addConduta(conduta);
                }
            }
        }
    }
    
    @Override
    public String getTipoConduta(){
        return("Insignia");
    }
}
