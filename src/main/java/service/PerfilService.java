/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.sql.SQLException;
import model.Usuario;
import model.Vendedor;
import repository.ICondutaRepository;
import repository.IReputacaoRepository;
import repository.database.PerfilVendedorRepository;
import repository.IPerfilRepository;

/**
 *
 * @author berna
 */
public abstract class PerfilService {
    protected IReputacaoRepository reputacaoRepository;
    
    public PerfilService(IReputacaoRepository reputacaoRepository){
        this.reputacaoRepository=reputacaoRepository;
    }
    
    public abstract void criar(Usuario usuario) throws SQLException;    
    
}
