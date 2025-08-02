package strategy.defectSystem;

import model.Item;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RegraDefeitosVestuario implements IRegraDefeitoStrategy{
    private Map<String,Double> descontoDefeitoVestuario;

    public RegraDefeitosVestuario() {
        descontoDefeitoVestuario = new HashMap<>();
        descontoDefeitoVestuario.put("rasgo estruturante",0.3);
        descontoDefeitoVestuario.put("Ausência de botão principal",0.15);
        descontoDefeitoVestuario.put("ziper parcialmente funcional",0.15);
        descontoDefeitoVestuario.put("mancha permanente",0.20);
        descontoDefeitoVestuario.put("desgaste por pilling acentuado",0.10);
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
