package br.com.visao;

import br.com.controle.Cliente;
import br.com.entidade.ManterClienteDAO;

import javax.swing.*;
import java.awt.*;

public class FormDeletarCliente extends JFrame {

    public FormDeletarCliente() {
        initComponents();
        aplicarEstiloVisual();
    }

    private void aplicarEstiloVisual() {
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(240, 240, 240));

        jLabel1.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        jLabel1.setForeground(Color.DARK_GRAY);

        jTcpf.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        jTcpf.setBackground(Color.WHITE);
        jTcpf.setForeground(Color.DARK_GRAY);
        jTcpf.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        jBdeletar.setFont(new Font("Segoe UI", Font.BOLD, 16));
        jBdeletar.setBackground(new Color(220, 53, 69));
        jBdeletar.setForeground(Color.WHITE);
        jBdeletar.setFocusPainted(false);
        jBdeletar.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    private void initComponents() {
        jLabel1 = new JLabel("Digite o CPF do cliente a ser deletado:");
        jTcpf = new JTextField();
        jBdeletar = new JButton("Deletar");

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Deletar Cliente");

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
                                                .addComponent(jTcpf, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE))
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
                                        .addComponent(jTcpf, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
                                .addGap(30)
                                .addComponent(jBdeletar, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(40, Short.MAX_VALUE))
        );

        pack();
    }

    private void jBdeletarActionPerformed() {
        try {
            String cpf = jTcpf.getText().trim();
            if (cpf.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Digite um CPF válido.");
                return;
            }

            Cliente c = new Cliente();
            c.setCpf(cpf);
            ManterClienteDAO dao = new ManterClienteDAO();
            dao.deletar(c);
            JOptionPane.showMessageDialog(this, "Cliente deletado com sucesso!");
            jTcpf.setText("");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao deletar cliente: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> new FormDeletarCliente().setVisible(true));
    }

    // Componentes
    private JButton jBdeletar;
    private JLabel jLabel1;
    private JTextField jTcpf;
}
