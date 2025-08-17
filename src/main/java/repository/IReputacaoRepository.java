/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repository;

import java.util.Optional;
import model.Perfil;
import model.Reputacao;
import org.sqlite.SQLiteException;

/**
 *
 * @author caiof
 */
public interface IReputacaoRepository {    
    public void salvarReputacao(Reputacao reputacao) throws SQLiteException;
    
    public Optional<Reputacao> getReputacao(Perfil perfil);
}
