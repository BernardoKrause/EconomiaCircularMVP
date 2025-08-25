/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presenter;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import model.Item;
import model.Material;
import model.Perfil;
import model.Vendedor;
import service.ItemService;
import service.PerfilService;
import view.item.FormItemView;
import view.item.MaterialComposicao;
import view.item.ShowItensView;

/**
 *
 * @author caiof
 */
public class ItemPresenter extends AbstractPresenter {

    private ItemService itemService;
    private PerfilService perfilService;
    private Perfil perfil;
    private String tipoTela;
    private String nomeTela;

    public ItemPresenter(ItemService itemService, Perfil perfil) throws SQLException{
        this.itemService=itemService;
        this.perfil = perfil;
    }

    public void createItem(PerfilService perfilService) throws SQLException{
        this.perfilService = perfilService;
        FormItemView formView = new FormItemView();
        tipoTela = "Vendedor";
        nomeTela = "CriarItem";


        resetButtonActions(formView.getBtnPublicar());
        resetButtonActions(formView.getBtnCancelar());

        formView.setVisible(false);
        formView.getBtnPublicar().addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try{
                    publicar();
                }catch (Exception ex){
                    JOptionPane.showMessageDialog(formView, "Erro ao Atualizar reputacao "+ex);
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

        // precisa de um parametro pra informar o item
        JComboBox comboBoxTipo = formView.getCbTipos();
        for (String tipo : itemService.getListaTipos()) {
            comboBoxTipo.addItem(tipo);
        }

        for(ActionListener al : comboBoxTipo.getActionListeners()) {
            comboBoxTipo.removeActionListener(al);
        }

        comboBoxTipo.addActionListener(e -> {
            try {
                String tipoSelecionado = String.valueOf(comboBoxTipo.getSelectedItem());

                formView.getTxtSubcategoria().setText("");
                formView.getSTamanho().setValue(1);
                formView.getTxtCor().setText("");

                List<Material> materiais = itemService.getListaMateriaisComposicao(tipoSelecionado);
                DefaultListModel<MaterialComposicao> modelMaterial = new DefaultListModel<>();

                for(Material material : materiais){
                    MaterialComposicao materialComposicao = new MaterialComposicao();
                    materialComposicao.getLblMaterial().setText(material.getTipo());
                    materialComposicao.getNumPercentual().setValue(0.0);
                    modelMaterial.addElement(materialComposicao);
                }

                JPanel listaMaterialComposicao = formView.getPMateriais();
                listaMaterialComposicao.setLayout(new BoxLayout(listaMaterialComposicao, BoxLayout.Y_AXIS));

                for (Material material : materiais) {
                    MaterialComposicao materialComposicao = new MaterialComposicao();
                    materialComposicao.getLblMaterial().setText(material.getTipo());
                    materialComposicao.getNumPercentual().setValue(0.0);

                    listaMaterialComposicao.add(materialComposicao);
                }
                formView.getSpComposicao().setViewportView(listaMaterialComposicao);

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
        this.view=formView;
    }

    public void showItens(List<Item> listaExistente) throws Exception{
        tipoTela="Comprador";
        nomeTela = "VerItens";
        ShowItensView itensView = new ShowItensView();
        itensView.setVisible(false);

        resetButtonActions(itensView.getBtnFechar());

        itensView.getBtnFechar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    cancelar();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(itensView, ex);
                }
           }
        });

        List<Item> itens;

        itens = itemService.getItens();
        if (!listaExistente.isEmpty()){
            itens=listaExistente;
            tipoTela="Vendedor";
        }


        DefaultTableModel model = new DefaultTableModel(
        new Object[]{"Tipo", "Subcategoria", "Tamanho", "Cor", "Preço Final", "ID Vendedor", "Ações"},
            0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 7;
            }
        };

        JTable tabelaItens = itensView.getTbItens();
        tabelaItens.setModel(model);


