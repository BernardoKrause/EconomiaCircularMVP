package repository;

import java.util.*;

public class TiposDefeitoRepository {
    private Map<String,Map<String,Double>> tiposDefeito;

    public TiposDefeitoRepository() {
        Map<String,Double> tiposDefeitoVestuario = new HashMap<>();
            tiposDefeitoVestuario.put("rasgo estruturante",0.3);
            tiposDefeitoVestuario.put("Ausência de botão principal",0.15);
            tiposDefeitoVestuario.put("ziper parcialmente funcional",0.15);
            tiposDefeitoVestuario.put("mancha permanente",0.20);
            tiposDefeitoVestuario.put("desgaste por pilling acentuado",0.10);

        Map<String,Double> tiposDefeitoCalcado = new HashMap<>();
            tiposDefeitoCalcado.put("sola sem relevo funcional",0.25);
            tiposDefeitoCalcado.put("descolamento parcial de entressola",0.20);
            tiposDefeitoCalcado.put("arranhões profundos",0.15);
            tiposDefeitoCalcado.put("palmilha original ausente",0.10);
            tiposDefeitoCalcado.put("odor persistente leve",0.10);

        Map<String,Double> tiposDefeitoBolsa = new HashMap<>();
            tiposDefeitoBolsa.put("alça reparada",0.2);
            tiposDefeitoBolsa.put("fecho defeituoso",0.20);
            tiposDefeitoBolsa.put("desbotamento extenso",0.15);
            tiposDefeitoBolsa.put("forro rasgado",0.15);

        Map<String,Double> tiposDefeitoBijuteria = new HashMap<>();
            tiposDefeitoBijuteria.put("oxidação visível",0.2);
            tiposDefeitoBijuteria.put("pedra ausente",0.15);
            tiposDefeitoBijuteria.put("fecho frouxo",0.10);

        tiposDefeito = new HashMap<>();
        tiposDefeito.put("vestuario",tiposDefeitoVestuario);
        tiposDefeito.put("calcado",tiposDefeitoCalcado);
        tiposDefeito.put("bolsas e mochilas",tiposDefeitoBolsa);
        tiposDefeito.put("bijuterias e acessorios",new HashMap<>());
    }

    public Optional<List<String>> getTiposDefeito(String tipoItem) {
        if(!tiposDefeito.containsKey(tipoItem)){
            return Optional.empty();
        }
        List<String> listaTipos = new ArrayList<>();
        tiposDefeito.get(tipoItem).keySet().forEach(key -> {
            listaTipos.add(key);
        });
        return Optional.of(listaTipos);
    }

    public Double getValor(String tipoItem,String defeito) {
        if(!tiposDefeito.containsKey(tipoItem)){
            throw new IllegalArgumentException("O tipo informado não existe!");
        }
        if(!tiposDefeito.get(tipoItem).containsKey(defeito)){
            throw new IllegalArgumentException("O defeito informado não existe!");
        }
        return tiposDefeito.get(tipoItem).get(defeito);
    }
}
