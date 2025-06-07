package br.com.visao;

import br.com.controle.Venda;
import br.com.entidade.ManterVendaDAO;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FormAlterarVenda extends JFrame {

    private JTextField tfId;
    private JTextField tfPlaca;
    private JTextField tfCpf;
    private JTextField tfMatricula;
    private JTextField tfData;
    private JButton btnAlterar;

    public FormAlterarVenda() {
        setTitle("Alterar Venda");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        initComponents();
    }

    private void initComponents() {
        JLabel lbId = new JLabel("ID da Venda:");
        JLabel lbPlaca = new JLabel("Nova Placa:");
        JLabel lbCpf = new JLabel("Novo CPF Cliente:");
        JLabel lbMatricula = new JLabel("Nova Matrícula Vendedor:");
        JLabel lbData = new JLabel("Nova Data (yyyy-MM-dd):");

        tfId = new JTextField();
        tfPlaca = new JTextField();
        tfCpf = new JTextField();
        tfMatricula = new JTextField();
        tfData = new JTextField();

        btnAlterar = new JButton("Alterar");
        btnAlterar.addActionListener(e -> alterarVenda());

        setLayout(new GridLayout(6, 2, 10, 10));
        add(lbId); add(tfId);
        add(lbPlaca); add(tfPlaca);
        add(lbCpf); add(tfCpf);
        add(lbMatricula); add(tfMatricula);
        add(lbData); add(tfData);
        add(new JLabel()); add(btnAlterar);
    }

    private void alterarVenda() {
        try {
            String idTexto = tfId.getText().trim();
            String placa = tfPlaca.getText().trim();
            String cpf = tfCpf.getText().trim();
            String matricula = tfMatricula.getText().trim();
            String dataTexto = tfData.getText().trim();

            if (idTexto.isEmpty() || placa.isEmpty() || cpf.isEmpty() || matricula.isEmpty() || dataTexto.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Todos os campos devem ser preenchidos.");
                return;
            }

            if (placa.length() > 8) {
                JOptionPane.showMessageDialog(this, "Placa inválida. Deve ter até 8 caracteres.");
                return;
            }

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            sdf.setLenient(false);
            Date data = sdf.parse(dataTexto);

            Venda v = new Venda();
            v.setId(Integer.parseInt(idTexto));
            v.setPlacaVeiculo(placa);
            v.setCpfCliente(cpf);
            v.setMatriculaVendedor(matricula);
            v.setDataVenda(data);

            ManterVendaDAO dao = new ManterVendaDAO();
            dao.editarVenda(v);

            JOptionPane.showMessageDialog(this, "Venda alterada com sucesso!");
            tfId.setText("");
            tfPlaca.setText("");
            tfCpf.setText("");
            tfMatricula.setText("");
            tfData.setText("");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao alterar venda: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FormAlterarVenda().setVisible(true));
    }
}
