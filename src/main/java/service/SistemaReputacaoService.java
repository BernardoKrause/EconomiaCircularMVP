/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import model.Conduta;
import model.Perfil;
import repository.ICondutaRepository;
import repository.IReputacaoRepository;
import strategy.sistemaResputacao.IMetodoReputacao;
import strategy.sistemaResputacao.MetodoInsignia;

/**
 *
 * @author caiof
 */
public class SistemaReputacaoService {
    private List<IMetodoReputacao> metodos;
    
    private Map<String, Map<String, Double>> condutasPorPerfil;
    private Map<String, Double> condutasRecorrentesVendedor;
    private Map<String, Double> condutasRecorrentesComprador;
    private ICondutaRepository condutaRepo;
    private IReputacaoRepository reputacaoRepo;
    
    public SistemaReputacaoService(ICondutaRepository condutaRepo, IReputacaoRepository reputacaoRepo){
        this.condutaRepo=condutaRepo;
        this.reputacaoRepo=reputacaoRepo;
        condutasPorPerfil = new HashMap<>();
        condutasRecorrentesVendedor = new HashMap<>();
        condutasRecorrentesComprador = new HashMap<>();
        
        condutasRecorrentesVendedor.put("Cadastro de item completo", 0.05);
        condutasRecorrentesComprador.put("Oferta dentro do intervalo permitido", 0.05);
        condutasRecorrentesVendedor.put("Resposta à oferta em até 24 horas", 0.05);
        condutasRecorrentesVendedor.put("Avaliação textual após venda", 0.05);
        condutasRecorrentesComprador.put("Avaliação textual após venda", 0.05);
        condutasRecorrentesVendedor.put("Transação concluída", 0.5);
        condutasRecorrentesComprador.put("Transação concluída", 0.5);
        condutasRecorrentesComprador.put("Denúncia procedente", 0.1);
        condutasRecorrentesVendedor.put("Descrição enganosa ou omissão confirmada", -0.5);
        condutasRecorrentesVendedor.put("Bloqueio de conta por 3 penalidades", 0.0);
         
        condutasPorPerfil.put("Vendedor", condutasRecorrentesVendedor);
        condutasPorPerfil.put("Comprador", condutasRecorrentesVendedor);
        
        metodos = new ArrayList<>();
        metodos.add(new MetodoInsignia());
    }
    
    public void atualizarReputacao(Perfil perfil, Optional<String> nomeConduta) throws SQLException{
        if(!nomeConduta.isEmpty()){
            aplicarConduta(perfil,nomeConduta.get());
        }
        for(IMetodoReputacao metodo : metodos){
            System.out.println("erro5");
            Conduta conduta = metodo.aplicarReputacao(perfil).get();
            perfil.getReputacao().addConduta(conduta);
            System.out.println(perfil.getReputacao().getId());
            try{
                condutaRepo.adicionarConduta(perfil.getReputacao(), conduta);
                System.out.println("erro6");
                reputacaoRepo.atualizarReputacao(perfil.getReputacao());
                System.out.println("erro7");
            } catch (Exception ex){
                throw new RuntimeException("Erro ao atualizar a reputaçao " + ex.getMessage());
            }
        }
        
    }
    
    private void aplicarConduta(Perfil perfil, String nomeConduta) throws SQLException{
        String tipoPerfil = "Vendedor";
        String tipoConduta = "Conduta Recorrente";
                System.out.println(perfil.getSistemId());
        
        if(perfil.getSistemId().startsWith("C")){
            tipoPerfil = "Comprador";
        }
        if(condutasPorPerfil.get(tipoPerfil).get(nomeConduta) <= 0.0){
            tipoConduta = "Penalidade";
        }
        Conduta conduta = new Conduta(nomeConduta, tipoConduta, tipoPerfil, condutasPorPerfil.get(tipoPerfil).get(nomeConduta));
        perfil.getReputacao().addConduta(conduta);
        try{
            condutaRepo.adicionarConduta(perfil.getReputacao(), conduta);
            reputacaoRepo.atualizarReputacao(perfil.getReputacao());
        } catch (Exception ex){
            throw new RuntimeException("Erro ao atualizar a reputaçao");
        }
    }
}
