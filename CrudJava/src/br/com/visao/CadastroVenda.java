package br.com.visao;

import br.com.controle.Venda;
import br.com.entidade.ManterVendaDAO;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CadastroVenda extends JFrame {

    private JComboBox<String> cbVeiculo;
    private JComboBox<String> cbCliente;
    private JComboBox<String> cbVendedor;
    private JTextField tfData;
    private JButton btCadastrar;

    public CadastroVenda() {
        setTitle("Cadastro de Venda");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        initComponents();
        carregarDados();
    }

    private void initComponents() {
        JLabel lbVeiculo = new JLabel("Veículo:");
        JLabel lbCliente = new JLabel("Cliente:");
        JLabel lbVendedor = new JLabel("Vendedor:");
        JLabel lbData = new JLabel("Data (yyyy-MM-dd):");

        cbVeiculo = new JComboBox<>();
        cbCliente = new JComboBox<>();
        cbVendedor = new JComboBox<>();
        tfData = new JTextField();

        btCadastrar = new JButton("Cadastrar Venda");
        btCadastrar.addActionListener(e -> cadastrarVenda());

        setLayout(new GridLayout(5, 2, 10, 10));
        add(lbVeiculo); add(cbVeiculo);
        add(lbCliente); add(cbCliente);
        add(lbVendedor); add(cbVendedor);
        add(lbData); add(tfData);
        add(new JLabel()); add(btCadastrar);
    }

    private void carregarDados() {
        try {
            cbVeiculo.removeAllItems();
            cbCliente.removeAllItems();
            cbVendedor.removeAllItems();

            for (String placa : ManterVendaDAO.listarPlacasVeiculos()) {
                cbVeiculo.addItem(placa);
            }
            for (String cpf : ManterVendaDAO.listarCpfsClientes()) {
                cbCliente.addItem(cpf);
            }
            for (String matricula : ManterVendaDAO.listarMatriculasVendedores()) {
                cbVendedor.addItem(matricula);
            }

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            tfData.setText(sdf.format(new Date()));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar dados: " + e.getMessage());
        }
    }

    private void cadastrarVenda() {
        try {
            String placa = (String) cbVeiculo.getSelectedItem();
            String cpf = (String) cbCliente.getSelectedItem();
            String matricula = (String) cbVendedor.getSelectedItem();
            String dataTexto = tfData.getText().trim();

            if (placa == null || placa.length() > 8) {
                JOptionPane.showMessageDialog(this, "Placa inválida. Deve ter até 8 caracteres.");
                return;
            }

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            sdf.setLenient(false);
            Date data = sdf.parse(dataTexto);

            Venda v = new Venda();
            v.setPlacaVeiculo(placa);
            v.setCpfCliente(cpf);
            v.setMatriculaVendedor(matricula);
            v.setDataVenda(data);

            ManterVendaDAO dao = new ManterVendaDAO();
            dao.inserir(v);

            JOptionPane.showMessageDialog(this, "Venda cadastrada com sucesso!");
            tfData.setText(sdf.format(new Date()));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao cadastrar venda: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CadastroVenda().setVisible(true));
    }
}
