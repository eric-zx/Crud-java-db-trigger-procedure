package br.com.visao;

import br.com.controle.Veiculo;
import br.com.entidade.ManterVeiculoDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class FormListaVeiculo extends javax.swing.JFrame {

    public FormListaVeiculo() {
        initComponents();
        aplicarEstiloVisual();
    }

    private void aplicarEstiloVisual() {
        setLocationRelativeTo(null); // Centraliza a janela
        getContentPane().setBackground(new Color(116, 211, 199)); // Fundo claro

        jBlista.setFont(new Font("Segoe UI", Font.BOLD, 16));
        jBlista.setBackground(new Color(130, 178, 232));
        jBlista.setForeground(Color.WHITE);
        jBlista.setFocusPainted(false);
        jBlista.setCursor(new Cursor(Cursor.HAND_CURSOR));

        jTveiculos.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        jTveiculos.setRowHeight(24);
        jTveiculos.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        jTveiculos.getTableHeader().setBackground(new Color(230, 230, 230));
        jTveiculos.setGridColor(new Color(220, 220, 220));
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        jBlista = new JButton("Listar Veículos");
        jScrollPane1 = new JScrollPane();
        jTveiculos = new JTable();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Lista de Veículos");

        jBlista.addActionListener(evt -> jBlistaActionPerformed(evt));

        jTveiculos.setModel(new DefaultTableModel(
                new Object [][] {},
                new String [] { "Placa", "Nome", "Modelo" }
        ));
        jScrollPane1.setViewportView(jTveiculos);

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

    private void jBlistaActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            ManterVeiculoDAO dao = new ManterVeiculoDAO();
            ArrayList<Veiculo> veiculos = dao.pesquisarTudo();
            DefaultTableModel modeloTabela = (DefaultTableModel) jTveiculos.getModel();
            modeloTabela.setRowCount(0); // limpa a tabela

            for (Veiculo v : veiculos) {
                modeloTabela.addRow(new Object[]{
                        v.getPlaca(),
                        v.getNome(),
                        v.getModelo()
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new FormListaVeiculo().setVisible(true));
    }

    // Declaração dos componentes
    private JButton jBlista;
    private JScrollPane jScrollPane1;
    private JTable jTveiculos;
}
