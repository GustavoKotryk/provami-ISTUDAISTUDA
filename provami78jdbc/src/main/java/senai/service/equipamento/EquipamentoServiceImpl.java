package senai.service.equipamento;

import senai.model.Equipamento;
import senai.repository.EquipamentoRepository;
import senai.repository.FornecedorRepository;

import java.sql.*;
import java.util.List;

public class EquipamentoServiceImpl implements EquipamentoService{

    private final EquipamentoRepository equipamentoRepository;
    private final FornecedorRepository fornecedorRepository;

    public EquipamentoServiceImpl(EquipamentoRepository equipamentoRepository, FornecedorRepository fornecedorRepository) {
        this.equipamentoRepository = equipamentoRepository;
        this.fornecedorRepository = fornecedorRepository;
    }

    @Override
    public Equipamento criarEquipamento(Equipamento equipamento) throws SQLException {

        if(fornecedorRepository.buscarPorId(equipamento.getFornecedorId()) == null){
            throw new RuntimeException("Fornecedor inválido ou inexistente!");
        }
       try{
            return equipamentoRepository.save(equipamento);
        } catch (RuntimeException e){
            throw new SQLException("Erro ao criar fornecedor: "+e.getMessage(),e);
        }
    }


    @Override
    public Equipamento buscarPorId(int id) throws SQLException {
        Equipamento equipamento = equipamentoRepository.findById(id);

        if(equipamento == null){
            throw new RuntimeException(("Não foi possivel encontrar o ID do equipamento!"));
        }
        return equipamento;
    }

    @Override
    public List<Equipamento> buscarPorFornecedorId(int fornecedorId) throws SQLException {

        return equipamentoRepository.findByEquipamentoID(fornecedorId);
    }

    @Override
    public void atualizarEquipamento(Equipamento equipamento) throws SQLException {



        equipamentoRepository.update(equipamento);
    }

    @Override
    public void deletarEquipamento(int id) throws SQLException {
        equipamentoRepository.delete(id);
    }
}
