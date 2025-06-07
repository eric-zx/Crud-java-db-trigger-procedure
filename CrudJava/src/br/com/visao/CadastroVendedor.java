package br.com.visao;

import br.com.controle.Vendedor;
import br.com.entidade.ManterVendedorDAO;

import javax.swing.*;
import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CadastroVendedor extends JFrame {

    public CadastroVendedor() {
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
        JLabel jLabel1 = new JLabel("Matrícula:");
        JLabel jLabel2 = new JLabel("Nome:");
        JLabel jLabel3 = new JLabel("Setor:");

        JTextField jTmatricula = new JTextField();
        JTextField jTnome = new JTextField();
        JTextField jTsetor = new JTextField();

        JButton jBcadastrar = new JButton("Cadastrar");
        jBcadastrar.setBackground(new Color(40, 167, 69));
        jBcadastrar.setForeground(Color.WHITE);
        jBcadastrar.setFocusPainted(false);
        jBcadastrar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        jBcadastrar.addActionListener(evt -> {
            Vendedor v = new Vendedor();
            ManterVendedorDAO dao = new ManterVendedorDAO();

            if (jTmatricula.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Digite uma matrícula válida");
                jTmatricula.requestFocus();
            } else if (jTnome.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Digite um nome válido");
                jTnome.requestFocus();
            } else if (jTsetor.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Digite um setor válido");
                jTsetor.requestFocus();
            } else {
                try {
                    v.setMatricula(jTmatricula.getText().trim());
                    v.setNome(jTnome.getText().trim());
                    v.setSetor(jTsetor.getText().trim());
                    dao.inserir(v);
                    JOptionPane.showMessageDialog(this, "Vendedor cadastrado com sucesso!");
                } catch (Exception ex) {
                    Logger.getLogger(CadastroVendedor.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(this, "Erro ao cadastrar vendedor.");
                }
            }
        });

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de Vendedor");

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
                                                        .addComponent(jLabel3))
                                                .addGap(18)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(jTmatricula)
                                                        .addComponent(jTnome)
                                                        .addComponent(jTsetor, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE))))
                                .addContainerGap(30, Short.MAX_VALUE))
        );

        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(30)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel1)
                                        .addComponent(jTmatricula, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
                                .addGap(20)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel2)
                                        .addComponent(jTnome, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
                                .addGap(20)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel3)
                                        .addComponent(jTsetor, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
                                .addGap(30)
                                .addComponent(jBcadastrar, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(30, Short.MAX_VALUE))
        );

        pack();
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> new CadastroVendedor().setVisible(true));
    }
}
