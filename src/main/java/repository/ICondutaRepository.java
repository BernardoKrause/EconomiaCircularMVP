/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repository;

import java.util.List;
import java.util.Optional;
import model.Conduta;
import model.Reputacao;

/**
 *
 * @author caiof
 */
public interface ICondutaRepository {
    public void SalvarCondutaReputacao(Reputacao reputacao, Conduta conduta);
    
    public Optional<List<Conduta>> getTotalCondutas(Reputacao reputacao);
    
    public Optional<List<Conduta>> getCondutasPorTipo(Reputacao reputacao, String tipo);
}
