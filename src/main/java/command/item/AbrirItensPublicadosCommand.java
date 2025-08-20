/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package command.item;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import model.Item;
import model.Perfil;
import model.Vendedor;

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
        Optional<List<Item>> listaItens;
        if(perfil.isVendedor()){
            listaItens=itemService.getItensVendedor((Vendedor)perfil);
        }
        else{
            listaItens=Optional.empty();
        }
        presenter.showItens(listaItens);
        desktop.add(presenter.getView());    
    }
    
}