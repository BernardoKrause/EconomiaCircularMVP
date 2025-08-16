/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import model.Comprador;
import model.Denuncia;
import model.Vendedor;

/**
 *
 * @author caiof
 */
public interface IDenunciaRepository {
    public void adicionaDenuncia(String idC, String descricao, String status, Comprador comprador, Vendedor vendedor) throws SQLException ;

    public Optional<Denuncia> getDenuncia(Integer id) throws SQLException ;

    public List<Denuncia> getTodasDenuncias() throws SQLException ;

    public void atualizaDenuncia(String idC, String descricao, String status, Comprador comprador, Vendedor vendedor) throws SQLException ;
    
    public void deletaDenuncia(Integer id) throws SQLException ;
}
