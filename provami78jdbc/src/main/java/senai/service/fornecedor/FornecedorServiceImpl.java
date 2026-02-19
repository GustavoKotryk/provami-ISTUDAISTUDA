package senai.service.fornecedor;

import senai.model.Fornecedor;
import senai.repository.FornecedorRepository;
import senai.repository.FornecedorRepositoryImpl;

import java.sql.SQLException;
import java.util.List;

public class FornecedorServiceImpl implements FornecedorService{

    private final FornecedorRepository repository;

    public FornecedorServiceImpl(){
        this.repository = new FornecedorRepositoryImpl();
    }
    @Override
    public Fornecedor criarFornecedor(Fornecedor fornecedor) throws SQLException {
        try{
            return repository.save(fornecedor);
        } catch (RuntimeException e){
            throw new SQLException("Erro ao criar fornecedor: " + e.getMessage(), e);
        }
    }

    @Override
    public Fornecedor buscarPorId(int id) {

        Fornecedor fornecedor = repository.buscarPorId(id);

        if(fornecedor == null){
            throw new RuntimeException("Id do Fornecedor não encontrado!");
        }

        return fornecedor;
    }

    @Override
    public List<Fornecedor> buscarTodos() throws SQLException {
        try{
            return repository.findAll();
        } catch (RuntimeException e){
            throw new SQLException("Erro ao buscar fornecedor " + e.getMessage(), e);
        }
    }

    @Override
    public void atualizarFornecedor(Fornecedor fornecedor) throws SQLException {

        if (repository.buscarPorId(fornecedor.getId()) == null){
            throw new RuntimeException("Id do fornecedor não encontrado!");
        }

        repository.update(fornecedor);
    }

    @Override
    public void deletarFornecedor(int id) throws SQLException {

        try {
            repository.delete(id);
        } catch (RuntimeException e){
            throw new RuntimeException("Erro ao deletar Fornecedor!");
        }

    }
}
