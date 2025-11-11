import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;

public class Programa{
    public static void main(String[] args) throws Exception{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int opc;
            System.out.println("---TABELA DE PREÇOS---");
            String nomeEntrada = informeEntrada(); // pede o arquivo de entrada
            do{
               System.out.println("[1] Consultar preço de venda");
               System.out.println("[2] Consultar itens com estoque inferior a 10");
               System.out.println("[3] Gerar arquivo com preços de venda");
               System.out.println("[4] Gerar arquivo de reposição (estoque < 10)");
               System.out.println("[5] Sair");

                opc = Integer.parseInt(reader.readLine());
                
                switch (opc){
                case 1: consultarVenda(nomeEntrada); //método para consultar a margem de lucro acima dos produtos
                break;
                case 2: consultarCompra(nomeEntrada); //método para consultar itens que precisam ser comprados (inferior a 10 no estoque)
                break; 
                case 3: gerarArquivoVenda(nomeEntrada); //método para gerar csv da venda
                break;
                case 4: gerarArquivoReposicao(nomeEntrada); //método para gerar csv da reposição
                break;
                case 5: System.err.println("SAINDO...");
                break;
                default:  
                    System.out.println("ERRO: Entrada Invalida!!");
                break;
                
                }
            }while(opc != 5);
    }

    public static String informeEntrada() throws Exception{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Informe o nome do arquivo de entrada: ");
        String nomeEntrada = reader.readLine(); //le o nome da entrada

        if(nomeEntrada.equals("")) nomeEntrada = "preco_custo.csv"; //nome padrão caso não seja informado
        return nomeEntrada;
    }

   public static void consultarVenda(String nomeEntrada) throws Exception{
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    BufferedReader csvReader = new BufferedReader(new FileReader(nomeEntrada));

    String linha;

    // Remove o cabeçalho
    csvReader.readLine();

    System.out.println("\n--- LISTA DE PRODUTOS (PREÇO DE CUSTO) ---");
    System.out.printf("%-6s %-8s %-25s %-12s %-12s\n", 
        "COD", "ESTOQUE", "PRODUTO", "PREÇO", "CATEGORIA");

    while((linha = csvReader.readLine()) != null){
        String[] campos = linha.split(";");
        System.out.printf("%-6s %-8s %-25s %-12s %-12s\n", 
            campos[0], campos[1], campos[2], campos[3], campos[4]);
    }
    csvReader.close();

    System.out.print("\nDigite a % de lucro: ");
    double lucro = Double.parseDouble(reader.readLine());

    System.out.println("\n--- PREÇOS COM LUCRO ---");
    System.out.printf("%-6s %-25s %-12s %-12s\n", "COD", "PRODUTO", "CUSTO", "VENDA");

    csvReader = new BufferedReader(new FileReader(nomeEntrada));

    // remove o cabeçalho de novo
    csvReader.readLine();

    while((linha = csvReader.readLine()) != null){
        String[] campos = linha.split(";");
        double custo = Double.parseDouble(campos[3].replace(",", "."));
        double venda = custo + (custo * (lucro / 100));

        System.out.printf("%-6s %-25s %-12.2f %-12.2f\n", 
            campos[0], campos[2], custo, venda);
    }
    csvReader.close();
}


    public static void consultarCompra(String nomeEntrada) throws Exception {
    BufferedReader csvReader = new BufferedReader(new FileReader(nomeEntrada));
    String linha;

    // Remove o cabeçalho
    csvReader.readLine();

    System.out.println("\n--- ITENS QUE PRECISAM SER REPOSTOS (ESTOQUE < 10) ---");
    System.out.printf("%-6s %-8s %-25s %-12s\n", "COD", "ESTOQUE", "PRODUTO", "CATEGORIA");

    while((linha = csvReader.readLine()) != null){
        String[] campos = linha.split(";");

        int estoque = Integer.parseInt(campos[1]);
        if(estoque < 10){
            System.out.printf("%-6s %-8d %-25s %-12s\n", 
                campos[0], estoque, campos[2], campos[4]);
        }
    }
    csvReader.close();
}

public static void gerarArquivoVenda(String nomeEntrada) throws Exception {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    BufferedReader csvReader = new BufferedReader(new FileReader(nomeEntrada));

    System.out.print("\nDigite a % de lucro para gerar novo arquivo: ");
    double lucro = Double.parseDouble(reader.readLine());

    BufferedWriter writer = new BufferedWriter(new FileWriter("preco_venda.csv"));

    writer.write("codigo;produto;preco_custo;preco_venda\n");

    String linha;
    csvReader.readLine(); //pula cabeçalho

    while((linha = csvReader.readLine()) != null){
        String[] campos = linha.split(";");
        double custo = Double.parseDouble(campos[3].replace(",", "."));
        double venda = custo + (custo * (lucro / 100));

        writer.write(campos[0] + ";" + campos[2] + ";" + String.format("%.2f", custo) + ";" + String.format("%.2f", venda) + "\n");
    }

    csvReader.close();
    writer.close();

    System.out.println("\nArquivo gerado: preco_venda.csv");
}

public static void gerarArquivoReposicao(String nomeEntrada) throws Exception {
    BufferedReader csvReader = new BufferedReader(new FileReader(nomeEntrada));
    BufferedWriter writer = new BufferedWriter(new FileWriter("repor_estoque.csv"));

    writer.write("codigo;estoque;produto;categoria\n");

    String linha;
    csvReader.readLine(); // pula cabeçalho

    while((linha = csvReader.readLine()) != null){
        String[] campos = linha.split(";");
        int estoque = Integer.parseInt(campos[1]);

        if(estoque < 10){
            writer.write(campos[0] + ";" + campos[1] + ";" + campos[2] + ";" + campos[4] + "\n");
        }
    }

    csvReader.close();
    writer.close();

    System.out.println("\nArquivo gerado: repor_estoque.csv");
}




}