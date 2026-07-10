package observer;

import main.Pedido;
public class Cozinha implements Observador {
    @Override
    public void atualizar(Pedido pedido) {
        System.out.println("COZINHA NOTIFICADA: Preparar novo pedido.\n" + pedido.getResumo() + "\n");
    }
}
