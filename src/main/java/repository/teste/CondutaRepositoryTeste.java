/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.teste;

import java.sql.SQLException;
import model.Conduta;
import model.Reputacao;
import repository.ICondutaRepository;
import java.util.ArrayList;
import java.util.List;

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

    public static CondutaRepositoryTeste getInstancia(){
        if(instancia == null){
            instancia = new CondutaRepositoryTeste();
        }
        return instancia;
    }

    @Override
    public List<Conduta> buscarTodasCondutas(Reputacao reputacao) {
        List<Conduta> lista = new ArrayList<>();
        for(Conduta c:condutasReputacaoCadastradas){
            for(Conduta cRep:reputacao.getCondutas()){
                if(c.equals(cRep)){
                    lista.add(cRep);
                }
            }
        }
        return lista;
    }

    @Override
    public List<Conduta> buscarCondutasPorTipo(Reputacao reputacao, String tipo){
        List<Conduta> lista = new ArrayList<>();
        for(Conduta c:condutasReputacaoCadastradas){
            for(Conduta cRep:reputacao.getCondutas()){
                if(c.equals(cRep) && c.getTipo().equals(tipo)){
                    lista.add(cRep);
                }
            }
        }
        return lista;
    }

    @Override
    public void adicionarConduta(Reputacao reputacao, Conduta conduta) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
