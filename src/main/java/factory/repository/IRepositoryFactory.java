/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package factory.repository;

import repository.ICondutaRepository;
import repository.IDefeitosTipoRepository;
import repository.IDenunciaRepository;
import repository.IItemRepository;
import repository.IReputacaoRepository;
import repository.IUsuarioRepository;
import repository.IPerfilRepository;

/**
 *
 * @author caiof
 */
public interface IRepositoryFactory {
    IUsuarioRepository criarUsuarioRepository();
    IPerfilRepository criarPerfilVendedorRepository();
    IItemRepository criarItemRepository();
    IDenunciaRepository criarDenunciaRepository();
    IDefeitosTipoRepository criarDefeitosTipoRepository();
    IReputacaoRepository criarReputacaoRepository();
    ICondutaRepository criarCondutaRepository();
}
