/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presenter;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListCellRenderer;
import model.Conduta;
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
    
    public PerfilPresenter(Perfil perfil, PerfilService service) throws SQLException{
        this.service=service;
        this.perfil=perfil;
        service.completarPerfil(this.perfil);
        System.out.println(this.perfil.getId());
    }
    
    public void verPerfil() throws SQLException{    
        PerfilView perfilView = new PerfilView();
        view=perfilView;
        
        perfilView.getTxtNome().setText(perfil.getUsuario().getNome());
        perfilView.getTxtNivel().setText(perfil.getReputacao().getNivel());
        perfilView.getTxtEstrelas().setText(String.valueOf(perfil.getReputacao().getEstrelas()));
        perfilView.getTxtBeneficio().setText(String.valueOf(perfil.getReputacao().getBeneficio()));
        
        
        JList<JButton> listaInsignias = perfilView.getLInsignias();
        criarListaCondutas("INSIGNIA",listaInsignias);
        perfilView.getSpInsignias().setViewportView(listaInsignias);
        
        JList<JButton> listaMedalhas = perfilView.getLMedalhas();
        criarListaCondutas("MEDALHA",listaMedalhas);
        perfilView.getSpMedalhas().setViewportView(listaMedalhas);
        
        
        perfilView.getBtnFechar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    fechar();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(perfilView, ex);
                }
           }
        });
        
        setButtons(perfilView);
        
        view.setVisible(false);
        view=perfilView;
        view.setVisible(true);
    }
    
    public abstract void setButtons(PerfilView perfilView);
    
    @Override
    public JInternalFrame getView() {
        return view;
    }
    
    public void fechar(){
        view.dispose();
    }
    
    public void criarListaCondutas(String tipo, JList<JButton> listaCondutas) throws SQLException{
        List<Conduta> condutas = service.getListaCondutasTipo(perfil,tipo).get();
        DefaultListModel<JButton> model = new DefaultListModel<>();
        Integer contadorInsignia = 0;
        
        for (Conduta conduta:condutas) {
            contadorInsignia++;
            JButton button = new JButton(String.valueOf(contadorInsignia));
            model.addElement(button);
        }

        listaCondutas.setModel(model);

        listaCondutas.setCellRenderer(new ListCellRenderer<JButton>() {
            @Override
            public Component getListCellRendererComponent(
                JList<? extends JButton> list,
                JButton value,
                int index,
                boolean isSelected,
                boolean cellHasFocus) {

                value.setBackground(isSelected ? list.getSelectionBackground() : list.getBackground());
                value.setForeground(isSelected ? list.getSelectionForeground() : list.getForeground());
                value.setEnabled(list.isEnabled());
                value.setFont(list.getFont());
                value.setFocusPainted(false);
                value.setBorderPainted(true);

                return value;
            }
        });

        listaCondutas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int index = listaCondutas.locationToIndex(e.getPoint());
                try {
                    JOptionPane.showMessageDialog(view, (condutas.get(index).toString()));
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(view, ex);
                }
            }
        });
        
    }
    
}
