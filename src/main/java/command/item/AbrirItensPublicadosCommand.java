/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package command.item;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Item;
import model.Perfil;
import model.Vendedor;
import presenter.GerenciadorTelas;

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
        List<Item> listaItens = new ArrayList<>();
        String tipoTela="Comprador";
        if(perfil.isVendedor()){
            listaItens=itemService.getItensVendedor((Vendedor)perfil);
            tipoTela="Vendedor";
        }
        try {
            presenter.showItens(listaItens);
        } catch (Exception ex) {
            Logger.getLogger(AbrirItensPublicadosCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        GerenciadorTelas.getInstancia().addTelaAberta(tipoTela, "VerItens", presenter);
        desktop.add(presenter.getView());    
    }
    
}