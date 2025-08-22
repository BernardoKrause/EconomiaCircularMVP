/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.database;

import factory.dao.IDAOFactory;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import model.Perfil;
import model.Vendedor;
import repository.IPerfilRepository;
import dao.IPerfilDAO;
import java.util.stream.Collectors;

/**
 *
 * @author berna
 */
public class PerfilVendedorRepository implements IPerfilRepository{
    private final IPerfilDAO perfilVendedorDAO;

    public PerfilVendedorRepository(IDAOFactory daoFactory) throws SQLException {
       this.perfilVendedorDAO = daoFactory.getPerfilVendedorDAO();
    }
    
    @Override
    public void adicionarPerfil(Perfil perfil) throws SQLException {
        perfilVendedorDAO.criar((Vendedor)perfil);
    }
    
    // implementar update
    
    @Override
    public Optional<Perfil> buscarPorIdUsuario(Integer id) throws SQLException {
        Perfil perfilAchado = perfilVendedorDAO.buscaPorIdUsuario(id).get();
        return Optional.of(perfilAchado);
    }
    
    @Override
    public List<Perfil> buscarTodosPerfis() throws SQLException {
        List<Perfil> vendedores = perfilVendedorDAO.buscaTodos();
        return vendedores.stream()
                .map(comprador -> (Perfil) comprador)
                .collect(Collectors.toList());
    }
    
    @Override
    public void atualizarPerfil (Perfil perfil) throws SQLException {
        perfilVendedorDAO.atualizar((Vendedor)perfil);
    }
    
    @Override
    public void deletarPerfil(Integer id) throws SQLException {
        perfilVendedorDAO.deletar(id);
    }
    
}
