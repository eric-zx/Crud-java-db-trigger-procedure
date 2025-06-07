package br.com.visao;

import br.com.controle.Cliente;
import br.com.entidade.ManterClienteDAO;

import javax.swing.*;
import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FormAlterarCliente extends JFrame {
    Cliente c = new Cliente();
    ManterClienteDAO dao = new ManterClienteDAO();

    public FormAlterarCliente() {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            UIManager.put("Label.font", new Font("Segoe UI", Font.PLAIN, 16));
            UIManager.put("TextField.font", new Font("Segoe UI", Font.PLAIN, 16));
            UIManager.put("Button.font", new Font("Segoe UI", Font.BOLD, 16));
        } catch (Exception e) {
            e.printStackTrace();
        }

        initComponents();
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(245, 245, 245));
    }

    private void initComponents() {
        JLabel jLabel1 = new JLabel("Digite o CPF a ser pesquisado:");
        JTextField jTpesquisar = new JTextField();
        JLabel jLabel2 = new JLabel("Nome:");
        JTextField jTnome = new JTextField();
        JLabel jLabel3 = new JLabel("Telefone:");
        JTextField jTtelefone = new JTextField();

        JButton jBpesquisar = new JButton("Pesquisar");
        JButton jBlimpar = new JButton("Limpar");
        JButton jBalterar = new JButton("Alterar");

        jBpesquisar.setBackground(new Color(0, 123, 255));
        jBpesquisar.setForeground(Color.WHITE);
        jBpesquisar.setFocusPainted(false);
        jBpesquisar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        jBlimpar.setBackground(new Color(108, 117, 125));
        jBlimpar.setForeground(Color.WHITE);
        jBlimpar.setFocusPainted(false);
        jBlimpar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        jBalterar.setBackground(new Color(40, 167, 69));
        jBalterar.setForeground(Color.WHITE);
        jBalterar.setFocusPainted(false);
        jBalterar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        jBpesquisar.addActionListener(evt -> {
            try {
                c.setCpf(jTpesquisar.getText());
                dao.pesquisarRegistro(c);
                jTnome.setText(c.getNome());
                jTtelefone.setText(c.getTelefone());
                jTpesquisar.setEnabled(false);
            } catch (Exception ex) {
                Logger.getLogger(FormAlterarCliente.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this, "Erro ao pesquisar cliente.");
            }
        });

        jBlimpar.addActionListener(evt -> {
            jTpesquisar.setText("");
            jTnome.setText("");
            jTtelefone.setText("");
            jTpesquisar.setEnabled(true);
        });

        jBalterar.addActionListener(evt -> {
            try {
                c.setNome(jTnome.getText());
                c.setTelefone(jTtelefone.getText());
                dao.editarCliente(c);
                JOptionPane.showMessageDialog(this, "Cliente alterado com sucesso!");
                jTnome.setText("");
                jTtelefone.setText("");
                jTpesquisar.setEnabled(true);
                jTpesquisar.requestFocus();
            } catch (Exception ex) {
                Logger.getLogger(FormAlterarCliente.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this, "Erro ao alterar cliente.");
            }
        });

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Alterar Cliente");

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
                                                .addComponent(jTtelefone, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE))
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
                                        .addComponent(jTtelefone, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
                                .addGap(30)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(jBlimpar, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jBalterar, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(30, Short.MAX_VALUE))
        );

        pack();
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> new FormAlterarCliente().setVisible(true));
    }
}
