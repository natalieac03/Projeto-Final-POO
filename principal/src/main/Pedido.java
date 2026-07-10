package main;

import produtos.Produto;
import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private Cliente cliente;
    private List<Produto> produtos;
    private String observacoes; 

    public Pedido(Cliente cliente) {
        this.cliente = cliente;
        this.produtos = new ArrayList<>();
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void adicionarProduto(Produto p) {
        produtos.add(p);
    }


    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public double calcularTotal() {
        return produtos.stream().mapToDouble(Produto::getPreco).sum();
    }


    public String getResumo() {
        StringBuilder sb = new StringBuilder();
        sb.append("Cliente: ").append(cliente.getNome()).append(" (");


        if (observacoes != null && !observacoes.trim().isEmpty()) {
            sb.append("OBS: ").append(observacoes.trim()).append("\n");
        }

        sb.append("Itens:\n");
        for (Produto p : produtos) {
            sb.append("- ").append(p.getNome()).append(": R$ ").append(String.format("%.2f", p.getPreco())).append("\n");
        }

        sb.append("\nTOTAL: R$ ").append(String.format("%.2f", calcularTotal()));
        return sb.toString();
    }

    @Override
    public String toString() {
        return getResumo();
    }
}
