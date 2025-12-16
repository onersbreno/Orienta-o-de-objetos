import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

public class Sistema {
    static BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in)); 
    public static void main(String[] args) throws Exception {
        Transportadora transp = new Transportadora();
        limparTela();

        boolean configuracaoCarregada = false; //Boleano da configuração
        String arqConfig = ""; //Declaraçao do arquivo de configuração

        do{
            try{
                System.out.println("Arquivo de configuração(.csv): ");
                arqConfig = teclado.readLine() + ".csv"; 
                transp.carregarConfiguracoes(arqConfig);
                configuracaoCarregada = true;

            }catch(FileNotFoundException e){ 
                System.out.println("ERRO: Arquivo '" + arqConfig + " ' não encontrado na pasta 'Dados/'.");
                
            }

        }while(!configuracaoCarregada); //Somente sai do laço caso configuracaoCarregada seja verdadeiro, caso ele ache o arquivo. 

        String opcao;
        do{
            System.out.println("- - - - MENU - - - - ");
            System.out.println("[1] - Importar arquivo de encomendas");
            System.out.println("[2] - Listar encomendas normais");
            System.out.println("[3] - Listar encomendas expressas");
            System.out.println("[0] - Sair");
            System.out.print("Opção: ");
            opcao = teclado.readLine();

            limparTela();
            
            switch (opcao) {
                case "1":
                    boolean arquivoValido = false;
                    String arqDados = "";

                    do {
                        try {
                            System.out.print("Arquivo de dados(.csv): ");
                            arqDados = teclado.readLine() + ".csv";
                            transp.importarDados(arqDados); 
                            arquivoValido = true;

                        }catch (FileNotFoundException e){ //Exceção em caso de não existir o arquivo digitado em Dados/
                            System.out.println("ERRO: Arquivo '" + arqDados + "' não encontrado na pasta 'Dados/'.");
                        }

                    }while (!arquivoValido); //Apenas se o Boleano for True
                    break;
                
                case "2":
                    transp.listarNormais();
                    break;
                
                case "3":
                    transp.listarExpressas();
                    break;
                
                case "0":
                    System.out.println("Bye....");
                    break;

                default:
                    System.out.println("Opção invalida!");
            }

        }while (!opcao.equals("0"));
    }

    static void limparTela() {
        try { //Tenta limpar a tela 
            new ProcessBuilder("clear").inheritIO().start().waitFor();
        } catch (Exception e) { //Se não conseguir repete o \n 40 vezes.
            System.out.println("\n".repeat(40));
        } //Fiz dessa forma pois estou programando no Windows.
    }
}

