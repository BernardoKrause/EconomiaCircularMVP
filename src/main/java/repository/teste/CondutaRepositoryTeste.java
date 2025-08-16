/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.teste;

import model.Conduta;
import model.Reputacao;
import repository.ICondutaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author caiof
 */
public class CondutaRepositoryTeste implements ICondutaRepository{
    private static CondutaRepositoryTeste instancia;
    private List<Conduta> condutasReputacaoCadastradas;

    private CondutaRepositoryTeste(){
        condutasReputacaoCadastradas = new ArrayList<Conduta>();
    }

    public CondutaRepositoryTeste getInstancia(){
        if(instancia == null){
            instancia = new CondutaRepositoryTeste();
        }
        return instancia;
    }

    @Override
    public void SalvarCondutaReputacao(Reputacao reputacao, Conduta conduta) {
        condutasReputacaoCadastradas.add(conduta);
        reputacao.addConduta(conduta);
    }

    @Override
    public Optional<List<Conduta>> getTotalCondutas(Reputacao reputacao) {
        List<Conduta> lista = new ArrayList<>();
        for(Conduta c:condutasReputacaoCadastradas){
            for(Conduta cRep:reputacao.getCondutas()){
                if(c.equals(cRep)){
                    lista.add(cRep);
                }
            }
        }
        return Optional.of(lista);
    }

    @Override
    public Optional<List<Conduta>> getCondutasPorTipo(Reputacao reputacao, String tipo){
        List<Conduta> lista = new ArrayList<>();
        for(Conduta c:condutasReputacaoCadastradas){
            for(Conduta cRep:reputacao.getCondutas()){
                if(c.equals(cRep) && c.getTipo().equals(tipo)){
                    lista.add(cRep);
                }
            }
        }
        return Optional.of(lista);
    }
}
