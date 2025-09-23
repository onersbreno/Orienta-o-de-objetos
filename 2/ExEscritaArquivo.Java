import java.io.BufferedWriter;
import java.io.FileWriter

public class ExEscritaArquivo{

    public static void main(String[] args) throws Exception {
        String nomeArqSaida = "arqSaida.csv";
        BufferedWriter arqGravacao = new BufferedWriter(
            new FileWriter(nomeArqSaida));

        String Linha = "Produto;Preço;Estoque";
        arqGravacao.write(linha);
        arqGravacao.newLine();

        String nomeProduto = "Mouse Dell";
        String preco = "112,50";
        String estoque = "22";
        linha = nomeProduto + preco + estoque;
        arqGravacao.write(linha);
        
        String nomeProduto = "Notebook Acer";
        String preco = "112,50";
        String estoque = "22";
        linha = nomeProduto + preco + estoque;
        arqGravacao.write(linha);

        String nomeProduto = "Cabo HDMI";
        String preco = "112,50";
        String estoque = "22";
        linha = nomeProduto + preco + estoque;
        arqGravacao.write(linha);
        
    }

}