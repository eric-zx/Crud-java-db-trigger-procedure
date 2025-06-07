package br.com.visao;

import br.com.controle.Vendedor;
import br.com.entidade.ManterVendedorDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class FormListaVendedor extends JFrame {

    public FormListaVendedor() {
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

        jTvendedores.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        jTvendedores.setRowHeight(24);
        jTvendedores.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        jTvendedores.getTableHeader().setBackground(new Color(200, 220, 240));
        jTvendedores.setGridColor(new Color(220, 220, 220));
    }

    private void initComponents() {
        jBlista = new JButton("Listar Vendedores");
        jScrollPane1 = new JScrollPane();
        jTvendedores = new JTable();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Lista de Vendedores");

        jBlista.addActionListener(evt -> jBlistaActionPerformed());

        jTvendedores.setModel(new DefaultTableModel(
                new Object [][] {},
                new String [] { "Matrícula", "Nome", "Setor" }
        ));
        jScrollPane1.setViewportView(jTvendedores);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(20)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(jBlista, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
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
            ManterVendedorDAO dao = new ManterVendedorDAO();
            ArrayList<Vendedor> vendedores = dao.pesquisarTudo();
            DefaultTableModel modeloTabela = (DefaultTableModel) jTvendedores.getModel();
            modeloTabela.setRowCount(0); // limpa a tabela

            for (Vendedor v : vendedores) {
                modeloTabela.addRow(new Object[]{
                        v.getMatricula(),
                        v.getNome(),
                        v.getSetor()
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao listar vendedores: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> new FormListaVendedor().setVisible(true));
    }

    // Componentes
    private JButton jBlista;
    private JScrollPane jScrollPane1;
    private JTable jTvendedores;
}
