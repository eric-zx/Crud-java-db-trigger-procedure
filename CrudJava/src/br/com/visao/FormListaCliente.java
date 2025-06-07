package br.com.visao;

import br.com.controle.Cliente;
import br.com.entidade.ManterClienteDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class FormListaCliente extends JFrame {

    public FormListaCliente() {
        initComponents();
        aplicarEstiloVisual();
    }

    private void aplicarEstiloVisual() {
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(230, 240, 250));

        jBlista.setFont(new Font("Segoe UI", Font.BOLD, 16));
        jBlista.setBackground(new Color(0, 123, 255));
        jBlista.setForeground(Color.WHITE);
        jBlista.setFocusPainted(false);
        jBlista.setCursor(new Cursor(Cursor.HAND_CURSOR));

        jTclientes.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        jTclientes.setRowHeight(24);
        jTclientes.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        jTclientes.getTableHeader().setBackground(new Color(200, 220, 240));
        jTclientes.setGridColor(new Color(220, 220, 220));
    }

    private void initComponents() {
        jBlista = new JButton("Listar Clientes");
        jScrollPane1 = new JScrollPane();
        jTclientes = new JTable();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Lista de Clientes");

        jBlista.addActionListener(evt -> jBlistaActionPerformed());

        jTclientes.setModel(new DefaultTableModel(
                new Object [][] {},
                new String [] { "CPF", "Nome", "Telefone" }
        ));
        jScrollPane1.setViewportView(jTclientes);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(20)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(jBlista, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 450, GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(20)
                                .addComponent(jBlista, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                                .addGap(10)
                                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 275, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(20, Short.MAX_VALUE))
        );

        pack();
    }

    private void jBlistaActionPerformed() {
        try {
            ManterClienteDAO dao = new ManterClienteDAO();
            ArrayList<Cliente> clientes = dao.pesquisarTudo();
            DefaultTableModel modeloTabela = (DefaultTableModel) jTclientes.getModel();
            modeloTabela.setRowCount(0); // limpa a tabela

            for (Cliente c : clientes) {
                modeloTabela.addRow(new Object[]{
                        c.getCpf(),
                        c.getNome(),
                        c.getTelefone()
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao listar clientes: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> new FormListaCliente().setVisible(true));
    }

    // Componentes
    private JButton jBlista;
    private JScrollPane jScrollPane1;
    private JTable jTclientes;
}
