/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package command.item;

import java.sql.SQLException;
import java.util.Optional;
import model.Item;
import model.Perfil;
import presenter.GerenciadorTelas;

/**
 *
 * @author caiof
 */
public class AbrirCadastroItemCommand extends ItemCommand{
    private Optional<Item> item;
    public AbrirCadastroItemCommand(Perfil perfil, Optional<Item> item)throws SQLException {
        super(perfil);
    }

    /**
     *
     * @throws SQLException
     */
    @Override
    public void executar() throws SQLException {
        presenter.createItem(service,item);
        GerenciadorTelas.getInstancia().addTelaAberta("Vendedor", "CriarItem", presenter);
        desktop.add(presenter.getView());    
    }
    
}
