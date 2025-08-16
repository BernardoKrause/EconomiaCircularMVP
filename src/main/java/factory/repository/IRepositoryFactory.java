/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package factory.repository;

import repository.IDefeitosTipoRepository;
import repository.IDenunciaRepository;
import repository.IItemRepository;
import repository.IPerfilSolicitacaoRepository;
import repository.IPerfilVendedorRepository;
import repository.IUsuarioRepository;

/**
 *
 * @author caiof
 */
public interface IRepositoryFactory {
    IUsuarioRepository criarUsuarioRepository();
    IPerfilVendedorRepository criarPerfilVendedorRepository();
    IPerfilSolicitacaoRepository criarPerfilSolicitacaoRepository();
    IItemRepository criarItemRepository();
    IDenunciaRepository criarDenunciaRepository();
    IDefeitosTipoRepository criarDefeitosTipoRepository();
}
