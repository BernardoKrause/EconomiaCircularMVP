/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package command.item;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Comprador;
import model.Item;
import model.Perfil;
import model.Vendedor;
import presenter.GerenciadorTelas;

/**
 *
 * @author caiof
 */
public class AbrirVerItemCommand extends ItemCommand{
    private Item item;
    public AbrirVerItemCommand(Vendedor perfil, Item item)throws SQLException {
        super(perfil);
        this.item = item;
    }
    public AbrirVerItemCommand(Comprador perfil, Item item)throws SQLException {
        super(perfil);
        this.item = item;
    }

    /**
     *
     * @throws SQLException
     */
    @Override
    public void executar() throws SQLException {
        String tipoTela="Comprador";
        if(perfil.isVendedor()){
            tipoTela="Vendedor";
        }
        try {
            presenter.showItem(item);
        } catch (Exception ex) {
            Logger.getLogger(AbrirItensPublicadosCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        GerenciadorTelas.getInstancia().addTelaAberta(tipoTela, "VerItem", presenter);
        desktop.add(presenter.getView());    
    }
    
}
