/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.teste;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import model.Conduta;
import model.Reputacao;
import model.Vendedor;
import repository.IReputacaoRepository;

/**
 *
 * @author caiof
 */
public class ReputacaoRepositoryTeste implements IReputacaoRepository{
    private List<Reputacao> reputacoes;
    private static ReputacaoRepositoryTeste instancia;
    
    private ReputacaoRepositoryTeste(){
        reputacoes=new ArrayList<>();
    }
    
    public static ReputacaoRepositoryTeste getInstancia(){
        if(instancia==null){
            instancia= new ReputacaoRepositoryTeste();
        }
        return instancia;
    }

    @Override
    public void adicionarReputacao(Reputacao reputacao) {
        reputacoes.add(reputacao);
    }

    @Override
    public void salvarReputacao(Reputacao reputacao) {
        try{
            for(Reputacao re:reputacoes){
                if(reputacao.getId().equals(re.getId())){
                    re.copy(reputacao);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
