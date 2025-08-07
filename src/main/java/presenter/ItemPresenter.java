/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presenter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import model.Item;
import model.Vendedor;
import service.ItemService;
import view.item.FormItemView;

/**
 *
 * @author caiof
 */
public class ItemPresenter {
    
    private FormItemView FormView;
    private ItemService itemService;
    private Vendedor vendedor;

    public ItemPresenter(ItemService itemService){
        this.itemService=itemService;
    }
    
    public void createItem(){
        FormView = new FormItemView();
        FormView.setVisible(false);
        
        FormView.getBtnPublicar().addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try{
                    publicar();
                }catch (Exception ex){
                    JOptionPane.showMessageDialog(FormView, ex);
                }
            }
        });
    }

    public void publicar(){
        String tipo = FormView.getCbTipos().getSelectedItem().getClass().getName();
        String subcategoria = FormView.getTxtSubcategoria().getText();
        String tamanho = String.valueOf(FormView.getSTamanho().getComponentCount());
        if(tipo.equalsIgnoreCase("bolsas")){
            tamanho="Unico";
        }
        String cor = FormView.getTxtCor().getText();
        Double peso = Double.valueOf(FormView.getTxtPeso().getText());
        String composicao = FormView.getCbComposicao().getSelectedItem().getClass().getName();
        Double precoBase = Double.valueOf(FormView.getTxtPrecoBase().getText());
        List<JCheckBox> checkBoxTiposDefeitos = FormView.getLTiposDefeito().getSelectedValuesList();
        
        List<String> defeitos = new ArrayList<>();
        
        for(JCheckBox box : checkBoxTiposDefeitos){
            if(box.isSelected()){
                defeitos.add(box.getText());
            }
        }
        
        Item item = new Item(tipo,subcategoria,tamanho,cor,peso,composicao,precoBase);
        
        try{
            itemService.publicar(item, defeitos, vendedor);
            FormView.dispose();
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
        
        
    }
}
