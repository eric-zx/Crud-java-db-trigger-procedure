package br.com.entidade;

import br.com.controle.Vendedor;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ManterVendedorDAO extends DAO {

    public void inserir(Vendedor v) throws Exception {
        try {
            abrirBanco();
            String query = "INSERT INTO vendedor(matricula, nome, setor) VALUES (?, ?, ?)";
            pst = con.prepareStatement(query);
            pst.setString(1, v.getMatricula());
            pst.setString(2, v.getNome());
            pst.setString(3, v.getSetor());
            pst.execute();
            fecharBanco();
        } catch (Exception e) {
            System.out.println("Erro " + e.getMessage());
        }
    }

    public void deletar(Vendedor v) {
        try {
            abrirBanco();
            String query = "DELETE FROM vendedor WHERE matricula = ?";
            pst = con.prepareStatement(query);
            pst.setString(1, v.getMatricula());
            pst.execute();
            fecharBanco();
        } catch (Exception e) {
            System.out.println("Erro " + e.getMessage());
        }
    }

    public ArrayList<Vendedor> pesquisarTudo() throws Exception {
        ArrayList<Vendedor> vendedores = new ArrayList<>();
        try {
            abrirBanco();
            String query = "SELECT * FROM vendedor";
            pst = con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            Vendedor v;
            while (rs.next()) {
                v = new Vendedor();
                v.setMatricula(rs.getString("matricula"));
                v.setNome(rs.getString("nome"));
                v.setSetor(rs.getString("setor"));
                vendedores.add(v);
            }
            fecharBanco();
        } catch (Exception e) {
            System.out.println("Erro " + e.getMessage());
        }
        return vendedores;
    }

    public void pesquisarRegistro(Vendedor v) throws Exception {
        try {
            abrirBanco();
            String query = "SELECT * FROM vendedor WHERE matricula = ?";
            pst = con.prepareStatement(query);
            pst.setString(1, v.getMatricula());
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                v.setMatricula(rs.getString("matricula"));
                v.setNome(rs.getString("nome"));
                v.setSetor(rs.getString("setor"));
            } else {
                JOptionPane.showMessageDialog(null, "Nenhum resultado encontrado!");
            }
            fecharBanco();
        } catch (Exception e) {
            System.out.println("Erro " + e.getMessage());
        }
    }

    public void editarVendedor(Vendedor v) throws Exception {
        try {
            abrirBanco();
            String query = "UPDATE vendedor SET nome = ?, setor = ? WHERE matricula = ?";
            pst = con.prepareStatement(query);
            pst.setString(1, v.getNome());
            pst.setString(2, v.getSetor());
            pst.setString(3, v.getMatricula());
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Vendedor alterado com sucesso!");
            fecharBanco();
        } catch (Exception e) {
            System.out.println("Erro " + e.getMessage());
        }
    }
}
