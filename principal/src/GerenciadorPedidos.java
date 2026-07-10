import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import main.Pedido;
import observer.ObservadorPedidos;

public class GerenciadorPedidos {
    private static GerenciadorPedidos instancia;
    private List<Pedido> pedidosEmEspera;
    private List<Pedido> pedidosEmPreparo;
    private List<Pedido> pedidosProntos;
    private List<ObservadorPedidos> observadores;

    private GerenciadorPedidos() {
        pedidosEmEspera = new ArrayList<>();
        pedidosEmPreparo = new ArrayList<>();
        pedidosProntos = new ArrayList<>();
        observadores = new ArrayList<>();
    }

    public static synchronized GerenciadorPedidos getInstancia() {
        if (instancia == null) {
            instancia = new GerenciadorPedidos();
        }
        return instancia;
    }

    public void adicionarObservador(ObservadorPedidos obs) {
        observadores.add(obs);
    }

    private void notificarObservadores() {
        for (ObservadorPedidos obs : observadores) {
            obs.atualizar();
        }
    }

    public void adicionarPedido(Pedido pedido) {
        if (pedido != null) {
            pedidosEmEspera.add(pedido);
            System.out.println("COZINHA NOTIFICADA: Novo pedido em espera.");
            notificarObservadores();
        }
    }

    public void iniciarPreparo(Pedido pedido) {
        if (pedido != null && pedidosEmEspera.remove(pedido)) {
            pedidosEmPreparo.add(pedido);
            System.out.println("COZINHA NOTIFICADA: Pedido movido para preparo.");
            notificarObservadores();
        }
    }

    public void finalizarPreparo(Pedido pedido) {
        if (pedido != null && pedidosEmPreparo.remove(pedido)) {
            pedidosProntos.add(pedido);
            System.out.println("COZINHA NOTIFICADA: Pedido pronto para retirada.");
            notificarObservadores();
        }
    }

    public List<Pedido> getPedidosEmEspera() {
        return Collections.unmodifiableList(pedidosEmEspera);
    }

    public List<Pedido> getPedidosEmPreparo() {
        return Collections.unmodifiableList(pedidosEmPreparo);
    }

    public List<Pedido> getPedidosProntos() {
        return Collections.unmodifiableList(pedidosProntos);
    }
}
