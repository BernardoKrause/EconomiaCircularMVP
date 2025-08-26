/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package main;

import factory.dao.IDAOFactory;
import java.sql.SQLException;
import model.Material;
import presenter.GerenciadorTelas;
import presenter.HomePresenter;

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
