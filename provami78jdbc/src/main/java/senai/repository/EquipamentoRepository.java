package senai.repository;

import senai.model.Equipamento;
import java.util.List;

public interface EquipamentoRepository {
    Equipamento save(Equipamento equipamento);
    Equipamento findById(int id);
    List<Equipamento> findByEquipamentoID(int equipamentoId);
    void update (Equipamento equipamento);
    void delete (int id);
}
