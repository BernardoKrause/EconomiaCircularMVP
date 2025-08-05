package service;

import model.Item;
import strategy.defectSystem.IRegraDefeitoStrategy;
import strategy.defectSystem.RegraDefeitosPercentual;

import java.util.ArrayList;
import java.util.List;

public class DefectSystemService {
    private static List<IRegraDefeitoStrategy> regrasDefeito;
    private static DefectSystemService defectSystemService;

    private DefectSystemService() {
        regrasDefeito = new ArrayList<IRegraDefeitoStrategy>();
        regrasDefeito.add(new RegraDefeitosPercentual());
    }

    public DefectSystemService getInstance() {
        if(defectSystemService == null) {
            defectSystemService = new DefectSystemService();
        }
        return defectSystemService;
    }

    public static void AplicarDefeitos(Item item, List<String> defeitos) {
        for(IRegraDefeitoStrategy regra : regrasDefeito){
            regra.AplicarDefeitos(item, defeitos);
        }
    }
}
