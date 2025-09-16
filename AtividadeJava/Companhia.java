public class Companhia {
    private String nome;
    private String tel;
    private int qtdVoos;
    private Voo vetorVoos[];

    public Companhia(){
        this.vetorVoos = new Voo[10];
        this.qtdVoos = 0;
    }

    /* Getter, Setter Nome */
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    /* Getter, Setter tel */
    public String getTel() {
        return tel;
    }
    public void setTel(String tel) {
        this.tel = tel;
    }

    /* Getter, Setter qtdVoos */
    public int getQtdVoos() {
        return qtdVoos;
    }
    public void setQtdVoos(int qtdVoos) {
        if(this.qtdVoos < 10){
            this.qtdVoos += qtdVoos;
        }
    }

    public Voo getVoo(int pos) {
        return vetorVoos[pos];
    }

    public void setVoos(Voo voo) {
         if(voo == null){
            System.out.println("Voo inválido");
            return;
        }

        if(qtdVoos < 10){
            this.vetorVoos[qtdVoos] = voo;
            qtdVoos++;

        } else{
            System.out.println("Limite de Vôos atingido");
        }
    }
    
}