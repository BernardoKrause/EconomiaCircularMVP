/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package factory.repository;

import repository.ICondutaRepository;
import repository.IDefeitosTipoRepository;
import repository.IDenunciaRepository;
import repository.IItemRepository;
import repository.IPerfilSolicitacaoRepository;
import repository.IPerfilVendedorRepository;
import repository.IReputacaoRepository;
import repository.IUsuarioRepository;
import repository.teste.CondutaRepositoryTeste;
import repository.teste.DefeitosTipoRepositoryTeste;
import repository.teste.DenunciaRepositoryTeste;
import repository.teste.ItemRepositoryTeste;
import repository.teste.PerfilSolicitacaoRepositoryTeste;
import repository.teste.PerfilVendedorRepositoryTeste;
import repository.teste.ReputacaoRepositoryTeste;
import repository.teste.UsuarioRepositoryTeste;

/**
 *
 * @author caiof
 */
public class RepositoryFactoryTeste implements IRepositoryFactory{

    @Override
    public IUsuarioRepository criarUsuarioRepository() {
        return UsuarioRepositoryTeste.getintancia();
    }

    @Override
    public IPerfilVendedorRepository criarPerfilVendedorRepository() {
        return PerfilVendedorRepositoryTeste.getInstancia();
    }

    @Override
    public IPerfilSolicitacaoRepository criarPerfilSolicitacaoRepository() {
        return PerfilSolicitacaoRepositoryTeste.getInstancia();
    }

    @Override
    public IItemRepository criarItemRepository() {
        return ItemRepositoryTeste.getInstancia();
    }

    @Override
    public IDenunciaRepository criarDenunciaRepository() {
        return DenunciaRepositoryTeste.getInstancia();
    }

    @Override
    public IDefeitosTipoRepository criarDefeitosTipoRepository() {
        return DefeitosTipoRepositoryTeste.getInstancia();
    }

    @Override
    public IReputacaoRepository criarReputacaoRepository() {
        return ReputacaoRepositoryTeste.getInstancia();
    }

    @Override
    public ICondutaRepository criarCondutarRepository() {
        return CondutaRepositoryTeste.getInstancia();
    }
}

