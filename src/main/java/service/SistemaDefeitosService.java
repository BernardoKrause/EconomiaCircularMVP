package service;

import model.Item;
import strategy.SistemaDefeitos.IRegraDefeitoStrategy;
import strategy.SistemaDefeitos.RegraDefeitosPercentual;

import java.util.ArrayList;
import java.util.List;

public class SistemaDefeitosService {
    private List<IRegraDefeitoStrategy> regrasDefeito;
    private SistemaDefeitosService sistemaDefeitosService;

    public SistemaDefeitosService() {
        regrasDefeito = new ArrayList<IRegraDefeitoStrategy>();
        regrasDefeito.add(new RegraDefeitosPercentual());
    }

    public void AplicarDefeitos(Item item, List<String> defeitos) {
        for(IRegraDefeitoStrategy regra : regrasDefeito){
            regra.AplicarDefeitos(item, defeitos);
        }
    }
}
