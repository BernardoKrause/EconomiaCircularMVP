package strategy.defectSystem;

import model.Defeito;
import model.Item;
import repository.TiposDefeitoRepository;

import java.util.List;
import java.util.Map;

public class RegraDefeitosPercentual implements IRegraDefeitoStrategy{
    private Map<String,Double> descontoDefeitoPercentual;
    private TiposDefeitoRepository tiposDefeitoRepository;

    public RegraDefeitosPercentual(){
        this.tiposDefeitoRepository = TiposDefeitoRepository.getInstance();
    }

    @Override
    public void AplicarDefeitos(Item item, List<String> defeitosAplicados) {
        this.descontoDefeitoPercentual = tiposDefeitoRepository.getMapTipoDefeitos(item.getTipo());
        for(String tipoDefeito : defeitosAplicados){
            if(!seAplica(tipoDefeito)){
                throw new RuntimeException("Defeito passado não encontrado!");
            }
            Defeito defeito = new Defeito(tipoDefeito,(int) (descontoDefeitoPercentual.get(tipoDefeito)*100));
            defeito.setValorDesconto(item.getPrecoBase()*descontoDefeitoPercentual.get(tipoDefeito));
            item.addDefeito(defeito);
        }
    }

    private boolean seAplica(String defeito) {
        return descontoDefeitoPercentual.containsKey(defeito);
    }
}
