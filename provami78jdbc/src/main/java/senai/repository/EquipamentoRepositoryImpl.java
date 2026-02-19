package senai.repository;

import senai.database.Conexao;
import senai.model.Equipamento;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

    public class EquipamentoRepositoryImpl implements EquipamentoRepository{

        @Override
        public Equipamento save(Equipamento equipamento) {
            String sql = "INSERT INTO Equipamento (nome, numero_serie, fornecedor_id) VALUES (?,?,?)";

            try (Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){

                stmt.setString(1, equipamento.getNome());
                stmt.setString(2, equipamento.getNumeroSerie());
                stmt.setInt(3, equipamento.getFornecedorId());

                int linhasAfetadas = stmt.executeUpdate();

                if (linhasAfetadas > 0){
                    ResultSet rs = stmt.getGeneratedKeys();
                    if(rs.next()){
                        int idGerado = rs.getInt(1);
                        equipamento.setId(idGerado);
                    }
                }
                return equipamento;

            } catch (SQLException e) {
                throw new RuntimeException("Erro ao salvar equipamento: "+ e.getMessage(), e);
            }
        }

    @Override
    public Equipamento findById(int id) {
        String query = """
                SELECT
                id, nome, numero_serie, fornecedor_id
                FROM Equipamento
                WHERE id = ?
                """;

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){

                stmt.setInt(1, id);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()){
                    return new Equipamento(
                            rs.getInt("id"),
                            rs.getString("nome"),
                            rs.getString("numero_serie"),
                            rs.getInt("fornecedor_id")
                    );
                }
                return null;
        } catch (SQLException e){
                throw new RuntimeException("Erro ao  buscar Equipamento por ID: " + e.getMessage(), e);
        }
    }

        @Override
        public List<Equipamento> findByEquipamentoID(int fornecedorId) throws SQLException {
            List<Equipamento> equipamentos = new ArrayList<>();

            String sql = """
        SELECT id, nome, numero_serie, fornecedor_id
        FROM Equipamento
        WHERE fornecedor_id = ?
        """;

            try (Connection conn = Conexao.conectar();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setInt(1, fornecedorId);

                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        Equipamento equipamento = new Equipamento();
                        equipamento.setId(rs.getInt("id"));
                        equipamento.setNome(rs.getString("nome"));
                        equipamento.setNumeroSerie(rs.getString("numero_serie"));
                        equipamento.setFornecedorId(rs.getInt("fornecedor_id"));
                        equipamentos.add(equipamento);
                    }
                }
            }

            return equipamentos;
        }

    @Override
    public void update(Equipamento equipamento) {
        String sql = """
                UPDATE Equipamento
                SET nome = ?, numero_serie = ?, fornecedor_id = ?
                WHERE id = ?
                """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setString(1, equipamento.getNome());
            stmt.setString(2, equipamento.getNumeroSerie());
            stmt.setInt(3, equipamento.getFornecedorId());
            stmt.setInt(4, equipamento.getId());

            int linhasAfetadas = stmt.executeUpdate();

            if(linhasAfetadas == 0){
                throw new RuntimeException("Equipamento não encontrado para atualização!");
            }
        } catch (SQLException e){
            throw new RuntimeException("Erro ao atualizar fornecedor: " + e.getMessage(), e);
        }
    }

    @Override
    public void delete(int id) {
    String command = "DELETE from Equipamento WHERE id = ?";

    try (Connection conn = Conexao.conectar();
    PreparedStatement stmt = conn.prepareStatement(command)){

        stmt.setInt(1, id);

        int linhasAfetadas = stmt.executeUpdate();
        if(linhasAfetadas == 0){
            throw new RuntimeException("Equipamento não encontrado para exclusão!");
        }
    } catch (SQLException e){
        throw new RuntimeException("Equipamento não encontrado para exclusão!"+ e.getMessage(), e);
    }
    }
}
