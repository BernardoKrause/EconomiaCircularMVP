/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presenter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import model.Comprador;
import model.Perfil;
import service.PerfilService;
import view.PerfilView;

/**
 *
 * @author caiof
 */
public class CompradorPresenter extends PerfilPresenter{
    private Comprador comprador;
    private PerfilView perfilView;
    
    public CompradorPresenter(Perfil comprador, PerfilService service) throws SQLException{
        super(comprador,service);
        this.comprador=(Comprador)comprador;
    }

    @Override
    public void setButtons(PerfilView perfilViewPassada) {
        view.setTitle("Meu Perfil - Comprador");
        this.perfilView=perfilViewPassada;
        
        perfilView.getBtnFunc1().setText("Ofertas");
        perfilView.getBtnFunc2().setText("Transações");
        
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
                    verTransacoes();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(perfilView, ex);
                }
           }
        });
    }
    
    public void verOfertas(){
        
    }
    
    public void verTransacoes(){
        
    }
}
