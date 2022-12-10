
package e2p1_diegoandino;

import java.util.ArrayList;

public class MaquinaEstados {
    
    private ArrayList<String> estado = new ArrayList();
    private ArrayList<String> estados_aceptacion = new ArrayList();
    private ArrayList<String> aristas = new ArrayList();
    private String estado_actual;
    
    public MaquinaEstados(String state, String arista){
        estado = splitStr(state, ';');
        aristas = splitStr(arista, ';');
        extractAcceptNodes();
        
        estado_actual = estado.get(0);
        
        System.out.println(imprimir(estado));
        System.out.println(imprimir(estados_aceptacion));
        System.out.println(imprimir(aristas));
        System.out.println(estado_actual);
    }
//------------------------------------------------------------------------------
//------------------------------------------------------------------------------
    public ArrayList<String> getEstado() {
        return estado;
    }

    public void setEstado(ArrayList<String> estado) {
        this.estado = estado;
    }

    public ArrayList<String> getEstados_aceptacion() {
        return estados_aceptacion;
    }

    public void setEstados_aceptacion(ArrayList<String> estados_aceptacion) {
        this.estados_aceptacion = estados_aceptacion;
    }

    public ArrayList<String> getAristas() {
        return aristas;
    }

    public void setAristas(ArrayList<String> aristas) {
        this.aristas = aristas;
    }

    public String getEstado_actual() {
        return estado_actual;
    }

    public void setEstado_actual(String estado_actual) {
        this.estado_actual = estado_actual;
    }
//------------------------------------------------------------------------------
//------------------------------------------------------------------------------
    public String imprimir(ArrayList<String> lista){
        String tempo = "";
        
        for(int i = 0; i < lista.size(); i++){
            tempo += lista.get(i);
            
            if(i != lista.size()-1){
                tempo += ", ";
            }
            
            else{
                tempo += "\n";
            }   
        }
        return tempo;
    }
    public ArrayList<String>splitStr(String str, char delim){
        ArrayList<String> Split = new ArrayList();
        
        String [] Arreglado = str.split(Character.toString(delim));
        
        for (int i = 0; i < Arreglado.length; i++) {
            Split.add(Arreglado[i]);
        }
  
        
        return Split;
    }
    public void extractAcceptNodes(){
        for(int i = 0; i < estado.size(); i++){
            String forma = estado.get(i);
            
            if(forma.charAt(0) == '.'){
                forma = forma.substring(1);
                estados_aceptacion.add(forma);
                
                estado.set(i, forma);
             
            }
        }
    }
    public String computeStr(String str){
         String answer = "";
        int prob = 0;
        for(int i = 0; i < str.length(); i++){

            for(int j = 0; j < estado.size(); j++){
                String temp = String.format("%s,%s,%s", estado_actual, str.charAt(i), estado.get(j));
                if(aristas.contains(temp)){

                    String estadoPrevio = estado_actual;
                    String[] arista = temp.split(",");
                    estado_actual = arista[2];
                    answer += String.format("%s:%s -> %s\n", estadoPrevio, str.charAt(i), estado_actual);
                    break;
                }
                
                if(j == estado.size()-1){
                    prob = 1;
                    break;
                }
            }
            
            if(prob == 1){
                break;
            }
            
        }
        
        if(prob == 1 || !estados_aceptacion.contains(estado_actual)){
            answer += "Rechazada...\n";
                
        }else{
            answer += "Aceptada...\n";
        }
        
        estado_actual = estado.get(0);
        return answer;
      
    }
}
