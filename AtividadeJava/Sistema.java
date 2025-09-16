import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Sistema {
    BufferedReader reader;
    Companhia c1;
    public static void main(String[] args) throws Exception{
        Sistema se = new Sistema(); 
        se.c1 = new Companhia(); 
        se.reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("+==CADASTRO DE COMPANHIA==+");
        System.out.print("|= Nome da companhia: ");
        se.c1.setNome(se.reader.readLine());

        System.out.print("|= Contato(tel): ");
        se.c1.setTel(se.reader.readLine());

        se.menu();
    }

    private void menu() throws Exception{
        String opc = "";
        while(!opc.equals("4")){
            System.out.println("");
            System.out.println("==========MENU==========");
            System.out.println("[1] Cadastrar vôo");
            System.out.println("[2] Listar vôo");
            System.out.println("[3] Consultar vôo");
            System.out.println("[4] Sair");
            System.out.println("========================");
            
            System.out.print("Escolha uma opção: ");
            opc = this.reader.readLine();
            System.out.println("");
            switch (opc) {
                case "1":
                   
                    cadastrarVoo();
                    break;

                case "2":
                
                    listarVoos();
                break;
                
                case "3":
                
                    consultarVoo();
                    break; 

                case "4":
                
                    System.err.println("SAINDO...");
                    break;

                default:
                    System.out.println("Entrada Invalida!!");
                    break;
            }
        }
    }
    private void cadastrarVoo() throws Exception{
        Voo voo = new Voo();
        System.out.println("+=CADASTRO DO VÔO=");
        
       
        System.out.print("|= Informe o numero do vôo: ");
        voo.setNumVoo(Integer.parseInt(reader.readLine()));

        System.out.print("|= Informe o local de embarque do vôo: ");
        voo.setOrigem(reader.readLine());

        System.out.print("|= Informe o destino final do vôo: ");
        voo.setDestino(reader.readLine());

        System.out.print("|= Informe a data de Embarque para o vôo: ");
        voo.setDataEmbarque(reader.readLine());
        
        System.out.print("|= Informe o numero de cadastros a serem realizados: ");
        int numCadastros = (Integer.parseInt(reader.readLine()));

        if(numCadastros < 50){
            System.out.println("+==CADASTROS DO VÔO=");
            for(int i = 0; i < numCadastros; i++){
                Passageiro pax = new Passageiro();
                System.out.println("");

                System.out.println(i+1 + "º Passageiro:");
                System.out.print("|= Nome: ");
                pax.setNome(reader.readLine());

                System.out.print("|= Passaporte: ");
                pax.setPassaporte(reader.readLine());

                System.out.print("|= Peso da Bagagem: ");
                pax.setPesoMala(reader.readLine()); 
                System.out.print("|= Endereço: ");
                pax.setEndereco(reader.readLine()); 

                System.out.print("|= Telefone: ");
                pax.setTel(reader.readLine());

                System.out.println("+===================");

                voo.setPassageiro(pax);
            }
            c1.setVoos(voo);
        }
        else{
            System.out.println("--------------------------------------");
            System.out.println("ERRO: O vôo só comporta 50 passageiros");
            System.out.println("--------------------------------------");
        }

    }

    private void listarVoos(){
        if(c1.getQtdVoos() != 0){
            System.out.println("=LISTA DE VÔOS=");
            for(int i = 0; i < c1.getQtdVoos(); i++){
                Voo voo = c1.getVoo(i);
                System.out.println("Numero: " + voo.getNumVoo() + " - Origem: " + voo.getOrigem() + " - Destino: " + voo.getDestino() + " - Embarque: " + voo.getDataEmbarque() + " - Qtde de passageiros: " + voo.getQtdPassageiros() + " - Assentos livres: " + voo.getAssentosLivres());
            }
            System.out.println("===============");
        }
        else{
            System.out.println("----------------------------");
            System.out.println("ERRO: Nenhum vôo cadastrado!");
            System.out.println("----------------------------");
        }
    }

    private void consultarVoo() throws Exception{
        if(c1.getQtdVoos() != 0){
            System.out.println("+=CONSULTA DE VÔO=");
            System.out.print("|= Informe o numero do vôo: ");
            int numVoo = Integer.parseInt(reader.readLine());
            boolean achou = false;

            for(int i = 0; i < c1.getQtdVoos(); i++){
                Voo voo = c1.getVoo(i);
                if(voo.getNumVoo() == numVoo){
                    System.out.println("+=DADOS DO VÔO=");
                    System.out.println("|= Numero: " + voo.getNumVoo());
                    System.out.println("|= Embargue: " + voo.getOrigem());
                    System.out.println("|= Destino: " + voo.getDestino());
                    System.out.println("|= Data Embarque: " + voo.getDataEmbarque());
                    System.out.println("|= Assentos Livres: " + voo.getAssentosLivres());
                    System.out.println("=-=-=-=-=-=-=-=");


                    System.out.println("+=PASSAGEIROS DO VÔO=");
                    for(int j = 0; j < voo.getQtdPassageiros(); j++){
                        Passageiro pax = voo.getPassageiro(j);
                        System.out.println("|=" + (j+1) + "º Passageiro");
                        System.out.println("|= Nome: " + pax.getNome());
                        System.out.println("|= Passaporte: " + pax.getPassaporte());
                        System.out.println("|= Peso da Bagagem: " + pax.getPesoMala());
                        System.out.println("|= Endereço: " + pax.getEndereco());
                        System.out.println("|= Telefone: " + pax.getTel());
                        System.out.println("|= Assento: " + (j+1));
                        System.out.println("--------------------");
                    }
                    System.out.println("=====================");
                    achou = true;
                    break;
                }
            }
            if(!achou){ 
            System.out.println("---------------------");
            System.out.println("ERRO: Vôo localizado!");
            System.out.println("---------------------");}
        }
        else{
            System.out.println("----------------------------");
            System.out.println("ERRO: Nenhum vôo cadastrado!");
            System.out.println("----------------------------");
        }
    }
}