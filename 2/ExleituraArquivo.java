import java.io.BufferedReader;
import java.io.FileReader;

public class ExleituraArquivo{
    
    public static void main(String[] args) throws Exception{
        String nomeArquivo = "ArquivoEntrada.csv";
        BufferedReader arqLeitura = new BufferedReader(
            new FileReader(nomeArquivo));

        String linha;
        while ((linha = arqLeitura.readLine()) != null) {
            System.out.println(linha);
        }
        arqLeitura.close();
    
        arqLeitura = new BufferedReader(new FileReader(nomeArquivo));
        linha = arqLeitura.readLine();
        while ((linha = arqLeitura.readLine()) != null) {
            String vetCampos[] = linha.split(";");
            System.out.println("Aluno: " +vetCampos[0]);
        }    
        arqLeitura.close();

    }

}