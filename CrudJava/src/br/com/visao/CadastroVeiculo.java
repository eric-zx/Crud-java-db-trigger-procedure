
package br.com.visao;

import br.com.controle.Veiculo;
import br.com.entidade.ManterVeiculoDAO;

import javax.swing.*;
import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CadastroVeiculo extends javax.swing.JFrame {

    public CadastroVeiculo() {
        try {
            // Aplica o tema Nimbus
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");

            // Personalização visual
            UIManager.put("Label.font", new Font("Segoe UI", Font.PLAIN, 16));
            UIManager.put("TextField.font", new Font("Segoe UI", Font.PLAIN, 16));
            UIManager.put("Button.font", new Font("Segoe UI", Font.BOLD, 16));
            UIManager.put("Button.background", new Color(0, 123, 255));
            UIManager.put("Button.foreground", Color.WHITE);
        } catch (Exception e) {
            e.printStackTrace();
        }

        initComponents();
        setLocationRelativeTo(null); // Centraliza a janela
        getContentPane().setBackground(new Color(245, 245, 245)); // Cor de fundo clara
    }

    private void initComponents() {
        jLabel1 = new JLabel("Placa:");
        jLabel2 = new JLabel("Nome:");
        jLabel3 = new JLabel("Modelo:");
        jLabel4 = new JLabel("Valor:");

        jTplaca = new JTextField();
        jTnome = new JTextField();
        jTmodelo = new JTextField();
        jTvalor = new JTextField();

        jBcadastrar = new JButton("Cadastrar");
        jBcadastrar.setFocusPainted(false);
        jBcadastrar.setBackground(new Color(40, 167, 69));
        jBcadastrar.setForeground(Color.WHITE);
        jBcadastrar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        jBcadastrar.addActionListener(evt -> jBcadastrarActionPerformed());

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de Veículo");

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);

        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(30)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(jBcadastrar, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel1)
                                .addComponent(jLabel2)
                                .addComponent(jLabel3)
                                .addComponent(jLabel4))
                            .addGap(18)
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                .addComponent(jTplaca)
                                .addComponent(jTnome)
                                .addComponent(jTmodelo)
                                .addComponent(jTvalor, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE))))
                    .addContainerGap(30, Short.MAX_VALUE))
        );

        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(30)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(jTplaca, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
                    .addGap(20)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(jTnome, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
                    .addGap(20)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(jTmodelo, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
                    .addGap(20)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(jTvalor, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
                    .addGap(30)
                    .addComponent(jBcadastrar, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(30, Short.MAX_VALUE))
        );

        pack();
    }

    private void jBcadastrarActionPerformed() {
        Veiculo v = new Veiculo();
        ManterVeiculoDAO dao = new ManterVeiculoDAO();

        if (jTplaca.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Digite uma placa válida");
            jTplaca.requestFocus();
        } else if (jTnome.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Digite um nome válido");
            jTnome.requestFocus();
        } else if (jTmodelo.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Digite um modelo válido");
            jTmodelo.requestFocus();
        } else if (jTvalor.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Digite um valor válido");
            jTvalor.requestFocus();
        } else {
            try {
                v.setPlaca(jTplaca.getText().trim());
                v.setNome(jTnome.getText().trim());
                v.setModelo(jTmodelo.getText().trim());
                v.setValor(Double.parseDouble(jTvalor.getText().trim()));
                dao.inserir(v);
                JOptionPane.showMessageDialog(this, "Veículo cadastrado com sucesso!");
            } catch (Exception ex) {
                Logger.getLogger(CadastroVeiculo.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this, "Erro ao cadastrar veículo.");
            }
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> new CadastroVeiculo().setVisible(true));
    }

    // Componentes
    private JButton jBcadastrar;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JTextField jTplaca;
    private JTextField jTnome;
    private JTextField jTmodelo;
    private JTextField jTvalor;
}
