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
    private PerfilView perfilView;
    
    public VendedorPresenter(Perfil vendedor, PerfilService service) throws SQLException{
        super(vendedor,service);
        this.vendedor=(Vendedor)vendedor;
    }

    @Override
    public void setButtons(PerfilView perfilViewPassada) {
        view.setTitle("Meu Perfil - Vendedor");
        this.perfilView=perfilViewPassada;
        
        perfilView.getBtnFunc1().setText("Ofertas");
        perfilView.getBtnFunc2().setText("Itens");
        
        resetButtonActions(perfilView.getBtnFunc1());
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
        
        resetButtonActions(perfilView.getBtnFunc2());
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
