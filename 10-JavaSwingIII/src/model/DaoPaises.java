package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class DaoPaises {
    private Connection conn;
    private Statement st;

    private void conectar(){
        try {
            this.conn = GerenciadorConexao.pegarConexao(); //Puxa a conexão
            this.st = conn.createStatement(); //Statement: objeto que intermedia a conversa com o banco

        } catch (Exception e){
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void desconectar(){
        try {
            this.st.close(); 
            this.conn.close(); 
        } catch (Exception e){
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public boolean inserir(Veiculo v){
        boolean resultado = false;

        try {
            this.conectar();
            String comando = "INSERT INTO tbveiculos VALUES (" + "Null, '" + v.getMarca() +"', '" + v.getModelo() + "', '" + v.getChassi() + "', " + v.getAno() + ");";

            //System.out.println(comando);

            st.executeUpdate(comando);
            resultado = true;

        } catch (Exception e) {
            System.out.println("Erro ao inserir registro: " + e.getMessage());
        
        } finally{
            this.desconectar();
        }

        return  resultado;
    }

    public ArrayList<Veiculo> buscarTodos(){
        ArrayList<Veiculo> resultados = new ArrayList<>();
        try {
            this.conectar();
            ResultSet rs = st.executeQuery("SELECT * FROM tbveiculos ORDER BY marca "); //ResultSet: Armazena os vindo do banco de dados, em formato de tabela.

            while(rs.next()){ //Vai um por um até o proximo ser vazio, não dá null pointer
                Veiculo v = new Veiculo(); //Dentro do objeto veiculo, ele seta os valores seguinda as tabelas do banco de dados.

                v.setCodigo(rs.getInt("codigo"));
                v.setMarca(rs.getString("marca")); //Tem que ser exatamente o nome que está no banco.
                v.setModelo(rs.getString("modelo"));
                v.setChassi(rs.getString("chassi"));
                v.setAno(rs.getInt("ano")); //Tipo seguindo da classe.

                resultados.add(v); //Coloca o Objeto V presetado dentro de um arraylist.
            }

        } catch (Exception e) {
            System.out.println("Erro ao buscar os registro: " + e.getMessage());
        
        } finally{
            this.desconectar();
        }

        return resultados;
    }
    
    public ArrayList<Veiculo> buscarTodosFiltro(String campo, String filtro){
        ArrayList<Veiculo> resultados = new ArrayList<>();
        
        if(!campo.equals("marca") && !campo.equals("modelo")){
            return resultados;
        }
        
        
        
        
        try {
            this.conectar();
            ResultSet rs = st.executeQuery("SELECT * FROM tbveiculos WHERE " + campo + " LIKE '%" + filtro + "%' ORDER BY marca "); //ResultSet: Armazena os vindo do banco de dados, em formato de tabela.

            while(rs.next()){ //Vai um por um até o proximo ser vazio, não dá null pointer
                Veiculo v = new Veiculo(); //Dentro do objeto veiculo, ele seta os valores seguinda as tabelas do banco de dados.

                v.setCodigo(rs.getInt("codigo"));
                v.setMarca(rs.getString("marca")); //Tem que ser exatamente o nome que está no banco.
                v.setModelo(rs.getString("modelo"));
                v.setChassi(rs.getString("chassi"));
                v.setAno(rs.getInt("ano")); //Tipo seguindo da classe.

                resultados.add(v); //Coloca o Objeto V presetado dentro de um arraylist.
            }

        } catch (Exception e) {
            System.out.println("Erro ao buscar os registro: " + e.getMessage());
        
        } finally{
            this.desconectar();
        }

        return resultados;
    }

    public int excluir (int cod){
        int qtde = 0;

        
        try {
            this.conectar();
            String comando = "DELETE FROM tbveiculos WHERE codigo = " + cod + ";";
            st.execute(comando);
            
            qtde = st.getUpdateCount();

        } catch (Exception e) {
            System.out.println("Erro ao deletar registro: " + e.getMessage());
        
        } finally{
            this.desconectar();
        }

        return qtde;
    }

    public int alterar(Veiculo v){
        int qtde = 0;
        try {
            this.conectar();
            String comando = "UPDATE tbveiculos SET "
            + "marca = '" + v.getMarca() + "', " 
            + "modelo = '" + v.getModelo() + "', " 
            + "chassi = '" + v.getChassi() + "', " 
            + "ano = '" + v.getAno() + "' "
            + "WHERE codigo = " + v.getCodigo() + ";";
            
            //System.out.println(comando);

            st.executeUpdate(comando);
            qtde = st.getUpdateCount();

        } catch (Exception e) {
            System.out.println("Erro ao inserir registro: " + e.getMessage());
        
        } finally{
            this.desconectar();
        }

        return qtde;
    } 


}
