/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package factory.dao;

import dao.ICondutaDAO;
import dao.IDefeitoDAO;
import dao.IDenunciaDAO;
import dao.IItemDAO;
import dao.IMaterialDAO;
import dao.IPerfilDAO;
import dao.IPerfilSolicitacaoDAO;
import dao.IReputacaoDAO;
import dao.IUsuarioDAO;
import dao.h2.CondutaDAOH2;
import dao.h2.DefeitoDAOH2;
import dao.h2.DenunciaDAOH2;
import dao.h2.ItemDAOH2;
import dao.h2.MaterialDAOH2;
import dao.h2.PerfilCompradorDAOH2;
import dao.h2.PerfilSolicitacaoDAOH2;
import dao.h2.PerfilVendedorDAOH2;
import dao.h2.ReputacaoDAOH2;
import dao.h2.UsuarioDAOH2;

/**
 *
 * @author berna
 */
public class H2DAOFactory extends IDAOFactory {
    @Override
    public ICondutaDAO getCondutaDAO() {
        return new CondutaDAOH2();
    }
    
    @Override
    public IItemDAO getItemDAO() {
        return new ItemDAOH2();
    }

    @Override
    public IDenunciaDAO getDenunciaDAO() {
        return new DenunciaDAOH2();
    }

    @Override
    public IPerfilDAO getPerfilCompradorDAO() {
        return new PerfilCompradorDAOH2();
    }

    @Override
    public IPerfilSolicitacaoDAO getPerfilSolicitacaoDAO() {
        return new PerfilSolicitacaoDAOH2();
    }

    @Override
    public IPerfilDAO getPerfilVendedorDAO() {
        return new PerfilVendedorDAOH2();
    }

    @Override
    public IUsuarioDAO getUsuarioDAO() {
        return new UsuarioDAOH2();
    }

    @Override
    public IReputacaoDAO getReputacaoDAO() {
        return new ReputacaoDAOH2();
    }

    @Override
    public IDefeitoDAO getDefeitoDAO() {
        return new DefeitoDAOH2();
    }

    @Override
    public IMaterialDAO getMaterialDAO() {
        return new MaterialDAOH2();
    }
}
