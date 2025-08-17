/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.database;

import java.util.Optional;
import model.Perfil;
import model.Reputacao;
import org.sqlite.SQLiteException;
import repository.IReputacaoRepository;

/**
 *
 * @author caiof
 */
public class ReputacaoRepositorySqLite implements IReputacaoRepository{

    @Override
    public void salvarReputacao(Reputacao reputacao) throws SQLiteException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Optional<Reputacao> getReputacao(Perfil perfil) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
