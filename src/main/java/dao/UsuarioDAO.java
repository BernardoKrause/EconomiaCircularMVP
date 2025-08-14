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
    public Usuario buscaPorId(Integer id);
    public List<Usuario> buscaTodos();
    public void atualizar(Usuario usuario);
    public void deletar(Integer id);
    public Optional<Usuario> buscaPorEmail(String email) throws SQLException;
}
