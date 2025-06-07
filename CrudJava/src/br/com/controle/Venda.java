package br.com.controle;

import java.util.Date;

public class Venda {
    private int id;
    private String placaVeiculo;
    private String cpfCliente;
    private String matriculaVendedor;
    private Date dataVenda;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getPlacaVeiculo() { return placaVeiculo; }
    public void setPlacaVeiculo(String placaVeiculo) { this.placaVeiculo = placaVeiculo; }

    public String getCpfCliente() { return cpfCliente; }
    public void setCpfCliente(String cpfCliente) { this.cpfCliente = cpfCliente; }

    public String getMatriculaVendedor() { return matriculaVendedor; }
    public void setMatriculaVendedor(String matriculaVendedor) { this.matriculaVendedor = matriculaVendedor; }

    public Date getDataVenda() { return dataVenda; }
    public void setDataVenda(Date dataVenda) { this.dataVenda = dataVenda; }
}
