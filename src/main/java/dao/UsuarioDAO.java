package dao;


import java.util.List;
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
    void criar(Usuario usuario);
    Usuario buscaPorId(Integer id);
    List<Usuario> buscaTodos();
    void atualizar(Usuario usuario);
    void deletar(Integer id);
}
