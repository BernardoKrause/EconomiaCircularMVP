package command.perfil;


import model.Usuario;
import repository.PerfilRepository;
import service.PerfilService;
import command.ICommand;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author caiof
 */
public class CriarPerfilVendedorCommand extends PerfilCommand{
    public CriarPerfilVendedorCommand(Usuario usuario){
        super(usuario);
    }
    
    @Override
    public void criar(PerfilService service) {
        service.criarPerfilVendedor(usuario);
    }
    
}
