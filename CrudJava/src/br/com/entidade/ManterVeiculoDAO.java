package br.com.entidade;

import java.sql.PreparedStatement;
import br.com.controle.Veiculo;
import java.util.ArrayList;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

public class ManterVeiculoDAO extends DAO {

    public void inserir(Veiculo v) throws Exception {
        try {
            abrirBanco();
            String query = "INSERT INTO veiculo(placa, nome, modelo) VALUES (?, ?, ?)";
            pst = (PreparedStatement) con.prepareStatement(query);
            pst.setString(1, v.getPlaca());
            pst.setString(2, v.getNome());
            pst.setString(3, v.getModelo());
            pst.execute();
            fecharBanco();
        } catch (Exception e) {
            System.out.println("Erro " + e.getMessage());
        }
    }

    public void deletar(Veiculo v) {
        try {
            abrirBanco();
            String query = "DELETE FROM veiculo WHERE placa = ?";
            pst = (PreparedStatement) con.prepareStatement(query);
            pst.setString(1, v.getPlaca());
            pst.execute();
            fecharBanco();
        } catch (Exception e) {
            System.out.println("Erro " + e.getMessage());
        }
    }

    public ArrayList<Veiculo> pesquisarTudo() throws Exception {
        ArrayList<Veiculo> veiculos = new ArrayList<>();
        try {
            abrirBanco();
            String query = "SELECT * FROM veiculo";
            pst = (PreparedStatement) con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            Veiculo v;
            while (rs.next()) {
                v = new Veiculo();
                v.setPlaca(rs.getString("placa"));
                v.setNome(rs.getString("nome"));
                v.setModelo(rs.getString("modelo"));
                veiculos.add(v);
            }
            fecharBanco();
        } catch (Exception e) {
            System.out.println("Erro " + e.getMessage());
        }
        return veiculos;
    }

    public void pesquisarRegistro(Veiculo v) throws Exception {
        try {
            abrirBanco();
            String query = "SELECT * FROM veiculo WHERE placa = ?";
            pst = (PreparedStatement) con.prepareStatement(query);
            pst.setString(1, v.getPlaca());
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                v.setPlaca(rs.getString("placa"));
                v.setNome(rs.getString("nome"));
                v.setModelo(rs.getString("modelo"));
            } else {
                JOptionPane.showMessageDialog(null, "Nenhum resultado encontrado!");
            }
            fecharBanco();
        } catch (Exception e) {
            System.out.println("Erro " + e.getMessage());
        }
    }

    public void editarVeiculo(Veiculo v) throws Exception {
        try {
            abrirBanco();
            String query = "UPDATE veiculo SET nome = ?, modelo = ? WHERE placa = ?";
            pst = (PreparedStatement) con.prepareStatement(query);
            pst.setString(1, v.getNome());
            pst.setString(2, v.getModelo());
            pst.setString(3, v.getPlaca());
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Veículo alterado com sucesso!");
            fecharBanco();
        } catch (Exception e) {
            System.out.println("Erro " + e.getMessage());
        }
    }
}
