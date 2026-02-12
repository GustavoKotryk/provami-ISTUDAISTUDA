package senai.repository;

import senai.database.Conexao;
import senai.model.Fornecedor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FornecedorRepositoryImpl implements FornecedorRepository{

    @Override
    public Fornecedor save(Fornecedor fornecedor){
        String sql = "INSERT INTO Fornecedor (nome, cnpj) VALUES (?, ?)";

        try(Connection conn = Conexao.conectar();
        PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){

            stmt.setString(1, fornecedor.getNome());
            stmt.setString(2, fornecedor.getCnpj());

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0){
                ResultSet rs = stmt.getGeneratedKeys();
                if(rs.next()){
                    int idGerado = rs.getInt(1);
                    fornecedor.setId(idGerado);
                }
            }
            return fornecedor;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar fornecedor: " + e.getMessage(), e);
        }
    }

    @Override
    public Fornecedor buscarPorId(int id){

        String query = """
            SELECT
            id,
            nome,
            cnpj
            FROM Fornecedor
            WHERE id = ?
            """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()){
                return new Fornecedor(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("cnpj")
                );
            }

            return null;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar fornecedor por ID: "+ e.getMessage(), e);
        }
    }

    @Override
    public List<Fornecedor> findAll() {
        String sql = """
                SELECT
                id,
                nome,
                cnpj
                FROM Fornecedor
                """;

        List<Fornecedor> fornecedores = new ArrayList<>();

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()){

            while (rs.next()){
                Fornecedor f = new Fornecedor(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("cnpj")
                );
                fornecedores.add(f);
            }

            return fornecedores;

        } catch (SQLException e){
            throw new RuntimeException("Erro ao listar fornecedores:  " + e.getMessage(), e);
        }

    }

    @Override
    public void update(Fornecedor fornecedor) {

        String sql = """
                UPDATE Fornecedor
                SET nome = ?, cnpj = ?
                WHERE id = ?
                """;

        try (Connection conn = Conexao.conectar();
        PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setString(1, fornecedor.getNome());
            stmt.setString(2, fornecedor.getCnpj());
            stmt.setInt(3, fornecedor.getId());

            int linhasAfetadas = stmt.executeUpdate();

            if(linhasAfetadas == 0){
                throw new RuntimeException("Id do fornecedor não encontrado.");
            }
        } catch (SQLException e){
            throw new RuntimeException("Erro ao atualizar fornecedor: " + e.getMessage(), e);
        }

    }

    @Override
    public void delete(int id) {
        String command  = " DELETE from Fornecedor WHERE id = ?" ;

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(command )) {

             stmt.setInt(1, id);

            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas == 0) {
                throw new RuntimeException("Id do fornecedor não encontrado.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar fornecedor: " + e.getMessage(), e);
        }
    }
}
