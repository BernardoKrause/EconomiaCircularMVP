/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package main;

import presenter.GerenciadorTelas;
import presenter.HomePresenter;

/**
 *
 * @author caiof
 */
public class EconomiaCircularMVP {

    public static void main(String[] args) {
        HomePresenter homePresenter = new HomePresenter();
        GerenciadorTelas.getInstancia().inicializar(homePresenter);
    }
}
