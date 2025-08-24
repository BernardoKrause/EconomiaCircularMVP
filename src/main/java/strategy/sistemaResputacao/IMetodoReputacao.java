/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package strategy.sistemaResputacao;

import model.Perfil;

/**
 *
 * @author caiof
 */
public interface IMetodoReputacao {
    void aplicarReputacao(Perfil perfil);
    boolean seAplica(Perfil perfil);
    String getTipoConduta();
    Integer getRepeticoes();
}
