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
import model.Item;
import model.Perfil;
import model.Oferta;
import service.OfertaService;
import service.PerfilService;
import view.item.FormOfertaView;

/**
 *
 * @author caiof
 */
public class OfertaPresenter extends AbstractPresenter{
    private PerfilService perfilService;
    private OfertaService ofertaService;
    private String nomeTela;
    private Perfil perfil;
    private Item item;
    
    public OfertaPresenter(Item item, Comprador comprador, OfertaService ofertaService){
        this.item=item;
        this.perfil=comprador;
        this.ofertaService=ofertaService;
    }
    
    public void createOferta(PerfilService perfilService){
        FormOfertaView ofertaView = new FormOfertaView();
        ofertaView.setVisible(false);
        
        nomeTela = "CriarOferta";
        
        resetButtonActions(ofertaView.getBtnOfertar());
        resetButtonActions(ofertaView.getBtnCancelar());
        
        ofertaView.getTxtItem().setText(item.getIdC());
        ofertaView.getTxtComprador().setText(perfil.getSistemId());
        
        ofertaView.getBtnOfertar().addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try{
                    ofertar();
                }catch (Exception ex){
                    JOptionPane.showMessageDialog(ofertaView, "Erro ao Atualizar Ofertar "+ex);
                }
            }
        });
        
        ofertaView.getBtnCancelar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    cancelar();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(ofertaView, ex);
                }
           }
        });
        
        ofertaView.setVisible(true);
        this.view=ofertaView;
    }
    
    public void ofertar() throws SQLException{
        FormOfertaView ofertaView = (FormOfertaView)view;
        Double valor = Double.valueOf(ofertaView.getTxtValor().getText());
        
        Oferta oferta = new Oferta(item,(Comprador)perfil,valor);
                
        try{
            ofertaService.criar(oferta);
            if(valor<=item.getPrecoFinal()*0.99 && valor>=item.getPrecoFinal()*0.80){
                perfilService.atualizarReputacao(perfil, "Oferta dentro do intervalo permitido");
            }
        }catch(Exception ex){
            JOptionPane.showMessageDialog(ofertaView, ex);
        }
    }
    
    public void cancelar() {
        GerenciadorTelas.getInstancia().removeTelaAberta(nomeTela);
    }
}
