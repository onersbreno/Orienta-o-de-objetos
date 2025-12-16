public class EncomendaExpressa extends EncomendaNormal{
    private int prazoEntrega;
    private String telefone;   

    public EncomendaExpressa(int numeroPedido, String dataPostagem, double peso, double precoKg, int prazoEntrega, String telefone) { //Construtor da classe.
        super(numeroPedido, dataPostagem, peso, precoKg);
        this.prazoEntrega = prazoEntrega;
        this.telefone = telefone;
    }

  
    @Override
    public double calcularFrete(){
        double valor = getPeso() * getPrecokg();

        if (prazoEntrega <= 2){ //Caso o prazo menor até 2 dias, 25% de acrescimo no valor.
            valor = valor * 1.25;
        }
        return valor;
    }

    //Getters e Setters
    //Prazo de Entrega
    public int getPrazoEntrega() {
        return prazoEntrega;
    }

    public void setPrazoEntrega(int prazoEntrega) {
        this.prazoEntrega = prazoEntrega;
    }

    //Telefone
    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    
}
