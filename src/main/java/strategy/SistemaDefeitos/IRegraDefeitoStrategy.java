package strategy.SistemaDefeitos;

import model.Item;
import java.util.List;

public interface IRegraDefeitoStrategy {
    void AplicarDefeitos(Item item, List<String> defeitosAplicados);
}
