package br.com.visao;

import br.com.controle.Vendedor;
import br.com.entidade.ManterVendedorDAO;

import javax.swing.*;
import java.awt.*;

public class FormDeletarVendedor extends JFrame {

    public FormDeletarVendedor() {
        initComponents();
        aplicarEstiloVisual();
    }

    private void aplicarEstiloVisual() {
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(240, 240, 240));

        jLabel1.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        jLabel1.setForeground(Color.DARK_GRAY);

        jTmatricula.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        jTmatricula.setBackground(Color.WHITE);
        jTmatricula.setForeground(Color.DARK_GRAY);
        jTmatricula.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        jBdeletar.setFont(new Font("Segoe UI", Font.BOLD, 16));
        jBdeletar.setBackground(new Color(220, 53, 69));
        jBdeletar.setForeground(Color.WHITE);
        jBdeletar.setFocusPainted(false);
        jBdeletar.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    private void initComponents() {
        jLabel1 = new JLabel("Digite a matrícula do vendedor a ser deletado:");
        jTmatricula = new JTextField();
        jBdeletar = new JButton("Deletar");

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Deletar Vendedor");

        jBdeletar.addActionListener(evt -> jBdeletarActionPerformed());

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
                                                .addComponent(jTmatricula, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE))
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
                                        .addComponent(jTmatricula, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
                                .addGap(30)
                                .addComponent(jBdeletar, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(40, Short.MAX_VALUE))
        );

        pack();
    }

    private void jBdeletarActionPerformed() {
        try {
            String matricula = jTmatricula.getText().trim();
            if (matricula.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Digite uma matrícula válida.");
                return;
            }

            Vendedor v = new Vendedor();
            v.setMatricula(matricula);
            ManterVendedorDAO dao = new ManterVendedorDAO();
            dao.deletar(v);
            JOptionPane.showMessageDialog(this, "Vendedor deletado com sucesso!");
            jTmatricula.setText("");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao deletar vendedor: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> new FormDeletarVendedor().setVisible(true));
    }

    // Componentes
    private JButton jBdeletar;
    private JLabel jLabel1;
    private JTextField jTmatricula;
}
