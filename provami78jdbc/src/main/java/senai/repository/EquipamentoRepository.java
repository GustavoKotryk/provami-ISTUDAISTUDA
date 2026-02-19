package senai.repository;

import senai.model.Equipamento;

import java.sql.SQLException;
import java.util.List;

public interface EquipamentoRepository {
    Equipamento save(Equipamento equipamento);
    Equipamento findById(int id);
    List<Equipamento> findByEquipamentoID(int equipamentoId) throws SQLException;
    void update (Equipamento equipamento);
    void delete (int id);
}
