package pagamento;

public class PagamentoCartao implements Pagamento {
    @Override
    public String pagar(double valor) {
        return "Pagamento de R$ " + String.format("%.2f", valor) + " realizado no Cartão.";
    }
}
