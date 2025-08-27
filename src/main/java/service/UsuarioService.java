/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import factory.repository.SeletorRepositoryFactory;
import java.sql.SQLException;
import java.util.Optional;
import model.Usuario;
import repository.IPerfilRepository;
import repository.IUsuarioRepository;

/**
 *
 * @author berna
 */
public class UsuarioService {
    private final IUsuarioRepository usuarioRepository;
    
    public UsuarioService(IUsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }
    
    public void cadastrarUsuario(String nome, String email, String telefone, String senha) throws SQLException, Exception {
        
        ValidadorSenhaService validador = new ValidadorSenhaService();
        
        validador.valida(senha);

        boolean isAdmin = usuarioRepository.totalUsuarios() == 0;
        try {
            usuarioRepository.adicionarUsuario(nome, email, telefone, senha, isAdmin);
        } catch (SQLException ex) {
            throw ex;
        }
    }
    
    public void autenticarUsuario (Usuario usuario) throws SQLException {
        Optional<Usuario> optUsuario;
        try {
            optUsuario = usuarioRepository.buscarUsuarioPorEmail(usuario.getEmail());
        } catch (SQLException ex) {
            throw new RuntimeException ("Erro ao buscar usuario"+ex.getMessage());
        }
        
        if (optUsuario.isPresent()) {
            if (optUsuario.get().getSenha().equals(usuario.getSenha())) {
                usuario.copy(optUsuario.get());
                usuario.setAutenticado(true);
            }
        } else {
            throw new RuntimeException("Email e senha não correspondem à um usuário.");
        }
    }
    
    public void completarUsuario(Usuario usuario) throws SQLException{
        IPerfilRepository repoVendedor = SeletorRepositoryFactory.obterInstancia().criarPerfilVendedorRepository();
        IPerfilRepository repoComprador = SeletorRepositoryFactory.obterInstancia().criarPerfilCompradorRepository();
        
        try{
            if(!repoVendedor.buscarPorIdUsuario(usuario.getId()).isEmpty()){
                usuario.addPerfil(repoVendedor.buscarPorIdUsuario(usuario.getId()).get());  
                System.out.println(repoVendedor.buscarPorIdUsuario(usuario.getId()).get());
            }
            if(!repoComprador.buscarPorIdUsuario(usuario.getId()).isEmpty()){
                usuario.addPerfil(repoComprador.buscarPorIdUsuario(usuario.getId()).get()); 
                System.out.println(repoComprador.buscarPorIdUsuario(usuario.getId()).get());
            }
            
        }catch(Exception ex){
            throw new RuntimeException("erro ao puxar perfis");
        }
    }
}
