# Sistema de Pedidos de Lanchonete (POO em Java)

Aplicação desktop em **Java + Swing** desenvolvida para a disciplina de **Programação Orientada a Objetos**.  
O sistema simula o fluxo de pedidos de uma lanchonete, com telas separadas para:

- Fazer o pedido (atendimento/caixa)
- Acompanhar pedidos na cozinha
- Painel de retirada para exibir pedidos prontos

Tudo estruturado com **POO**, uso de **interfaces**, **herança**, **coleções** e alguns **padrões de projeto** (Singleton, Observer, Strategy).

---

## Funcionalidades

- Cadastro rápido do cliente (nome)
- Montagem do pedido com:
  - Hambúrgueres: X-Burger, X-Tudo, Hot Dog
  - Bebidas: Água, Pepsi, Guaraná, sucos
  - Sobremesas: Sorvete (sabores), Brownie, Milkshakes
- Campo de **observações** do pedido
- Cálculo automático do **total**
- Escolha da forma de pagamento:
  - Dinheiro
  - Cartão
  - Pix
- Exibição de um **resumo detalhado** do pedido
- Gerenciamento do fluxo do pedido:
  - Em espera → Em preparo → Pronto para retirada
- Telas específicas para:
  - **Cozinha**: visualizar pedidos em espera, mover para preparo, finalizar
  - **Retirada**: listar nomes dos clientes com pedidos prontos

---

## Conceitos de POO utilizados

O projeto foi pensado para praticar os principais conceitos de Programação Orientada a Objetos:

- **Encapsulamento**  
  Classes bem definidas para cliente, pedido e produtos.

- **Herança**  
  - `Produto` (abstrata) como base para:
    - `Lanche`
    - `Bebida`
    - `Sobremesa`
  - E a partir delas, classes concretas como `XBurger`, `XTudo`, `HotDog`, `Agua`, `Pepsi`, `Brownie`, etc.

- **Polimorfismo**  
  - Lista de produtos (`List<Produto>`) dentro de `Pedido`
  - Interface `Pagamento` com implementações diferentes:
    - `PagamentoDinheiro`
    - `PagamentoCartao`
    - `PagamentoPix`

- **Coleções e Generics**
  - Uso de `List<Pedido>` para gerenciar:
    - Pedidos em espera
    - Pedidos em preparo
    - Pedidos prontos

---

## Padrões de Projeto

Alguns padrões clássicos aparecem no projeto:

- **Singleton**  
  - `GerenciadorPedidos` é uma classe com única instância centralizando o controle dos pedidos.

- **Observer**  
  - A lista de pedidos é observada por telas como:
    - `TelaCozinha`
    - `TelaRetirada`  
  Quando os pedidos mudam de estado, os observadores são notificados e atualizam a interface.

- **Strategy**  
  - A forma de pagamento é escolhida em tempo de execução:
    - A interface `Pagamento` define o contrato
    - Cada classe concreta implementa uma estratégia de pagamento diferente.

---

## Estrutura do Projeto

Organização sugerida de pastas:

```text
src/
 ├── Main.java
 ├── GerenciadorPedidos.java
 ├── TelaPedido.java
 ├── TelaCozinha.java
 ├── TelaRetirada.java
 ├── main/
 │    ├── Cliente.java
 │    └── Pedido.java
 ├── produtos/
 │    ├── Produto.java
 │    ├── Lanche.java
 │    ├── Bebida.java
 │    ├── Sobremesa.java
 │    ├── XBurger.java
 │    ├── XTudo.java
 │    ├── HotDog.java
 │    ├── Agua.java
 │    ├── Pepsi.java
 │    ├── Guarana.java
 │    ├── SucoMaracuja.java
 │    ├── SucoLaranja.java
 │    ├── Brownie.java
 │    ├── SorveteBaunilha.java
 │    ├── SorveteChocolate.java
 │    ├── MilkshakeMorango.java
 │    └── MilkshakeChocolate.java
 ├── pagamento/
 │    ├── Pagamento.java
 │    ├── PagamentoDinheiro.java
 │    ├── PagamentoCartao.java
 │    └── PagamentoPix.java
 └── observer/
      ├── Observador.java
      ├── ObservadorPedidos.java
      └── Cozinha.java

