/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.sqlite;

import java.util.List;
import java.util.Optional;
import model.Conduta;
import model.Reputacao;
import repository.ICondutaRepository;

/**
 *
 * @author caiof
 */
public class CondutaRepositorySqLite implements ICondutaRepository{
    public void SalvarCondutaReputacao(Reputacao reputacao, Conduta conduta) {
        
    }
    
    public Optional<List<Conduta>> getTotalCondutas(Reputacao reputacao) {
        return null;
    }
    
    public Optional<List<Conduta>> getCondutasPorTipo(Reputacao reputacao, String tipo) {
        return null;
    }
}
