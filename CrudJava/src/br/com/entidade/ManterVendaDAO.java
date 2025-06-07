package br.com.entidade;

import br.com.controle.Venda;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ManterVendaDAO extends DAO {

    public void inserir(Venda v) throws Exception {
        try {
            abrirBanco();
            String query = "INSERT INTO venda (placa_veiculo, cpf_cliente, matricula_vendedor, data_venda) VALUES (?, ?, ?, ?)";
            pst = con.prepareStatement(query);
            pst.setString(1, v.getPlacaVeiculo());
            pst.setString(2, v.getCpfCliente());
            pst.setString(3, v.getMatriculaVendedor());
            pst.setDate(4, new java.sql.Date(v.getDataVenda().getTime()));
            pst.execute();
            fecharBanco();
            JOptionPane.showMessageDialog(null, "Venda registrada com sucesso!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao registrar venda: " + e.getMessage());
        }
    }

    public void editarVenda(Venda v) throws Exception {
        try {
            abrirBanco();
            String query = "UPDATE venda SET placa_veiculo = ?, cpf_cliente = ?, matricula_vendedor = ?, data_venda = ? WHERE id = ?";
            pst = con.prepareStatement(query);
            pst.setString(1, v.getPlacaVeiculo());
            pst.setString(2, v.getCpfCliente());
            pst.setString(3, v.getMatriculaVendedor());
            pst.setDate(4, new java.sql.Date(v.getDataVenda().getTime()));
            pst.setInt(5, v.getId());
            pst.executeUpdate();
            fecharBanco();
            JOptionPane.showMessageDialog(null, "Venda alterada com sucesso!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao alterar venda: " + e.getMessage());
        }
    }

    public void deletar(Venda v) throws Exception {
        try {
            abrirBanco();
            String query = "DELETE FROM venda WHERE id = ?";
            pst = con.prepareStatement(query);
            pst.setInt(1, v.getId());
            pst.executeUpdate();
            fecharBanco();
            JOptionPane.showMessageDialog(null, "Venda deletada com sucesso!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao deletar venda: " + e.getMessage());
        }
    }

    public ArrayList<Venda> listarVendas() throws Exception {
        ArrayList<Venda> vendas = new ArrayList<>();
        try {
            abrirBanco();
            String query = "SELECT * FROM venda";
            pst = con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Venda v = new Venda();
                v.setId(rs.getInt("id"));
                v.setPlacaVeiculo(rs.getString("placa_veiculo"));
                v.setCpfCliente(rs.getString("cpf_cliente"));
                v.setMatriculaVendedor(rs.getString("matricula_vendedor"));
                v.setDataVenda(rs.getDate("data_venda"));
                vendas.add(v);
            }
            fecharBanco();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar vendas: " + e.getMessage());
        }
        return vendas;
    }

    public static ArrayList<String> listarPlacasVeiculos() throws Exception {
        ArrayList<String> placas = new ArrayList<>();
        DAO dao = new DAO();
        try {
            dao.abrirBanco();
            String query = "SELECT placa FROM veiculo";
            dao.pst = dao.con.prepareStatement(query);
            ResultSet rs = dao.pst.executeQuery();
            while (rs.next()) {
                placas.add(rs.getString("placa"));
            }
            dao.fecharBanco();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar placas de veículos: " + e.getMessage());
        }
        return placas;
    }

    public static ArrayList<String> listarCpfsClientes() throws Exception {
        ArrayList<String> cpfs = new ArrayList<>();
        DAO dao = new DAO();
        try {
            dao.abrirBanco();
            String query = "SELECT cpf FROM cliente";
            dao.pst = dao.con.prepareStatement(query);
            ResultSet rs = dao.pst.executeQuery();
            while (rs.next()) {
                cpfs.add(rs.getString("cpf"));
            }
            dao.fecharBanco();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar CPFs de clientes: " + e.getMessage());
        }
        return cpfs;
    }

    public static ArrayList<String> listarMatriculasVendedores() throws Exception {
        ArrayList<String> matriculas = new ArrayList<>();
        DAO dao = new DAO();
        try {
            dao.abrirBanco();
            String query = "SELECT matricula FROM vendedor";
            dao.pst = dao.con.prepareStatement(query);
            ResultSet rs = dao.pst.executeQuery();
            while (rs.next()) {
                matriculas.add(rs.getString("matricula"));
            }
            dao.fecharBanco();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar matrículas de vendedores: " + e.getMessage());
        }
        return matriculas;
    }
}
