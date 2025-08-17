/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presenter;

import javax.swing.JInternalFrame;
import model.Perfil;
import service.PerfilService;
import view.PerfilView;

/**
 *
 * @author caiof
 */
public abstract class PerfilPresenter implements IPresenter{
    protected JInternalFrame view;
    protected PerfilService service;
    protected Perfil perfil;
    
    public PerfilPresenter(Perfil perfil){
        this.perfil=perfil;
    }
    
    public void verPerfil(){       
        PerfilView perfilView = new PerfilView();
        perfilView.setVisible(false);
        this.view=perfilView;
        
        perfilView.getTxtNome().setText(perfil.getUsuario().getNome());
        perfilView.getTxtNivel().setText(perfil.getReputacao().getNivel());
        perfilView.getTxtEstrelas().setText(String.valueOf(perfil.getReputacao().getEstrelas()));
        setButtons();
        
        perfilView.setVisible(true);
    }
    
    public abstract void setButtons();
    
    @Override
    public JInternalFrame getView() {
        return view;
    }
    
}
