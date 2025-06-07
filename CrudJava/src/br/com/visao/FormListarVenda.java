
package br.com.visao;

import br.com.entidade.ManterVendaDAO;
import br.com.controle.Venda;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class FormListarVenda extends JFrame {

    public FormListarVenda() {
        initComponents();
        aplicarEstiloVisual();
        carregarVendas();
    }

    private void aplicarEstiloVisual() {
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(230, 240, 250));

        jBlista.setFont(new Font("Segoe UI", Font.BOLD, 16));
        jBlista.setBackground(new Color(0, 123, 255));
        jBlista.setForeground(Color.WHITE);
        jBlista.setFocusPainted(false);
        jBlista.setCursor(new Cursor(Cursor.HAND_CURSOR));

        jTvendas.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        jTvendas.setRowHeight(24);
        jTvendas.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        jTvendas.getTableHeader().setBackground(new Color(200, 220, 240));
        jTvendas.setGridColor(new Color(220, 220, 220));
    }

    private void initComponents() {
        jBlista = new JButton("Listar Vendas");
        jScrollPane1 = new JScrollPane();
        jTvendas = new JTable();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Lista de Vendas");

        jBlista.addActionListener(evt -> carregarVendas());

        jTvendas.setModel(new DefaultTableModel(
            new Object [][] {},
            new String [] { "Veículo", "Cliente", "Vendedor", "Data" }
        ));
        jScrollPane1.setViewportView(jTvendas);

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

    private void carregarVendas() {
        try {
            ManterVendaDAO dao = new ManterVendaDAO();
            ArrayList<Venda> vendas = dao.listarVendas();
            DefaultTableModel modeloTabela = (DefaultTableModel) jTvendas.getModel();
            modeloTabela.setRowCount(0); // limpa a tabela

            for (Venda v : vendas) {
                modeloTabela.addRow(new Object[]{
                    v.getPlacaVeiculo(),
                    v.getCpfCliente(),
                    v.getMatriculaVendedor(),
                    v.getDataVenda()
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao listar vendas: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FormListarVenda().setVisible(true));
    }

    // Componentes
    private JButton jBlista;
    private JScrollPane jScrollPane1;
    private JTable jTvendas;
}
