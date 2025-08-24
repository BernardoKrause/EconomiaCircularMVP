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
    public void adicionarPerfil(Perfil perfil) throws SQLException;
    
    public Optional<Perfil> buscarPorIdUsuario(Integer id) throws SQLException;
    
    public List<Perfil> buscarTodosPerfis() throws SQLException;
    
    public void atualizarPerfil (Perfil perfil) throws SQLException;
    
    public void deletarPerfil(Integer id) throws SQLException;
}
