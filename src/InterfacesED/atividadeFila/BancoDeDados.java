/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfacesED.atividadeFila;

/**
 *
 * @author Matheus Nunes
 */
public class BancoDeDados {

    private Lista<Cliente> clientesDoSistema;
    private Lista<Produto> produtosDoSistema;
    private Fila<Pedido> filaDePedidos;

    public BancoDeDados() {
        this.clientesDoSistema = new Vetor<>();
        this.produtosDoSistema = new Vetor<>();
        this.filaDePedidos = new ListaDuplamenteLigada<>();
    }
    
    public String cadastrarProduto(Produto produto) {
        if (!this.produtosDoSistema.contem(produto)) {
            this.produtosDoSistema.adicionar(produto);
            return "Produto cadastrado no sistema";
        }
        return "Produto ja cadastrado no sistema";
    }
    
    public String cadastrarCliente(Cliente cliente){
        if (cliente.getIdade() < 18){
            return "Cliente menor de idade, portanto nao pode ser cadastrado";
        }
        if (!this.clientesDoSistema.contem(cliente)){
            this.clientesDoSistema.adicionar(cliente);
            return "Cliente cadastrado com sucesso";
        }
        return "Cliente com cpf informado ja cadastrado no sistema";
    }
    
    public String efetuarPedido(Cliente cliente,Lista<Produto> vetorDeProdutos){
        if (!this.clientesDoSistema.contem(cliente)){
            return "Nao existe cliente com cpf informado no sistema";
        }
        
        Cliente clienteDoPedido = this.clientesDoSistema.buscar(this.clientesDoSistema.indexOf(cliente));
        
        Pedido novoPedido = new Pedido(clienteDoPedido, vetorDeProdutos);
        this.filaDePedidos.enfileirar(novoPedido);
        this.removerProdutosQueForamPedidos(vetorDeProdutos);
        return "Pedido adicionado com sucesso";
    }
    
    //Metodo responsavel por remover os produtos que foram efetuado pedido por parte de um cliente
    private void removerProdutosQueForamPedidos(Lista<Produto> vetorDeProdutosPedidos){
        for (Produto produtoAtual : vetorDeProdutosPedidos) {
            this.produtosDoSistema.remover(produtoAtual);
        }
    }
    
    public String despacharPedido(){
        if (this.filaDePedidos.vazia()){
            return "Nao a pedidos a serem despachados";
        }
        
        Pedido pedidoASerDespachado = this.filaDePedidos.desenfileirar();
        Cliente clienteDoPedido = pedidoASerDespachado.getCliente();
        
        clienteDoPedido.adicionarNovoPedidoDespachado(pedidoASerDespachado);
        
        return "Os seguintes produtos foram enviados para o cliente "+ clienteDoPedido.getNome() + ": " + pedidoASerDespachado.getListaDeProdutos().toString();
    }
    
    //Metodo para exibir os pedidos na ordem de cadastro. Os pedidos sera despachados de acordo com a ordem de cadastro
    public String exibirFilaDePedidos(){
         if (this.filaDePedidos.vazia()){
            return "Nao a pedidos a serem listados";
        }
        StringBuilder construtor = new StringBuilder();
        int numeroDePedidos = 1;
        for (Pedido pedido : this.filaDePedidos) {
            construtor.append("Pedido numero ").append(numeroDePedidos++).append("----------------------------------------------\n");
            construtor.append("Cliente: ").append(pedido.getCliente()).append(" | CPF: ").append(pedido.getCliente().getCpf()).append("\n");
            construtor.append("Produtos: ");
            for (int i = 0; i < pedido.getListaDeProdutos().tamanho(); i++) {
                construtor.append(pedido.getListaDeProdutos().buscar(i)).append("\n");
            }
        }
        return construtor.toString();
    }
    
    //Retorna os produtos disponiveis para pedidos contendo suas respectivas posicoes no sistema e suas informacoes
    public String exibirRelatorioDeProdutosDisponiveisParaPedido(){
        StringBuilder construtor = new StringBuilder();
        if (this.produtosDoSistema.tamanho() == 0){
            return "Nao há produtos no sistema";
        }
        for (int i = 0; i < this.numeroDeprodutosNoSistema(); i++) {
            construtor.append((i+1)).append(" - ").append(this.produtosDoSistema.buscar(i).getNome())
                    .append(" ID: ").append(this.produtosDoSistema.buscar(i).getIdDoProduto()).append("\n");
        }
        return  construtor.toString();
    }

    public Produto retornaProduto(int posicao){
        return this.produtosDoSistema.buscar(posicao);
    }
    
    //Retorna a quantidade de produtos disponiveis para serem pedidos no sistema
    public int numeroDeprodutosNoSistema(){
        return this.produtosDoSistema.tamanho();
    }

    public Lista<Produto> getProdutosDoSistema() {
        return produtosDoSistema;
    }
    
    
}