        for (Item item : itens) {
            JButton btnVer = new JButton("V");
            JButton btnAcao = new JButton(tipoTela.equals("Vendedor") ? "E" : "O");

            btnVer.setMargin(new Insets(2, 6, 2, 6));
            btnAcao.setMargin(new Insets(2, 6, 2, 6));
            btnVer.setPreferredSize(new Dimension(40, 25));
            btnAcao.setPreferredSize(new Dimension(40, 25));

            resetButtonActions(btnVer);
            resetButtonActions(btnAcao);
            
            btnVer.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        verItem(item);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(itensView, ex);
                    }
                }
            });

            btnAcao.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        func2(item);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(itensView, ex);
                    }
                }
            });

            List<JButton> botoes = new ArrayList<>();
            botoes.add(btnVer);
            botoes.add(btnAcao);

            Object[] linha = {
                    item.getTipo(),
                    item.getSubcategoria(),
                    item.getTamanho(),
                    item.getCor(),
                    item.getPrecoFinal(),
                    item.getVendedor().getSistemId(),
                    botoes
            };
            model.addRow(linha);
        }

        tabelaItens.getColumn("Ações").setCellRenderer(new TableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                           boolean isSelected, boolean hasFocus,
                                                           int row, int column) {
                JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
                if (value instanceof List<?>) {
                    for (Object obj : (List<?>) value) {
                        if (obj instanceof JButton) {
                            panel.add((JButton) obj);
                        }
                    }
                }
                return panel;
            }
        });

        tabelaItens.getColumn("Ações").setCellEditor(new DefaultCellEditor(new JCheckBox()) {
            private JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));

            @Override
            public Component getTableCellEditorComponent(JTable table, Object value,
                                                         boolean isSelected, int row, int column) {
                panel.removeAll();
                if (value instanceof List<?>) {
                    for (Object obj : (List<?>) value) {
                        if (obj instanceof JButton) {
                            panel.add((JButton) obj);
                        }
                    }
                }
                return panel;
            }

            @Override
            public Object getCellEditorValue() {
                return null;
            }
        });

        itensView.getSpItens().setViewportView(tabelaItens);

        itensView.setVisible(true);
        this.view=itensView;
    }

    public void publicar() throws Exception{
        FormItemView formView = (FormItemView)view;
        String tipo = String.valueOf(formView.getCbTipos().getSelectedItem());
        String subcategoria = formView.getTxtSubcategoria().getText();
        String tamanho = String.valueOf(formView.getSTamanho().getValue().toString());
        if(tipo.equalsIgnoreCase("bolsas")){
            tamanho="Unico";
        }
        String cor = formView.getTxtCor().getText();
        Double peso = Double.valueOf(formView.getTxtPeso().getText());
        Double precoBase = Double.valueOf(formView.getTxtPrecoBase().getText());

        Double porcentagemTotal=0.0;

        JPanel listaMaterialComposicao = formView.getPMateriais();
        List<Material> composicao = new ArrayList<>();

        for (Component comp : listaMaterialComposicao.getComponents()) {
            if (comp instanceof MaterialComposicao) {
                MaterialComposicao panel = (MaterialComposicao) comp;

                String tipoMaterial = panel.getLblMaterial().getText();
                Double percentual =  Double.valueOf(panel.getNumPercentual().getValue().toString());
                Double fatorMaterial = itemService.getFatorMaterial(tipoMaterial);

                porcentagemTotal += percentual;
                if(percentual != 0.0){
                    composicao.add(new Material(tipoMaterial, fatorMaterial, percentual / 100));
                }
            }
        }

        if (porcentagemTotal != 100) {
            JOptionPane.showMessageDialog(formView,
                "A soma das porcentagens deve ser igual a 100% (atual: " + porcentagemTotal + "%)");
            return;
        }

        DefaultListModel<JCheckBox> model = (DefaultListModel<JCheckBox>) formView.getLTiposDefeito().getModel();
        List<String> defeitos = new ArrayList<>();

        for (int i = 0; i < model.size(); i++) {
            JCheckBox box = model.getElementAt(i);
            if (box.isSelected()) {
                defeitos.add(box.getText());
            }
        }

        Item item = new Item(tipo,subcategoria,tamanho,cor,peso,composicao,precoBase);

        try{
            itemService.criar(item, defeitos, (Vendedor)perfil);
            perfilService.atualizarReputacao(perfil, "Cadastro de item completo");
            GerenciadorTelas.getInstancia().removeTelaAberta(nomeTela);
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao criar item"+ex.getMessage());
        }
    }

    public void cancelar() {
        GerenciadorTelas.getInstancia().removeTelaAberta(nomeTela);
    }

    public void verItem(Item item){
        
        JOptionPane.showMessageDialog(view, "Ver detalhes do item: " + item.getTipo());
    }

    public void func2(Item item){
        if(tipoTela.equals("Vendedor")){
            JOptionPane.showMessageDialog(view, "Editar item: " + item.getTipo());
        } else {
            JOptionPane.showMessageDialog(view, "Comprar item: " + item.getTipo());
        }
    }
}
