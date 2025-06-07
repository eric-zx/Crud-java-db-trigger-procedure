package br.com.entidade;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAO {
    protected Connection con; // conexão com o banco
    protected PreparedStatement pst; // preparação de comandos SQL
    protected ResultSet rs; // resultados de consultas

    public void abrirBanco() throws SQLException {
        try {
            // Carrega o driver JDBC do MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Configurações de conexão
            String url = "jdbc:mysql://localhost:3306/veiculos_db"; // nome do banco atualizado
            String user = "root"; // usuário do MySQL
            String senha = ""; // senha do MySQL

            // Estabelece a conexão
            con = DriverManager.getConnection(url, user, senha);
            System.out.println("Sistema conectado com sucesso!!");

        } catch (ClassNotFoundException ex) {
            System.out.println("ERRO DE SISTEMA");
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void fecharBanco() throws Exception {
        if (pst != null) {
            pst.close();
            System.out.println("Execução da query finalizada.");
        }
        if (con != null) {
            con.close();
            System.out.println("Conexão com o banco encerrada.");
        }
    }
}
