import javax.swing.*;
import java.awt.*;
import main.Pedido;
import observer.ObservadorPedidos;
import java.util.List;

public class TelaCozinha extends JFrame implements ObservadorPedidos {

    private DefaultListModel<Pedido> modeloEspera;
    private DefaultListModel<Pedido> modeloPreparo;
    private DefaultListModel<Pedido> modeloPronto;
    private JList<Pedido> listaEspera;
    private JList<Pedido> listaPreparo;
    private JList<Pedido> listaPronto;

    public TelaCozinha() {
        super("Painel da Cozinha");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));


        JPanel painelListas = new JPanel(new GridLayout(1, 3, 10, 10));


        modeloEspera = new DefaultListModel<>();
        listaEspera = new JList<>(modeloEspera);
        painelListas.add(criarPainelColuna("Em Espera", listaEspera));


        modeloPreparo = new DefaultListModel<>();
        listaPreparo = new JList<>(modeloPreparo);
        painelListas.add(criarPainelColuna("Em Preparo", listaPreparo));

  
        modeloPronto = new DefaultListModel<>();
        listaPronto = new JList<>(modeloPronto);
        painelListas.add(criarPainelColuna("Pronto", listaPronto));

        add(painelListas, BorderLayout.CENTER);


        JPanel painelBotoes = new JPanel(new FlowLayout());
        JButton btnPreparar = new JButton("Mover para Preparo");
        JButton btnFinalizar = new JButton("Mover para Pronto");

        painelBotoes.add(btnPreparar);
        painelBotoes.add(btnFinalizar);
        add(painelBotoes, BorderLayout.SOUTH);


        btnPreparar.addActionListener(e -> moverPedidoParaPreparo());
        btnFinalizar.addActionListener(e -> moverPedidoParaPronto());
        

        listaEspera.setCellRenderer(new PedidoListCellRenderer());
        listaPreparo.setCellRenderer(new PedidoListCellRenderer());
        listaPronto.setCellRenderer(new PedidoListCellRenderer());


        setVisible(true);
        atualizar(); 
    }

    private JPanel criarPainelColuna(String titulo, JList<Pedido> lista) {
        JPanel painel = new JPanel(new BorderLayout());
        painel.setBorder(BorderFactory.createTitledBorder(titulo));
        lista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(lista);
        painel.add(scrollPane, BorderLayout.CENTER);
        return painel;
    }

    private void moverPedidoParaPreparo() {
        Pedido pedidoSelecionado = listaEspera.getSelectedValue();
        if (pedidoSelecionado != null) {
            GerenciadorPedidos.getInstancia().iniciarPreparo(pedidoSelecionado);
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um pedido na coluna 'Em Espera'.");
        }
    }

    private void moverPedidoParaPronto() {
        Pedido pedidoSelecionado = listaPreparo.getSelectedValue();
        if (pedidoSelecionado != null) {
            GerenciadorPedidos.getInstancia().finalizarPreparo(pedidoSelecionado);
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um pedido na coluna 'Em Preparo'.");
        }
    }

    @Override
    public void atualizar() {
        SwingUtilities.invokeLater(() -> {

            modeloEspera.clear();
            modeloPreparo.clear();
            modeloPronto.clear();


            GerenciadorPedidos gerenciador = GerenciadorPedidos.getInstancia();
            List<Pedido> emEspera = gerenciador.getPedidosEmEspera();
            List<Pedido> emPreparo = gerenciador.getPedidosEmPreparo();
            List<Pedido> prontos = gerenciador.getPedidosProntos();


            emEspera.forEach(modeloEspera::addElement);
            emPreparo.forEach(modeloPreparo::addElement);
            prontos.forEach(modeloPronto::addElement);
        });
    }
    

    class PedidoListCellRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {



            JTextArea textArea = new JTextArea();
            textArea.setText(((Pedido) value).getResumo());
            textArea.setWrapStyleWord(true);
            textArea.setLineWrap(true);
            textArea.setOpaque(true);
            textArea.setEditable(false);
            textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
            
            if (isSelected) {
                textArea.setBackground(list.getSelectionBackground());
                textArea.setForeground(list.getSelectionForeground());
            } else {
                textArea.setBackground(list.getBackground());
                textArea.setForeground(list.getForeground());
            }

            return textArea;
        }
    }
}
