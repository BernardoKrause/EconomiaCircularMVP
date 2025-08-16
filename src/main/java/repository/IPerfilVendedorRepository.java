/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import model.Reputacao;
import model.Vendedor;

/**
 *
 * @author caiof
 */
public interface IPerfilVendedorRepository {
    public void adicionaPerfil(Vendedor vendedor) throws SQLException ;
    
    public List<Vendedor> getTodosVendedores() throws SQLException ;
    
    public void deletaPerfil(Integer id) throws SQLException ;

    public Optional<Reputacao> getReputacaoVendedor(Vendedor vendedor) throws SQLException ;
}
