/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import model.Denuncia;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author berna
 */
public interface DenunciaDAO {
    public void criar(Denuncia denuncia) throws SQLException;
    public List<Denuncia> buscaTodos() throws SQLException;
    public Optional<Denuncia> buscaPorIdC(Integer idC) throws SQLException;
    public void atualizar(Denuncia denuncia) throws SQLException;
    public void deletar(Integer idDenuncia) throws SQLException;
}
