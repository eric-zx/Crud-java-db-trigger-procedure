package br.com.entidade;

import br.com.controle.Cliente;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ManterClienteDAO extends DAO {

    public void inserir(Cliente c) throws Exception {
        try {
            abrirBanco();
            String query = "INSERT INTO cliente(cpf, nome, telefone) VALUES (?, ?, ?)";
            pst = con.prepareStatement(query);
            pst.setString(1, c.getCpf());
            pst.setString(2, c.getNome());
            pst.setString(3, c.getTelefone());
            pst.execute();
            fecharBanco();
        } catch (Exception e) {
            System.out.println("Erro " + e.getMessage());
        }
    }

    public void deletar(Cliente c) {
        try {
            abrirBanco();
            String query = "DELETE FROM cliente WHERE cpf = ?";
            pst = con.prepareStatement(query);
            pst.setString(1, c.getCpf());
            pst.execute();
            fecharBanco();
        } catch (Exception e) {
            System.out.println("Erro " + e.getMessage());
        }
    }

    public ArrayList<Cliente> pesquisarTudo() throws Exception {
        ArrayList<Cliente> clientes = new ArrayList<>();
        try {
            abrirBanco();
            String query = "SELECT * FROM cliente";
            pst = con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            Cliente c;
            while (rs.next()) {
                c = new Cliente();
                c.setCpf(rs.getString("cpf"));
                c.setNome(rs.getString("nome"));
                c.setTelefone(rs.getString("telefone"));
                clientes.add(c);
            }
            fecharBanco();
        } catch (Exception e) {
            System.out.println("Erro " + e.getMessage());
        }
        return clientes;
    }

    public void pesquisarRegistro(Cliente c) throws Exception {
        try {
            abrirBanco();
            String query = "SELECT * FROM cliente WHERE cpf = ?";
            pst = con.prepareStatement(query);
            pst.setString(1, c.getCpf());
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                c.setCpf(rs.getString("cpf"));
                c.setNome(rs.getString("nome"));
                c.setTelefone(rs.getString("telefone"));
            } else {
                JOptionPane.showMessageDialog(null, "Nenhum resultado encontrado!");
            }
            fecharBanco();
        } catch (Exception e) {
            System.out.println("Erro " + e.getMessage());
        }
    }

    public void editarCliente(Cliente c) throws Exception {
        try {
            abrirBanco();
            String query = "UPDATE cliente SET nome = ?, telefone = ? WHERE cpf = ?";
            pst = con.prepareStatement(query);
            pst.setString(1, c.getNome());
            pst.setString(2, c.getTelefone());
            pst.setString(3, c.getCpf());
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Cliente alterado com sucesso!");
            fecharBanco();
        } catch (Exception e) {
            System.out.println("Erro " + e.getMessage());
        }
    }
}
