/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.teste;

import dao.DenunciaDAO;
import dao.DenunciaDAOSQLite;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import model.Comprador;
import model.Denuncia;
import model.Vendedor;
import repository.IDenunciaRepository;

/**
 *
 * @author caiof
 */
public class DenunciaRepositoryTeste implements IDenunciaRepository{
     private List<Denuncia> denunciasPublicadas;

    public DenunciaRepositoryTeste() throws SQLException {
        denunciasPublicadas=new ArrayList<>();
    }

     @Override
    public void adicionaDenuncia(String idC, String descricao, String status, Comprador comprador, Vendedor vendedor) throws SQLException {
        Denuncia d=new Denuncia(idC, descricao, status, comprador, vendedor);
    }

     @Override
    public Optional<Denuncia> getDenuncia(Integer id) throws SQLException {
        for(Denuncia d:denunciasPublicadas){
            if(d.getId()==id){
                return Optional.of(d);
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Denuncia> getTodasDenuncias() throws SQLException {
        return denunciasPublicadas;
    }

     @Override
    public void atualizaDenuncia(String idC, String descricao, String status, Comprador comprador, Vendedor vendedor) throws SQLException {
        //fazer
    }
    
     @Override
    public void deletaDenuncia(Integer id) throws SQLException {
        //fazer
    }
}
