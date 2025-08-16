/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package main;

import dao.UsuarioDAO;
import dao.UsuarioDAOSQLite;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import model.Usuario;
import presenter.GerenciadorTelas;
import presenter.HomePresenter;
import repository.sqlite.UsuarioRepositorySqLite;

/**
 *
 * @author caiof
 */
public class EconomiaCircularMVP {

    public static void main(String[] args) throws SQLException {
        HomePresenter homePresenter = new HomePresenter();
        GerenciadorTelas.getInstancia().inicializar(homePresenter);
        
    }
}
