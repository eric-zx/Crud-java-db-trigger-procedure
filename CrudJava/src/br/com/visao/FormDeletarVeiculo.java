package br.com.visao;

import br.com.controle.Veiculo;
import br.com.entidade.ManterVeiculoDAO;

import javax.swing.*;
import java.awt.*;

public class FormDeletarVeiculo extends javax.swing.JFrame {

    public FormDeletarVeiculo() {
        initComponents();
        aplicarEstiloVisual();
    }

    private void aplicarEstiloVisual() {
        setLocationRelativeTo(null); // Centraliza a janela
        getContentPane().setBackground(new Color(62, 26, 26)); // Fundo claro

        jLabel1.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        jLabel1.setForeground(new Color(118, 81, 81));

        jTplaca.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        jTplaca.setBackground(Color.WHITE);
        jTplaca.setForeground(Color.DARK_GRAY);
        jTplaca.setBorder(BorderFactory.createLineBorder(new Color(62, 26, 26)));

        jBdeletar.setFont(new Font("Segoe UI", Font.BOLD, 16));
        jBdeletar.setBackground(new Color(220, 53, 69)); // Vermelho moderno
        jBdeletar.setForeground(Color.WHITE);
        jBdeletar.setFocusPainted(false);
        jBdeletar.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        jLabel1 = new JLabel();
        jTplaca = new JTextField();
        jBdeletar = new JButton();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Deletar Veículo");

        jLabel1.setText("Digite a placa do veículo a ser deletado:");

        jBdeletar.setText("Deletar");
        jBdeletar.addActionListener(evt -> jBdeletarActionPerformed(evt));

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(30)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel1)
                                                .addGap(10)
                                                .addComponent(jTplaca, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(120)
                                                .addComponent(jBdeletar, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(30, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(40)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel1)
                                        .addComponent(jTplaca, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
                                .addGap(30)
                                .addComponent(jBdeletar, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(40, Short.MAX_VALUE))
        );

        pack();
    }

    private void jBdeletarActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            String placa = jTplaca.getText().trim();
            if (placa.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Digite uma placa válida.");
                return;
            }

            Veiculo v = new Veiculo();
            v.setPlaca(placa);
            ManterVeiculoDAO dao = new ManterVeiculoDAO();
            dao.deletar(v);
            JOptionPane.showMessageDialog(this, "Veículo deletado com sucesso!");
            jTplaca.setText("");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao deletar veículo: " + e.getMessage());
        }
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new FormDeletarVeiculo().setVisible(true));
    }

    // Declaração dos componentes
    private JButton jBdeletar;
    private JLabel jLabel1;
    private JTextField jTplaca;
}
