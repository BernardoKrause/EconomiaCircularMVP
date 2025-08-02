/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author berna
 */
public class LoginView extends javax.swing.JFrame {
    private JLabel lblEmail;
    private JLabel lblSenha;
    private JTextField txtEmail;
    private JTextField txtSenha;
    private JButton btnAcessar;
    private JButton btnCancelar;
    
    public LoginView() {
        initComponents();
    }
    
    public JButton getBtnAcessar() {
        return btnAcessar;
    }
    
    public JButton getBtnCancelar() {
        return btnCancelar;
    }
    
    public JTextField getTxtEmail() {
        return txtEmail;
    }
    
    public JTextField getTxtSenha() {
        return txtSenha;
    }
    
    
    @SuppressWarnings("unchecked")
    private void initComponents() {
        
        lblEmail = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        
        lblSenha = new javax.swing.JLabel();
        txtSenha = new javax.swing.JTextField();
        
        btnAcessar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        
        // oq essa linha faz?
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Login");
        
        lblEmail.setText("E-mail");
        lblSenha.setText("Senha");
        
        btnAcessar.setText("Acessar");
        btnCancelar.setText("Cancelar");
        
        // oq isso faz?
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblSenha)
                            .addComponent(lblEmail))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtEmail)
                            .addComponent(txtSenha, javax.swing.GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(103, 103, 103)
                        .addComponent(btnAcessar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCancelar)))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEmail)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSenha)
                    .addComponent(txtSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAcessar)
                    .addComponent(btnCancelar))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        
        pack();
    }
}
