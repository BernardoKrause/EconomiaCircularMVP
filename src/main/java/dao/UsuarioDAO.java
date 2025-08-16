package dao;


import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import model.Usuario;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

/**
 *
 * @author berna
 */
public interface UsuarioDAO {
    public void criar(Usuario usuario) throws SQLException;
    public Optional<Usuario> buscaPorId(Integer id) throws SQLException;
    public List<Usuario> buscaTodos() throws SQLException;
    public void atualizar(Usuario usuario) throws SQLException;
    public void deletar(Integer id) throws SQLException;
    public Optional<Usuario> buscaPorEmail(String email) throws SQLException;
}
