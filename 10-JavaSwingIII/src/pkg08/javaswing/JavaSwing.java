package pkg08.javaswing;

import java.util.ArrayList;
import model.DaoPaises;
import model.Veiculo;
import view.VeiculoView;

public class JavaSwing {

    
    public static void main(String[] args) {
        DaoPaises daoVeiculo = new DaoPaises();
        ArrayList<Veiculo> veiculos = daoVeiculo.buscarTodos();
        
        for(Veiculo v : veiculos){
            System.out.println(v.getModelo() + " - " + v.getMarca());
        } 
        
        //com.formdev.flatlaf.FlatLightLaf.setup();    // Tema Claro
        com.formdev.flatlaf.FlatDarkLaf.setup();     // Tema Escuro
        
        new VeiculoView().setVisible(true); //Chama a view 
    }
    
}
