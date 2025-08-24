/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package strategy.sistemaResputacao;

import java.util.Optional;
import model.Conduta;
import model.Perfil;

/**
 *
 * @author caiof
 */
public interface IMetodoReputacao {
    Optional<Conduta> aplicarReputacao(Perfil perfil);
    boolean seAplica(Perfil perfil);
    String getTipoConduta();
    Integer getRepeticoes();
}
