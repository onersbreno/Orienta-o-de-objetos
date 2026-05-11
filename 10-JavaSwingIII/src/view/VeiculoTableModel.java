package view;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import model.Veiculo;

public class VeiculoTableModel extends AbstractTableModel{
    public static final int COL_CODIGO = 0;
    public static final int COL_MARCA = 1;
    public static final int COL_MODELO = 2;
    public static final int COL_CHASSI = 3;
    public static final int COL_ANO = 4;

    public ArrayList<Veiculo> listaVeiculos;
    
    public VeiculoTableModel(ArrayList<Veiculo> veiculos){
        this.listaVeiculos = veiculos;
    
    }
    
    @Override
    public int getColumnCount(){
        return 5;
    }
    
    @Override
    public int getRowCount(){
        return listaVeiculos.size();
    
    }
    
    @Override
    public String getColumnName(int coluna){
        String nome = "";
        if(coluna == COL_CODIGO){nome = "Codigo";}
        if(coluna == COL_MARCA){nome = "Marca";}
        if(coluna == COL_MODELO){nome = "Modelo";}
        if(coluna == COL_CHASSI){nome = "Chassi";}
        if(coluna == COL_ANO){nome = "Ano";}
        return nome;
    }
    
    @Override
    public Object getValueAt(int linha, int coluna){
        Veiculo v = listaVeiculos.get(linha);
        Object conteudo = "";
        
        if(coluna == COL_CODIGO){conteudo = v.getCodigo();}
        if(coluna == COL_MARCA){conteudo = v.getMarca();}
        if(coluna == COL_MODELO){conteudo = v.getModelo();}
        if(coluna == COL_CHASSI){conteudo = v.getChassi();}
        if(coluna == COL_ANO){conteudo = v.getAno();}
   
        return conteudo;
    
    
    }
    
    
}
