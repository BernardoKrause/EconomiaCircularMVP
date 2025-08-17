package strategy.SistemaDefeitos;

import factory.repository.SeletorRepositoryFactory;
import java.sql.SQLException;
import model.Defeito;
import model.Item;
import repository.teste.DefeitosTipoRepositoryTeste;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import repository.IDefeitosTipoRepository;

public class RegraDefeitosPercentual implements IRegraDefeitoStrategy{
    private IDefeitosTipoRepository tiposDefeitoRepository;

    public RegraDefeitosPercentual(){
        this.tiposDefeitoRepository = SeletorRepositoryFactory.obterInstancia().criarDefeitosTipoRepository();
    }

    @Override
    public void AplicarDefeitos(Item item, List<String> defeitosAplicados) {
        for(String defeitoAnalizado : defeitosAplicados){
            try {
                if(!seAplica(item.getTipo(),defeitoAnalizado)){
                    throw new RuntimeException("Defeito passado não encontrado!");
                }
                Double percentual = tiposDefeitoRepository.getPercentualPorDefeito(defeitoAnalizado);
                Defeito defeito = new Defeito(defeitoAnalizado,(int) (percentual*100));
                defeito.setValorDesconto(item.getPrecoBase()*percentual);
                item.addDefeito(defeito);
            } catch (SQLException ex) {
                Logger.getLogger(RegraDefeitosPercentual.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private boolean seAplica(String tipo, String defeito) throws SQLException {
        List<String> defeitosExistentes = tiposDefeitoRepository.BuscarPorTipo(tipo).get();
        for(String d : defeitosExistentes){
            if(d.equalsIgnoreCase(defeito)){
                return true;
            }
        }
        return false;
    }
}
