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
import model.Perfil;
import model.Vendedor;
import repository.IPerfilRepository;

/**
 *
 * @author caiof
 */
public class PerfilVendedorRepositoryTeste implements IPerfilRepository{
    private List<Vendedor> vendedoresCriados;
    private static PerfilVendedorRepositoryTeste instancia;

    private PerfilVendedorRepositoryTeste() throws SQLException {
       this.vendedoresCriados = new ArrayList<>();
    }

    public static PerfilVendedorRepositoryTeste getInstancia(){
        if (instancia == null) {
            try {
                instancia = new PerfilVendedorRepositoryTeste();
            } catch (SQLException ex) {
                Logger.getLogger(PerfilVendedorRepositoryTeste.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return instancia;
    }

    @Override
    public void adicionarPerfil(Perfil vendedor) throws SQLException {
        vendedoresCriados.add((Vendedor)vendedor);
    }
    
    @Override
    public Optional<Perfil> buscarPorIdUsuario(Integer id) throws SQLException {
        for (Vendedor v:vendedoresCriados){
            if(id.equals(v.getId())){
                return Optional.of(v);
            }
        }
        return Optional.empty();
    }
    
    @Override
    public List<Perfil> buscarTodosPerfis() throws SQLException {
        List<Perfil> vendedores = new ArrayList<>();
        for(Perfil v : vendedoresCriados){
            vendedores.add(v);
        }
        return vendedores;
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

