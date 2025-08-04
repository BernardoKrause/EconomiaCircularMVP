package strategy.defectSystem;

import model.Item;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RegraDefeitosVestuario implements IRegraDefeitoStrategy{
    private Map<String,Double> descontoDefeitoVestuario;

    public RegraDefeitosVestuario() {

    }

    @Override
    public void calcularDefeitos(Item item) {

    }

    @Override
    public boolean seAplica(Item item, List<String> defeitosAplicados) {
        for(String defeito : defeitosAplicados){

        }
    }
}
