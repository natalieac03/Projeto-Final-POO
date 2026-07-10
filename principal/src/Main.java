import javax.swing.SwingUtilities;


public class Main {
    public static void main(String[] args) {

        GerenciadorPedidos gerenciador = GerenciadorPedidos.getInstancia();


        TelaCozinha cozinha = new TelaCozinha();
        TelaRetirada retirada = new TelaRetirada();
        

        gerenciador.adicionarObservador(cozinha);
        gerenciador.adicionarObservador(retirada);


        SwingUtilities.invokeLater(TelaPedido::new);
    }
}
