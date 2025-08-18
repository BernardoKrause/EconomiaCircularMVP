/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presenter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import model.Perfil;
import model.Vendedor;
import service.PerfilService;
import view.PerfilView;

/**
 *
 * @author caiof
 */
public class VendedorPresenter extends PerfilPresenter{
    private Vendedor vendedor;
    
    public VendedorPresenter(Perfil vendedor, PerfilService service) throws SQLException{
        super(vendedor,service);
        this.vendedor=(Vendedor)vendedor;
    }

    @Override
    public void setButtons(PerfilView perfilView) {
        view.setTitle("Meu Perfil - Vendedor");
        
        perfilView.getBtnFunc1().setText("Ofertas");
        perfilView.getBtnFunc2().setText("Itens");
        
        perfilView.getBtnFunc1().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    verOfertas();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(perfilView, ex);
                }
           }
        });
        
        perfilView.getBtnFunc2().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    verItensPublicados();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(perfilView, ex);
                }
           }
        });
    }
    
    public void verOfertas(){
        
    }
    
    public void verItensPublicados(){
        
    }
}
