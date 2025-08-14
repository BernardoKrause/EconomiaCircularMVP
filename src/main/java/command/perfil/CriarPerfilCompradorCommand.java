/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package command.perfil;

import model.Usuario;
import service.PerfilService;

/**
 *
 * @author caiof
 */
public class CriarPerfilCompradorCommand extends PerfilCommand{
    public CriarPerfilCompradorCommand(Usuario usuario){
        super(usuario);
    }
    
    @Override
    public void criar(PerfilService service) {
        service.criarPerfilComprador(usuario);
    }
    
}

