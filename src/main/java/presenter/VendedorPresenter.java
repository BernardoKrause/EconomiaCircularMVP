/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presenter;

import model.Perfil;
import model.Vendedor;

/**
 *
 * @author caiof
 */
public class VendedorPresenter extends PerfilPresenter{
    private Vendedor vendedor;
    
    public VendedorPresenter(Perfil vendedor){
        super(vendedor);
        this.vendedor=(Vendedor)vendedor;
    }

    @Override
    public void setButtons() {
        view.setTitle("Meu Perfil - Vendedor");
        
    }
    
}
