public class EncomendaNormal {
 
    private int numeroPedido;
    private String dataPostagem;
    private double peso;
    private double precokg;

    public EncomendaNormal(int numeroPedido, String dataPostagem, double peso, double precokg){
        this.numeroPedido = numeroPedido;
        this.dataPostagem = dataPostagem;
        this.peso = peso;
        this.precokg = precokg;
    }

    //Metodos 
    public double calcularFrete(){
        return peso * precokg;
    }

    
    //Getters e Setters 
    
    //Numero do pedido 
    public int getNumeroPedido() {
        return numeroPedido;
    }

    public void setNumeroPedido(int numeroPedido) {
        this.numeroPedido = numeroPedido;
    }

    //Data de Postagem.
    public String getDataPostagem() {
        return dataPostagem;
    }

    public void setDataPostagem(String dataPostagem) {
        this.dataPostagem = dataPostagem;
    }

    //Peso
    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    //Preço do Kilograma
    public double getPrecokg() {
        return precokg;
    }

    public void setPrecokg(double precokg) {
        this.precokg = precokg;
    }
   
}
