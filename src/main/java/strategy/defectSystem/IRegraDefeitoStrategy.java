package strategy.defectSystem;

import model.Item;
import java.util.List;

public interface IRegraDefeitoStrategy {
    public void calcularDefeitos(Item item);
    public boolean seAplica(Item item,List<String> defeitosAplicados);
}
