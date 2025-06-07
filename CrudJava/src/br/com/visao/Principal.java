package br.com.visao;

import javax.swing.*;
import java.awt.*;

public class Principal extends javax.swing.JFrame {

    public Principal() {
        initComponents();
        aplicarEstiloVisual();
    }

    private void aplicarEstiloVisual() {
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(62, 26, 26));

        jMenuBar1.setBackground(new Color(118, 81, 81));
        jMenuBar1.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        jMenuBar1.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        for (JMenu menu : new JMenu[]{jMenu1, jMenu2, jMenu3, jMenu4, jMenuCliente, jMenuVendedor, jMenuVenda}) {
            menu.setFont(new Font("Segoe UI", Font.BOLD, 14));
        }
    }

    private void initComponents() {
        jMenuBar1 = new JMenuBar();

        jMenu1 = new JMenu("Arquivo");
        jMenu2 = new JMenu("Deletar");
        jMenu3 = new JMenu("Relatórios");
        jMenu4 = new JMenu("Alterar");
        jMenuCliente = new JMenu("Cliente");
        jMenuVendedor = new JMenu("Vendedor");
        jMenuVenda = new JMenu("Vendas");

        // Veículo
        jMenuItem1 = new JMenuItem("Cadastro Veículo");
        jMenuItem1.addActionListener(evt -> new CadastroVeiculo().setVisible(true));
        jMenu1.add(jMenuItem1);

        jMenuItem2 = new JMenuItem("Deletar Veículo");
        jMenuItem2.addActionListener(evt -> new FormDeletarVeiculo().setVisible(true));
        jMenu2.add(jMenuItem2);

        jMenuItem3 = new JMenuItem("Listar Veículos");
        jMenuItem3.addActionListener(evt -> new FormListaVeiculo().setVisible(true));
        jMenu3.add(jMenuItem3);

        jMenuItem4 = new JMenuItem("Alterar Veículo");
        jMenuItem4.addActionListener(evt -> new FormAlterarVeiculo().setVisible(true));
        jMenu4.add(jMenuItem4);

        // Cliente
        JMenuItem itemCadCliente = new JMenuItem("Cadastrar Cliente");
        itemCadCliente.addActionListener(evt -> new CadastroCliente().setVisible(true));
        JMenuItem itemAltCliente = new JMenuItem("Alterar Cliente");
        itemAltCliente.addActionListener(evt -> new FormAlterarCliente().setVisible(true));
        JMenuItem itemDelCliente = new JMenuItem("Deletar Cliente");
        itemDelCliente.addActionListener(evt -> new FormDeletarCliente().setVisible(true));
        JMenuItem itemListCliente = new JMenuItem("Listar Clientes");
        itemListCliente.addActionListener(evt -> new FormListaCliente().setVisible(true));

        jMenuCliente.add(itemCadCliente);
        jMenuCliente.add(itemAltCliente);
        jMenuCliente.add(itemDelCliente);
        jMenuCliente.add(itemListCliente);

        // Vendedor
        JMenuItem itemCadVendedor = new JMenuItem("Cadastrar Vendedor");
        itemCadVendedor.addActionListener(evt -> new CadastroVendedor().setVisible(true));
        JMenuItem itemAltVendedor = new JMenuItem("Alterar Vendedor");
        itemAltVendedor.addActionListener(evt -> new FormAlterarVendedor().setVisible(true));
        JMenuItem itemDelVendedor = new JMenuItem("Deletar Vendedor");
        itemDelVendedor.addActionListener(evt -> new FormDeletarVendedor().setVisible(true));
        JMenuItem itemListVendedor = new JMenuItem("Listar Vendedores");
        itemListVendedor.addActionListener(evt -> new FormListaVendedor().setVisible(true));

        jMenuVendedor.add(itemCadVendedor);
        jMenuVendedor.add(itemAltVendedor);
        jMenuVendedor.add(itemDelVendedor);
        jMenuVendedor.add(itemListVendedor);

        // Vendas
        JMenuItem itemCadVenda = new JMenuItem("Cadastrar Venda");
        itemCadVenda.addActionListener(evt -> new CadastroVenda().setVisible(true));

        JMenuItem itemAltVenda = new JMenuItem("Alterar Venda");
        itemAltVenda.addActionListener(evt -> new FormAlterarVenda().setVisible(true));

        JMenuItem itemDelVenda = new JMenuItem("Deletar Venda");
        itemDelVenda.addActionListener(evt -> new FormDeletarVenda().setVisible(true));

        JMenuItem itemListarVendas = new JMenuItem("Listar Vendas");
        itemListarVendas.addActionListener(evt -> new FormListarVenda().setVisible(true));

        jMenuVenda.add(itemCadVenda);
        jMenuVenda.add(itemAltVenda);
        jMenuVenda.add(itemDelVenda);
        jMenuVenda.add(itemListarVendas);

        // Adiciona menus à barra
        jMenuBar1.add(jMenu1);
        jMenuBar1.add(jMenu2);
        jMenuBar1.add(jMenu3);
        jMenuBar1.add(jMenu4);
        jMenuBar1.add(jMenuCliente);
        jMenuBar1.add(jMenuVendedor);
        jMenuBar1.add(jMenuVenda);

        setJMenuBar(jMenuBar1);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sistema de Gerenciamento");
        setResizable(false);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 500, Short.MAX_VALUE));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 400, Short.MAX_VALUE));

        pack();
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new Principal().setVisible(true));
    }

    // Componentes
    private JMenu jMenu1;
    private JMenu jMenu2;
    private JMenu jMenu3;
    private JMenu jMenu4;
    private JMenu jMenuCliente;
    private JMenu jMenuVendedor;
    private JMenu jMenuVenda;
    private JMenuBar jMenuBar1;
    private JMenuItem jMenuItem1;
    private JMenuItem jMenuItem2;
    private JMenuItem jMenuItem3;
    private JMenuItem jMenuItem4;
}
