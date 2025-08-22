/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import model.Perfil;

/**
 *
 * @author berna
 */
public interface IPerfilDAO {
    public void criar(Perfil perfil) throws SQLException;
    public Optional<Perfil> buscaPorIdUsuario (Integer id) throws SQLException;
    public List<Perfil> buscaTodos() throws SQLException;
    public void atualizar(Perfil perfil) throws SQLException;
    public void deletar(Integer id) throws SQLException;
}
