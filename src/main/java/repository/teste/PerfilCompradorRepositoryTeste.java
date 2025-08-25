/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.teste;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Comprador;
import model.Perfil;
import model.Vendedor;
import repository.IPerfilRepository;

/**
 *
 * @author caiof
 */
public class PerfilCompradorRepositoryTeste implements IPerfilRepository{
    private List<Comprador> compradoresCriados;
    private static PerfilCompradorRepositoryTeste instancia;

    private PerfilCompradorRepositoryTeste() throws SQLException {
       this.compradoresCriados = new ArrayList<>();
    }

    public static PerfilCompradorRepositoryTeste getInstancia(){
        if (instancia == null) {
            try {
                instancia = new PerfilCompradorRepositoryTeste();
            } catch (SQLException ex) {
                Logger.getLogger(PerfilCompradorRepositoryTeste.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return instancia;
    }

    @Override
    public void adicionarPerfil(Perfil comprador) throws SQLException {
        compradoresCriados.add((Comprador)comprador);
    }
    
    @Override
    public Optional<Perfil> buscarPorIdUsuario(Integer id) throws SQLException {
        for (Comprador c:compradoresCriados){
            if(id.equals(c.getId())){
                return Optional.of(c);
            }
        }
        return Optional.empty();
    }
    
    @Override
    public List<Perfil> buscarTodosPerfis() throws SQLException {
        List<Perfil> compradores = new ArrayList<>();
        for(Perfil c : compradoresCriados){
            compradores.add(c);
        }
        return compradores;
    }
    
    @Override
    public void deletarPerfil(Integer id) throws SQLException {
        //nao tem
    }

    @Override
    public void atualizarPerfil(Perfil perfil) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }


}
