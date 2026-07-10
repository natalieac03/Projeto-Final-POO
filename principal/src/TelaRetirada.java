import javax.swing.*;
import java.awt.*;
import main.Pedido;
import observer.ObservadorPedidos;
import java.util.List;

public class TelaRetirada extends JFrame implements ObservadorPedidos {

    private DefaultListModel<String> modeloProntos;
    private JList<String> listaProntos;

    public TelaRetirada() {
        super("Painel de Retirada");
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(100, 150); 
        setLayout(new BorderLayout());

        modeloProntos = new DefaultListModel<>();
        listaProntos = new JList<>(modeloProntos);
        listaProntos.setFont(new Font("Arial", Font.BOLD, 24));
        listaProntos.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JScrollPane scrollPane = new JScrollPane(listaProntos);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Pedidos Prontos para Retirada"));
        
        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
        atualizar(); 
    }

    @Override
    public void atualizar() {
        SwingUtilities.invokeLater(() -> {
            modeloProntos.clear();
            List<Pedido> pedidosProntos = GerenciadorPedidos.getInstancia().getPedidosProntos();
            for (Pedido p : pedidosProntos) {
                modeloProntos.addElement(p.getCliente().getNome());
            }
        });
    }
}
