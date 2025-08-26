/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presenter;

import command.Oferta.AbrirPublicarOfertaCommand;
import command.item.AbrirCadastroItemCommand;
import command.item.AbrirVerItemCommand;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.swing.BoxLayout;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListCellRenderer;
import javax.swing.table.DefaultTableModel;
import model.Defeito;
import model.Item;
import model.Material;
import model.Oferta;
import model.Perfil;
import model.Vendedor;
import service.ItemService;
import service.PerfilService;
import view.item.FormItemView;
import view.item.MaterialComposicao;
import view.item.ShowItemView;
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

    public void createItem(PerfilService perfilService, Optional<Item> itemEditavel) throws SQLException{
        this.perfilService = perfilService;
        FormItemView formView = new FormItemView();
        tipoTela = "Vendedor";
        nomeTela = "CriarItem";
        formView.setTitle("Cadastro item");

        if(!itemEditavel.isEmpty()){
            nomeTela = "EditarItem";
            formView.setTitle("Editar Item");
            Item item= itemEditavel.get();
            
            formView.getTxtSubcategoria().setText(item.getSubcategoria());
            formView.getSTamanho().setValue(item.getTamanho());
            formView.getTxtPeso().setText(String.valueOf(item.getPeso()));
            formView.getTxtCor().setText(item.getCor());
            formView.getTxtPrecoBase().setText(String.valueOf(item.getPrecoBase()));
        }
        
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

                List<Material> materiais = itemService.getListaMateriaisComposicao();
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

    public void showItens(List<Item> listaExistente) throws Exception {
        tipoTela = "Comprador";
        nomeTela = "VerItens";
        ShowItensView itensView = new ShowItensView();
        itensView.setVisible(false);

        resetButtonActions(itensView.getBtnFechar());
        itensView.getBtnFechar().addActionListener(e -> {
            try {
                cancelar();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(itensView, ex);
            }
        });

        List<Item> itens = itemService.getItens();
        if (!listaExistente.isEmpty()) {
            itens = listaExistente;
            tipoTela = "Vendedor";
        }

        DefaultTableModel model = new DefaultTableModel(
                new Object[]{"Tipo", "Subcategoria", "Tamanho", "Cor", "Preço Final", "ID Vendedor", "Ações"},
                0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 6;
            }
        };

        JTable tabelaItens = itensView.getTbItens();
        tabelaItens.setModel(model);
        for (Item item : itens) {
            JButton btnVer = new JButton("V");
            btnVer.setMargin(new Insets(2, 6, 2, 6));
            btnVer.setPreferredSize(new Dimension(40, 25));
            resetButtonActions(btnVer);

            btnVer.addActionListener(ev -> {
                try {
                    verItem(item);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(itensView, ex);
                }
            });
            model.addRow(new Object[]{
                    item.getTipo(),
                    item.getSubcategoria(),
                    item.getTamanho(),
                    item.getCor(),
                    item.getPrecoFinal(),
                    item.getVendedor().getSistemId(),
                    btnVer
            });
        }

        tabelaItens.getColumn("Ações").setCellRenderer((table, value, isSelected, hasFocus, row, column) -> {
            if (value instanceof JButton) {
                return (JButton) value;
            }
            return new JLabel(value == null ? "" : value.toString());
        });

        tabelaItens.getColumn("Ações").setCellEditor(new DefaultCellEditor(new JCheckBox()) {
            private JButton currentButton;

            @Override
            public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
                if (value instanceof JButton) {
                    currentButton = (JButton) value;
                    return currentButton;
                }
                return null;
            }

            @Override
            public Object getCellEditorValue() {
                return currentButton;
            }
        });

        itensView.getSpItens().setViewportView(tabelaItens);
        
        itensView.setVisible(true);
        this.view = itensView;
    }

    public void showItem(Item item) throws SQLException{
        ShowItemView itemView = new ShowItemView();
        tipoTela = "Comprador";
        itemView.getBtnExcluir().setVisible(false);
        if(perfil.getSistemId().startsWith("V")){
            tipoTela="Vendedor";
            
            itemView.getBtnExcluir().setVisible(true);
        }
        
        
        itemView.setVisible(false);
        itemView.setTitle("item - "+item.getIdC());
        
        itemView.getTxtIdCItem().setText(item.getIdC());
        itemView.getTxtTipoItem().setText(item.getTipo());
        itemView.getTxtSubcategoriaItem().setText(item.getSubcategoria());
        itemView.getTxtTamanhoItem().setText(item.getTamanho());
        itemView.getTxtPesoItem().setText(String.valueOf(item.getPeso())+" kg");
        itemView.getTxtCorItem().setText(item.getCor());
        itemView.getTxtPrecoItem().setText("R$ "+item.getPrecoFinal());
        itemView.getTxtVendedorDados().setText(item.getVendedor().getSistemId() + " " + item.getVendedor().getReputacao().getNivel());
    
        List<Material> materiais = item.getComposicao();
        DefaultListModel<JLabel> modelMaterial = new DefaultListModel<>();

        for(Material material : materiais){
            JLabel lblMaterial = new JLabel();
            lblMaterial.setText(material.getTipo() + " " + material.getPercentualItem()*100 +"%");
            modelMaterial.addElement(lblMaterial);
        }

        JList<JLabel> listaMateriais = itemView.getLComposicao();            
        listaMateriais.setModel(modelMaterial);
        listaMateriais.setCellRenderer((JList<? extends JLabel> list, JLabel value, int index, boolean isSelected, boolean cellHasFocus) -> {
            value.setBackground(isSelected ? list.getSelectionBackground() : list.getBackground());
            value.setForeground(isSelected ? list.getSelectionForeground() : list.getForeground());
            value.setEnabled(list.isEnabled());
            value.setFont(list.getFont());
            return value;
        });
        itemView.getSpComposicaoItem().setViewportView(listaMateriais);
        
        
        List<Defeito> defeitos = item.getDefeitos();
        DefaultListModel<JLabel> modelDefeito = new DefaultListModel<>();

        for(Defeito defeito : defeitos){
            JLabel lblDefeito = new JLabel();
            lblDefeito.setText(defeito.getDescricao() + " R$ -" + defeito.getValorDesconto());
            modelDefeito.addElement(lblDefeito);
        }

        JList<JLabel> listaDefeitos = itemView.getlTiposDefeito();
        listaDefeitos.setModel(modelDefeito);
        listaDefeitos.setCellRenderer((JList<? extends JLabel> list, JLabel value, int index, boolean isSelected, boolean cellHasFocus) -> {
            value.setBackground(isSelected ? list.getSelectionBackground() : list.getBackground());
            value.setForeground(isSelected ? list.getSelectionForeground() : list.getForeground());
            value.setEnabled(list.isEnabled());
            value.setFont(list.getFont());
            return value;
        });

        itemView.getSpDefeitos().setViewportView(listaDefeitos);
        
        List<Oferta> ofertas = itemService.getOfertasPorItem(item);

        DefaultTableModel model = new DefaultTableModel(
                new Object[]{"Comprador", "Preço", "Data Oferta", "Status", "", ""},
                0
        );

        JTable tabelaOfertas = itemView.gettOfertas();
        tabelaOfertas.setModel(model);
        
        for (Oferta oferta : ofertas) {
            JButton btnAceitar = new JButton("Aceitar");
            btnAceitar.setMargin(new Insets(2, 6, 2, 6));
            btnAceitar.setPreferredSize(new Dimension(40, 25));
            resetButtonActions(btnAceitar);

            btnAceitar.addActionListener(ev -> {
                try {
                    aceitarOferta(oferta, item);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(itemView, ex);
                }
            });

            JButton btnRejeitar = new JButton("Rejeitar");
            btnRejeitar.setMargin(new Insets(2, 6, 2, 6));
            btnRejeitar.setPreferredSize(new Dimension(40, 25));
            resetButtonActions(btnRejeitar);

            btnRejeitar.addActionListener(ev -> {
                try {
                    rejeitarOferta(oferta);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(itemView, ex);
                }
            });
            
            model.addRow(new Object[]{
                oferta.getComprador().getSistemId(),
                oferta.getValor(),
                oferta.getData(),
                oferta.getStatus(),
                btnAceitar,
                btnRejeitar
            });
            
            tabelaOfertas.getColumnModel().getColumn(4).setCellRenderer((table, value, isSelected, hasFocus, row, column) -> {
                return (value instanceof JButton) ? (JButton) value : new JLabel(value == null ? "" : value.toString());
            });
            tabelaOfertas.getColumnModel().getColumn(4).setCellEditor(new DefaultCellEditor(new JCheckBox()) {
                JButton btn;
                {
                    setClickCountToStart(1);
                }
                @Override
                public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
                    if (value instanceof JButton) {
                        btn = (JButton) value;
                        return btn;
                    }
                    return super.getTableCellEditorComponent(table, value, isSelected, row, column);
                }
                @Override
                public Object getCellEditorValue() {
                    return btn;
                }
            });

            tabelaOfertas.getColumnModel().getColumn(5).setCellRenderer((table, value, isSelected, hasFocus, row, column) -> {
                return (value instanceof JButton) ? (JButton) value : new JLabel(value == null ? "" : value.toString());
            });
            tabelaOfertas.getColumnModel().getColumn(5).setCellEditor(new DefaultCellEditor(new JCheckBox()) {
                JButton btn;
                {
                    setClickCountToStart(1);
                }
                @Override
                public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
                    if (value instanceof JButton) {
                        btn = (JButton) value;
                        return btn;
                    }
                    return super.getTableCellEditorComponent(table, value, isSelected, row, column);
                }
                @Override
                public Object getCellEditorValue() {
                    return btn;
                }
            });

        }

        itemView.getSpOfertas().setViewportView(tabelaOfertas);
        
        resetButtonActions(itemView.getBtnCancelar());
        itemView.getBtnCancelar().addActionListener(e -> {
            try {
                cancelar("VerItem");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(itemView, ex);
            }
        });
        
        resetButtonActions(itemView.getBtnExcluir());
        itemView.getBtnExcluir().addActionListener(e -> {
            try {
                excluir(item);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(itemView, ex);
            }
        });
        
        resetButtonActions(itemView.getBtnFunc());
        itemView.getBtnFunc().addActionListener(e -> {
            try {
                if(perfil.getSistemId().startsWith("C")){
                    ofertar(item);
                    itemView.getBtnFunc().setText("Oferta");
                }else{
                    editar(item);
                    itemView.getBtnFunc().setText("Editar");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(itemView, ex);
            }
        });
        
        itemView.setVisible(true);
        this.view = itemView;
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
            if(nomeTela.equals("EditarItem")){
                itemService.editar(item, defeitos, (Vendedor)perfil);
            }else{
                itemService.criar(item, defeitos, (Vendedor)perfil);  
            }
            perfilService.completarPerfil(perfil);
            perfilService.atualizarReputacao(perfil, "Cadastro de item completo");
            GerenciadorTelas.getInstancia().removeTelaAberta(nomeTela);
            verItem(item);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, ex);
        }
    }

    public void cancelar() {
        GerenciadorTelas.getInstancia().removeTelaAberta(nomeTela);
    }
    
    public void cancelar(String nometela) {
        GerenciadorTelas.getInstancia().removeTelaAberta(nometela);
    }

    public void verItem(Item item) throws SQLException{
        new AbrirVerItemCommand(perfil,item).executar();
    }
    
    private void aceitarOferta(Oferta oferta, Item item){
        oferta.setAceito();
        for(Oferta o : item.getOfertas()){
            if(!o.equals(oferta)){
                o.setNegado();
            }
        }
        //implementar transação
    }    
    
    private void rejeitarOferta(Oferta oferta){
        oferta.setNegado();
    }
    
    private void ofertar(Item item) throws SQLException{
        new AbrirPublicarOfertaCommand(perfil,item).executar();
    }
    
    private void editar(Item item) throws SQLException{
        new AbrirCadastroItemCommand(perfil,Optional.of(item)).executar();

        System.out.println("Editar");
    }
    
    private void excluir(Item item){
        itemService.excluir(item);
    }
}
