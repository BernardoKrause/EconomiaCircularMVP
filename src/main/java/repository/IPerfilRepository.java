/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import model.Perfil;

/**
 *
 * @author caiof
 */
public interface IPerfilRepository{
    public void salvarPerfil(Perfil perfil) throws SQLException ;
    
    public Optional<Perfil> getPorIdUsuario(Integer id) throws SQLException;
    
    public Optional<List<Perfil>> getTodosPerfils() throws SQLException ;
    
    public void deletaPerfil(Integer id) throws SQLException ;
}
