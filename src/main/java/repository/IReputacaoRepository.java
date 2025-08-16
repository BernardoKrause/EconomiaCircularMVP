/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repository;

import java.util.List;
import java.util.Optional;

import model.Conduta;
import model.Reputacao;
import model.Vendedor;

/**
 *
 * @author caiof
 */
public interface IReputacaoRepository {
    public void adicionarReputacao(Reputacao reputacao);
    
    public void salvarReputacao(Reputacao reputacao);
}
