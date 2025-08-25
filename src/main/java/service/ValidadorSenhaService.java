/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import com.pss.senha.validacao.ValidadorSenha;

/**
 *
 * @author berna
 */
public class ValidadorSenhaService {
    private final ValidadorSenha validador;
    
    public ValidadorSenhaService() {
        this.validador = new ValidadorSenha();
    }
    
    public boolean valida(String senha) throws Exception {
        var validacoes = validador.validar(senha);
        
        if (!validacoes.isEmpty()) {
            String mensagemErro = String.join("\n", validacoes);
            throw new Exception(mensagemErro);
        }  else {
            return true;
        }
    }
}
