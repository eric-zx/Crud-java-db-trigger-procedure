
package br.com.visao;

import br.com.controle.Veiculo;
import br.com.entidade.ManterVeiculoDAO;

import javax.swing.*;
import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FormAlterarVeiculo extends javax.swing.JFrame {
    Veiculo v = new Veiculo();
    ManterVeiculoDAO dao = new ManterVeiculoDAO();

    public FormAlterarVeiculo() {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");

            UIManager.put("Label.font", new Font("Segoe UI", Font.PLAIN, 16));
            UIManager.put("TextField.font", new Font("Segoe UI", Font.PLAIN, 16));
            UIManager.put("Button.font", new Font("Segoe UI", Font.BOLD, 16));
            UIManager.put("Button.background", new Color(0, 123, 255));
            UIManager.put("Button.foreground", Color.WHITE);
        } catch (Exception e) {
            e.printStackTrace();
        }

        initComponents();
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(245, 245, 245));
    }

    private void initComponents() {
        jLabel1 = new JLabel("Digite a placa a ser pesquisada:");
        jTpesquisar = new JTextField();
        jLabel2 = new JLabel("Nome:");
        jTnome = new JTextField();
        jLabel3 = new JLabel("Modelo:");
        jTmodelo = new JTextField();
        jLabel4 = new JLabel("Valor:");
        jTvalor = new JTextField();

        jBpesquisar = new JButton("Pesquisar");
        jBpesquisar.setBackground(new Color(0, 123, 255));
        jBpesquisar.setForeground(Color.WHITE);
        jBpesquisar.setFocusPainted(false);
        jBpesquisar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jBpesquisar.addActionListener(evt -> jBpesquisarActionPerformed(evt));

        jBlimpar = new JButton("Limpar");
        jBlimpar.setBackground(new Color(108, 117, 125));
        jBlimpar.setForeground(Color.WHITE);
        jBlimpar.setFocusPainted(false);
        jBlimpar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jBlimpar.addActionListener(evt -> {
            jTpesquisar.setText("");
            jTnome.setText("");
            jTmodelo.setText("");
            jTvalor.setText("");
            jTpesquisar.setEnabled(true);
        });

        jBalterar = new JButton("Alterar");
        jBalterar.setBackground(new Color(40, 167, 69));
        jBalterar.setForeground(Color.WHITE);
        jBalterar.setFocusPainted(false);
        jBalterar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jBalterar.addActionListener(evt -> jBalterarActionPerformed(evt));

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Alterar Veículo");

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
                            .addComponent(jTpesquisar, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
                            .addGap(10)
                            .addComponent(jBpesquisar))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addGap(18)
                            .addComponent(jTnome, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel3)
                            .addGap(18)
                            .addComponent(jTmodelo, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel4)
                            .addGap(18)
                            .addComponent(jTvalor, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jBlimpar, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                            .addGap(20)
                            .addComponent(jBalterar, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(30, Short.MAX_VALUE))
        );

        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(30)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(jTpesquisar, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                        .addComponent(jBpesquisar, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
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
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(jBlimpar, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                        .addComponent(jBalterar, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(30, Short.MAX_VALUE))
        );

        pack();
    }

    private void jBpesquisarActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            v.setPlaca(jTpesquisar.getText());
            dao.pesquisarRegistro(v);
            jTnome.setText(v.getNome());
            jTmodelo.setText(v.getModelo());
            jTvalor.setText(String.valueOf(v.getValor()));
            jTpesquisar.setEnabled(false);
        } catch (Exception ex) {
            Logger.getLogger(FormAlterarVeiculo.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Erro ao pesquisar veículo.");
        }
    }

    private void jBalterarActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            v.setNome(jTnome.getText());
            v.setModelo(jTmodelo.getText());
            v.setValor(Double.parseDouble(jTvalor.getText()));
            dao.editarVeiculo(v);
            JOptionPane.showMessageDialog(this, "Veículo alterado com sucesso!");
            jTnome.setText("");
            jTmodelo.setText("");
            jTvalor.setText("");
            jTpesquisar.setEnabled(true);
            jTpesquisar.requestFocus();
        } catch (Exception ex) {
            Logger.getLogger(FormAlterarVeiculo.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Erro ao alterar veículo.");
        }
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> new FormAlterarVeiculo().setVisible(true));
    }

    // Componentes
    private JButton jBalterar;
    private JButton jBlimpar;
    private JButton jBpesquisar;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JTextField jTmodelo;
    private JTextField jTnome;
    private JTextField jTvalor;
    private JTextField jTpesquisar;
}
