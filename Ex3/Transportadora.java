import java.io.BufferedReader;
import java.io.FileReader;

public class Transportadora implements ImportacaoArquivos {
    public static int tamanho = 1000;

    // Vetores de encomendas
    private EncomendaNormal normais[] = new EncomendaNormal[tamanho];
    private EncomendaExpressa expressas[] = new EncomendaExpressa[tamanho];

    // Índices de controle
    private int indiceNormal = 0;
    private int indiceExpressa = 0;

    // Preços por kg
    private double precoNormalKg;
    private double precoExpressoKg;

    public void carregarConfiguracoes(String arquivo) throws Exception {

        BufferedReader br = new BufferedReader(new FileReader("Dados/" + arquivo));
        br.readLine(); // pula cabeçalho

        String linha;
        while ((linha = br.readLine()) != null) {

            String[] col = linha.split(";");
            String tipo = col[0];
            double preco = Double.parseDouble(col[2]);

            if (tipo.equals("Normal")) {
                precoNormalKg = preco;
            } else if (tipo.equals("Expressa")) {
                precoExpressoKg = preco;
            } else {
                System.out.println("Tipo de Entrega não cadastrado!");
            }
        }

        br.close();
        System.out.println("Configurações carregadas.\n");
    }

    public void importarDados(String arquivo) throws Exception {

        BufferedReader br = new BufferedReader(new FileReader("Dados/" + arquivo));
        br.readLine(); // pula cabeçalho

        String linha;
        while ((linha = br.readLine()) != null) {

            String[] col = linha.split(";");

            int numero = Integer.parseInt(col[0]);
            String data = col[1];
            String tipo = col[2];
            String prazoTxt = col.length > 3 ? col[3] : "";
            double peso = Double.parseDouble(col[4]);
            String telefone = col.length > 5 ? col[5] : "";

            // Encomenda Normal
            if (tipo.equals("EN")) {

                if (indiceNormal >= tamanho) {
                    System.out.println("AVISO: Limite de " + tamanho + " encomendas normais atingido. Pedido " + numero + " ignorado.");
                    continue;
                }

                normais[indiceNormal] = new EncomendaNormal(numero, data, peso, precoNormalKg);
                indiceNormal++;
            }
            // Encomenda Expressa
            else if (tipo.equals("EE")) {

                if (indiceExpressa >= tamanho) {
                    System.out.println("AVISO: Limite de " + tamanho + " encomendas expressas atingido. Pedido " + numero + " ignorado.");
                    continue;
                }

                int prazo = prazoTxt.equals("") ? 0 : Integer.parseInt(prazoTxt);
                expressas[indiceExpressa] = new EncomendaExpressa(numero, data, peso, precoExpressoKg, prazo, telefone);
                indiceExpressa++;
            } 
            else {
                System.err.println("Encomenda não registrada.");
            }
        }

        br.close();
        System.out.println("Arquivo importado.\n");
    }

    public void listarNormais() {

        if (indiceNormal == 0) {
            System.out.println("Nenhuma encomenda normal carregada.\n");
            return;
        }

        System.out.println("\n- - - ENCOMENDAS NORMAIS - - -");
        System.out.printf("%10s | %12s | %10s%n", "Nro Pedido", "Peso (KG)", "Frete");

        for (int i = 0; i < indiceNormal; i++) {
            EncomendaNormal n = normais[i];
            System.out.printf("%10d | %12.2f | R$ %8.2f%n",
                    n.getNumeroPedido(), n.getPeso(), n.calcularFrete());
        }

        System.out.println();
    }

    public void listarExpressas() {

        if (indiceExpressa == 0) {
            System.out.println("Nenhuma encomenda expressa carregada.\n");
            return;
        }

        System.out.println("\n- - - ENCOMENDAS EXPRESSAS - - -");
        System.out.printf("%10s | %12s | %10s%n", "Nro Pedido", "Peso (KG)", "Frete");

        for (int i = 0; i < indiceExpressa; i++) {
            EncomendaExpressa e = expressas[i];
            System.out.printf("%10d | %12.2f | R$ %8.2f%n",
                    e.getNumeroPedido(), e.getPeso(), e.calcularFrete());
        }

        System.out.println();
    }

    public void limparEncomendas() {

        for (int i = 0; i < tamanho; i++) {
            normais[i] = null;
            expressas[i] = null;
        }

        indiceNormal = 0;
        indiceExpressa = 0;

        System.out.println("Todos os dados de encomendas foram apagados.\n");
    }
}
