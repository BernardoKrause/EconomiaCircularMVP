/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package command.item;

import java.sql.SQLException;
import model.Perfil;
import presenter.GerenciadorTelas;

/**
 *
 * @author caiof
 */
public class AbrirCadastroItemCommand extends ItemCommand{
    public AbrirCadastroItemCommand(Perfil perfil)throws SQLException {
        super(perfil);
    }

    /**
     *
     * @throws SQLException
     */
    @Override
    public void executar() throws SQLException {
        presenter.createItem();
        GerenciadorTelas.getInstancia().addTelaAberta("Vendedor", "CriarItem", presenter);
        desktop.add(presenter.getView());    
    }
    
}
