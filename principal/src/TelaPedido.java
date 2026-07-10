import javax.swing.*;
import java.awt.*;
import produtos.*; 
import pagamento.*; 
import main.Cliente;
import main.Pedido;

public class TelaPedido extends JFrame {
    private JTextField campoNome;
    private JTextArea areaResumo;
    private JTextArea areaObservacoes;
    private Pedido pedido;

    public TelaPedido() {
        super("Sistema de Pedidos");


        setSize(800, 600); 
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));


        JPanel painelCliente = new JPanel(new GridLayout(2, 2, 5, 5)); 
        painelCliente.setBorder(BorderFactory.createTitledBorder("Dados do Cliente"));
        painelCliente.add(new JLabel("Nome:"));
        campoNome = new JTextField(20); 
        painelCliente.add(campoNome);
        
        JPanel painelObservacoes = new JPanel(new BorderLayout());
        painelObservacoes.setBorder(BorderFactory.createTitledBorder("Observações (opcional)"));
        areaObservacoes = new JTextArea(3, 20);
        areaObservacoes.setLineWrap(true);
        areaObservacoes.setWrapStyleWord(true);
        JScrollPane scrollObs = new JScrollPane(areaObservacoes);
        painelObservacoes.add(scrollObs, BorderLayout.CENTER);



        JPanel painelProdutos = new JPanel(new GridLayout(0, 3, 10, 10));
        painelProdutos.setBorder(BorderFactory.createTitledBorder("Faça seu Pedido!"));

        JPanel painelSuperior = new JPanel(new BorderLayout(5, 5));
        painelSuperior.add(painelCliente, BorderLayout.NORTH);
        painelSuperior.add(painelObservacoes, BorderLayout.CENTER);

        JPanel painelHamburgueres = new JPanel(new GridLayout(0, 1, 5, 5));
        painelHamburgueres.setBorder(BorderFactory.createTitledBorder("Lanches"));
        JButton btnXBurguer = new JButton("X-Burguer");
        JButton btnXTudo = new JButton("X-Tudo");
        JButton btnHotDog = new JButton("Hot Dog");
        painelHamburgueres.add(btnXBurguer);
        painelHamburgueres.add(btnXTudo);
        painelHamburgueres.add(btnHotDog);


        JPanel painelBebidas = new JPanel(new GridLayout(0, 1, 5, 5));
        painelBebidas.setBorder(BorderFactory.createTitledBorder("Bebidas"));
        JButton btnAgua = new JButton("Água");
        JButton btnPepsi = new JButton("Pepsi");
        JButton btnSuco = new JButton("Suco"); 
        painelBebidas.add(btnAgua);
        painelBebidas.add(btnPepsi);
        painelBebidas.add(btnSuco);


        JPanel painelSobremesas = new JPanel(new GridLayout(0, 1, 5, 5));
        painelSobremesas.setBorder(BorderFactory.createTitledBorder("Sobremesas"));
        JButton btnSorvete = new JButton("Sorvete"); 
        JButton btnBrownie = new JButton("Brownie");
        JButton btnMilkshake = new JButton("Milkshake"); 
        painelSobremesas.add(btnSorvete);
        painelSobremesas.add(btnBrownie);
        painelSobremesas.add(btnMilkshake);

        btnBrownie.addActionListener(e -> adicionarProduto(new Brownie()));

        painelProdutos.add(painelHamburgueres);
        painelProdutos.add(painelBebidas);
        painelProdutos.add(painelSobremesas);


        areaResumo = new JTextArea(15, 40); 
        areaResumo.setEditable(false);
        areaResumo.setFont(new Font("Monospaced", Font.PLAIN, 14)); 
        JScrollPane scroll = new JScrollPane(areaResumo);
        scroll.setBorder(BorderFactory.createTitledBorder("Resumo do Pedido"));


        JButton btnFinalizar = new JButton("Finalizar Pedido");
        btnFinalizar.setFont(new Font("Arial", Font.BOLD, 16));

 
        add(painelSuperior, BorderLayout.NORTH);
        add(painelProdutos, BorderLayout.CENTER);
        add(scroll, BorderLayout.EAST);
        add(btnFinalizar, BorderLayout.SOUTH);


        btnXBurguer.addActionListener(e -> adicionarProduto(new XBurger()));
        btnXTudo.addActionListener(e -> adicionarProduto(new XTudo()));
        btnHotDog.addActionListener(e -> adicionarProduto(new HotDog()));
        btnAgua.addActionListener(e -> adicionarProduto(new Agua()));
        btnPepsi.addActionListener(e -> adicionarProduto(new Pepsi()));

        btnSuco.addActionListener(e -> showSucoOptions());
        btnSorvete.addActionListener(e -> showSorveteOptions());
        btnMilkshake.addActionListener(e -> showMilkshakeOptions());

        btnFinalizar.addActionListener(e -> finalizarPedido());

        setVisible(true);
    }

    private void adicionarProduto(Produto p) {
        if (pedido == null) {
            String nome = campoNome.getText().trim();
            if (nome.isEmpty()){
                JOptionPane.showMessageDialog(this, "Por favor, preencha o nome do cliente para iniciar o pedido.");
                return;
            }
            pedido = new Pedido(new Cliente(nome));
        }
        pedido.adicionarProduto(p);
        atualizarResumoPedido();
    }

    private void showSucoOptions() {
        String[] opcoesSuco = {"Maracujá", "Laranja"};
        String escolha = (String) JOptionPane.showInputDialog(
                this,
                "Selecione o sabor do suco:",
                "Escolher Suco",
                JOptionPane.PLAIN_MESSAGE,
                null,
                opcoesSuco,
                opcoesSuco[0]
        );
        if (escolha != null) {
            if (escolha.equals("Maracujá")) {
                adicionarProduto(new SucoMaracuja());
            } else if (escolha.equals("Laranja")) {
                adicionarProduto(new SucoLaranja());
            }
        }
    }

    private void showSorveteOptions() {
        String[] opcoesSorvete = {"Baunilha", "Chocolate"};
        String escolha = (String) JOptionPane.showInputDialog(
                this,
                "Selecione o sabor do sorvete:",
                "Escolher Sorvete",
                JOptionPane.PLAIN_MESSAGE,
                null,
                opcoesSorvete,
                opcoesSorvete[0]
        );
        if (escolha != null) {
            if (escolha.equals("Baunilha")) {
                adicionarProduto(new SorveteBaunilha());
            } else if (escolha.equals("Chocolate")) {
                adicionarProduto(new SorveteChocolate());
            }
        }
    }

    private void showMilkshakeOptions() {
        String[] opcoesMilkshake = {"Morango", "Chocolate"};
        String escolha = (String) JOptionPane.showInputDialog(
                this,
                "Selecione o sabor do milkshake:",
                "Escolher Milkshake",
                JOptionPane.PLAIN_MESSAGE,
                null,
                opcoesMilkshake,
                opcoesMilkshake[0]
        );
        if (escolha != null) {
            if (escolha.equals("Morango")) {
                adicionarProduto(new MilkshakeMorango());
            } else if (escolha.equals("Chocolate")) {
                adicionarProduto(new MilkshakeChocolate());
            }
        }
    }

    private void atualizarResumoPedido() {
        if (pedido != null) {
            areaResumo.setText(pedido.getResumo());
        } else {
            areaResumo.setText("");
        }
    }

    private void finalizarPedido() {
        if (pedido == null || pedido.calcularTotal() == 0) {
            JOptionPane.showMessageDialog(this, "Nenhum item foi adicionado ao pedido.");
            return;
        }
        pedido.setObservacoes(areaObservacoes.getText().trim());
        

        String[] opcoesPagamento = {"Dinheiro", "Cartão", "Pix"};
        String escolhaPagamento = (String) JOptionPane.showInputDialog(
                this, "Selecione a forma de pagamento:", "Finalizar Pedido",
                JOptionPane.PLAIN_MESSAGE, null, opcoesPagamento, opcoesPagamento[0]);

        if (escolhaPagamento == null) {
            return;
        }
        
        
       

        Pagamento formaPagamento;
        switch (escolhaPagamento) {
            case "Cartão":
                formaPagamento = new PagamentoCartao();
                break;
            case "Pix":
                formaPagamento = new PagamentoPix();
                break;
            default:
                formaPagamento = new PagamentoDinheiro();
                break;
        }

        String mensagemPagamento = formaPagamento.pagar(pedido.calcularTotal());
        JOptionPane.showMessageDialog(this, mensagemPagamento + "\n\n" + pedido.getResumo()); 


GerenciadorPedidos.getInstancia().adicionarPedido(pedido);


        pedido = null;
        campoNome.setText("");
        areaObservacoes.setText(""); 
        atualizarResumoPedido();
    }}



