package strategy.SistemaDefeitos;

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
        this.tiposDefeitoRepository = new DefeitosTipoRepositoryTeste();
    }

    @Override
    public void AplicarDefeitos(Item item, List<String> defeitosAplicados) {
        for(String tipoDefeito : defeitosAplicados){
            try {
                if(!seAplica(tipoDefeito)){
                    throw new RuntimeException("Defeito passado não encontrado!");
                }
                Double percentual = tiposDefeitoRepository.getPercentualPorDefeito(tipoDefeito);
                Defeito defeito = new Defeito(tipoDefeito,(int) (percentual*100));
                defeito.setValorDesconto(item.getPrecoBase()*percentual);
                item.addDefeito(defeito);
            } catch (SQLException ex) {
                Logger.getLogger(RegraDefeitosPercentual.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private boolean seAplica(String defeito) throws SQLException {
        for(String defeitoExistente : tiposDefeitoRepository.getTiposItem().get()){
            return(defeitoExistente.equalsIgnoreCase(defeito));
        }
        return false;
    }
}
