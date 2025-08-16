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
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListCellRenderer;
import model.Item;
import model.Vendedor;
import service.ItemService;
import view.item.FormItemView;

/**
 *
 * @author caiof
 */
public class ItemPresenter implements IPresenter{
    
    private FormItemView formView;
    private ItemService itemService;
    private Vendedor vendedor;

    public ItemPresenter(ItemService itemService) throws SQLException{
        this.itemService=itemService;
        createItem();
    }
    
    public void createItem() throws SQLException{
        formView = new FormItemView();
        formView.setVisible(false);
        
        formView.getBtnPublicar().addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try{
                    publicar();
                }catch (Exception ex){
                    JOptionPane.showMessageDialog(formView, ex);
                }
            }
        });
        
        formView.getBtnCancelar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    cancelar();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(formView, ex);
                }
           }
        });
        
         JComboBox comboBoxTipo = formView.getCbTipos();
    for (String tipo : itemService.getListaTiposItem()) {
        comboBoxTipo.addItem(tipo);
    }

    comboBoxTipo.addActionListener(e -> {
        try {
            String tipoSelecionado = String.valueOf(comboBoxTipo.getSelectedItem());

            formView.getTxtSubcategoria().setText("");
            formView.getSTamanho().setValue(1);
            formView.getTxtPeso().setText("0.0");
            formView.getTxtCor().setText("");
            formView.getCbComposicao().removeAllItems();

            for (String material : itemService.getListaMateriaisComposicao()) {
                formView.getCbComposicao().addItem(material);
            }

            formView.getTxtPrecoBase().setText("0.0");

            List<String> defeitos = itemService.getListaDefeitosExistentes(tipoSelecionado);
            DefaultListModel<JCheckBox> model = new DefaultListModel<>();

            for (String defeito : defeitos) {
                JCheckBox check = new JCheckBox(defeito);
                model.addElement(check);
            }

            JList<JCheckBox> listaTiposDefeitos = formView.getLTiposDefeito();
            listaTiposDefeitos.setModel(model);

            listaTiposDefeitos.setCellRenderer(new ListCellRenderer<JCheckBox>() {
                @Override
                public Component getListCellRendererComponent(
                    JList<? extends JCheckBox> list,
                    JCheckBox value,
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

            listaTiposDefeitos.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int index = listaTiposDefeitos.locationToIndex(e.getPoint());
                    if (index != -1) {
                        JCheckBox checkbox = model.getElementAt(index);
                        checkbox.setSelected(!checkbox.isSelected());
                        listaTiposDefeitos.repaint();
                    }
                }
            });

            formView.getSpDefeitos().setViewportView(listaTiposDefeitos);

            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(formView, ex);
        }
    });

    comboBoxTipo.setSelectedIndex(0);

    formView.setVisible(true);
    }

    public void publicar(){
        String tipo = String.valueOf(formView.getCbTipos().getSelectedItem());
        String subcategoria = formView.getTxtSubcategoria().getText();
        String tamanho = String.valueOf(formView.getSTamanho().getComponentCount());
        if(tipo.equalsIgnoreCase("bolsas")){
            tamanho="Unico";
        }
        String cor = formView.getTxtCor().getText();
        Double peso = Double.valueOf(formView.getTxtPeso().getText());
        String composicao = String.valueOf(formView.getCbComposicao().getSelectedItem());
        Double precoBase = Double.valueOf(formView.getTxtPrecoBase().getText());
        
        DefaultListModel<JCheckBox> model = (DefaultListModel<JCheckBox>) formView.getLTiposDefeito().getModel();
        List<String> defeitos = new ArrayList<>();

        for (int i = 0; i < model.size(); i++) {
            JCheckBox box = model.getElementAt(i);
            if (box.isSelected()) {
                defeitos.add(box.getText());
                System.out.println(box.getText()); // debug
            }
        }
        
        Item item = new Item(tipo,subcategoria,tamanho,cor,peso,composicao,precoBase);
        
        try{
            vendedor = new Vendedor("10");
            itemService.criar(item, defeitos, vendedor);
            formView.dispose();
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }
    
    public void cancelar() {
        formView.dispose();
    }
    
    @Override
    public JInternalFrame getView(){
        return formView;
    }
}
