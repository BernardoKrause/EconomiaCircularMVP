/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repository;

import model.Reputacao;
import org.sqlite.SQLiteException;

/**
 *
 * @author caiof
 */
public interface IReputacaoRepository {
    public void adicionarReputacao(Reputacao reputacao) throws SQLiteException;
    
    public void salvarReputacao(Reputacao reputacao) throws SQLiteException;
}
