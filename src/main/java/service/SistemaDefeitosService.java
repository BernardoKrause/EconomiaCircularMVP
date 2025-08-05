package service;

import model.Item;
import strategy.SistemaDefeitos.IRegraDefeitoStrategy;
import strategy.SistemaDefeitos.RegraDefeitosPercentual;

import java.util.ArrayList;
import java.util.List;

public class SistemaDefeitosService {
    private static List<IRegraDefeitoStrategy> regrasDefeito;
    private static SistemaDefeitosService sistemaDefeitosService;

    private SistemaDefeitosService() {
        regrasDefeito = new ArrayList<IRegraDefeitoStrategy>();
        regrasDefeito.add(new RegraDefeitosPercentual());
    }

    public SistemaDefeitosService getInstance() {
        if(sistemaDefeitosService == null) {
            sistemaDefeitosService = new SistemaDefeitosService();
        }
        return sistemaDefeitosService;
    }

    public static void AplicarDefeitos(Item item, List<String> defeitos) {
        for(IRegraDefeitoStrategy regra : regrasDefeito){
            regra.AplicarDefeitos(item, defeitos);
        }
    }
}
