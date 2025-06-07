package br.com.visao;

import br.com.controle.Venda;
import br.com.entidade.ManterVendaDAO;

import javax.swing.*;
import java.awt.*;

public class FormDeletarVenda extends JFrame {

    private JTextField tfId;
    private JButton btnDeletar;

    public FormDeletarVenda() {
        setTitle("Deletar Venda");
        setSize(300, 150);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        initComponents();
    }

    private void initComponents() {
        JLabel lbId = new JLabel("ID da Venda:");

        tfId = new JTextField();
        btnDeletar = new JButton("Deletar");

        btnDeletar.addActionListener(e -> deletarVenda());

        setLayout(new GridLayout(2, 2, 10, 10));
        add(lbId); add(tfId);
        add(new JLabel()); add(btnDeletar);
    }

    private void deletarVenda() {
        try {
            String idTexto = tfId.getText().trim();
            if (idTexto.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Digite o ID da venda.");
                return;
            }

            int id = Integer.parseInt(idTexto);
            Venda v = new Venda();
            v.setId(id);

            ManterVendaDAO dao = new ManterVendaDAO();
            dao.deletar(v);

            JOptionPane.showMessageDialog(this, "Venda deletada com sucesso!");
            tfId.setText("");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao deletar venda: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FormDeletarVenda().setVisible(true));
    }
}
