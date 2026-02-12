package senai.repository;

import senai.model.Fornecedor;
import java.util.List;

public interface FornecedorRepository {
    Fornecedor save(Fornecedor fornecedor);
    Fornecedor buscarPorId(int id);
    List<Fornecedor> findAll();
    void update(Fornecedor fornecedor);
    void delete(int id);
}
