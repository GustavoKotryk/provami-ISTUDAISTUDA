package senai.service.equipamento;

import senai.model.Equipamento;

import java.sql.SQLException;
import java.util.List;

public class EquipamentoServiceImpl implements EquipamentoService{
    @Override
    public Equipamento criarEquipamento(Equipamento equipamento) throws SQLException {
        return null;
    }

    @Override
    public Equipamento buscarPorId(int id) throws SQLException {
        return null;
    }

    @Override
    public List<Equipamento> buscarPorFornecedorId(int fornecedorId) throws SQLException {
        return List.of();
    }

    @Override
    public void atualizarEquipamento(Equipamento equipamento) throws SQLException {

    }

    @Override
    public void deletarEquipamento(int id) throws SQLException {

    }
}
