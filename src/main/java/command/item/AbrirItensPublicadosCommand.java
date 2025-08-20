/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package command.item;

import java.sql.SQLException;
import model.Perfil;

/**
 *
 * @author caiof
 */
public class AbrirItensPublicadosCommand extends ItemCommand{
    public AbrirItensPublicadosCommand(Perfil perfil)throws SQLException {
        super(perfil);
    }

    /**
     *
     * @throws SQLException
     */
    @Override
    public void executar() throws SQLException {
        presenter.showItens();
        desktop.add(presenter.getView());    
    }
    
}