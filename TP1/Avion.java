import java.util.Random;
public class Avion {

// ------------------------------- VARIABLES ---------------------------------------------

    // Tamaño de filas y columnas predefinido
    private Integer filas = 31;
    private Integer columnas = 6;

    // Conjunto de Asientos
    private Asiento asientos[][];
     
// ------------------------------- FUNCIONES ---------------------------------------------

    // Constructor con filas y columnas definibles
    public Avion(Integer filas, Integer columnas){
        this.filas = filas;
        this.columnas = columnas;
        asientos = new Asiento[filas][columnas];
        for(Integer i = 0; i < filas; i++)
        for(Integer j = 0; j < columnas; j++){
            Asiento a = new Asiento(i.toString()+(char)(j+65));
            asientos[i][j] = a;
        } 
    }

    // Constructor con filas y columnas predefinidas
    public Avion(){
        asientos = new Asiento[filas][columnas];
        for(Integer i = 0; i < filas; i++)
        for(Integer j = 0; j < columnas; j++){
            Asiento a = new Asiento(i.toString()+(char)(j+65));
            asientos[i][j] = a;
        } 
    }

    // Imprime todos los asientos existentes
    public synchronized void printAsientos(){
        System.out.println("\n\n");
        for(Integer i = 0; i < filas ; i++){
            for(Integer j = 0; j < columnas; j++){
                
                System.out.print(asientos[i][j].getAsiento()+"  ");
            }
            System.out.println("");
        }
    }

    // Imprime en forma de lista, el estado de todos los asientos
    public synchronized void printEstadoAsientos(){
        System.out.println("\n\n");
        for(Integer i = 0; i < filas ; i++) for(Integer j = 0; j < columnas; j++){

            System.out.println(asientos[i][j].getAsiento()+": "+asientos[i][j].getEstado());
        }
    }

    // Cambia el estado particular de UN asiento. 
    public synchronized void cambiarEstado(String a,Integer e){
        for(Integer i = 0; i < filas ; i++) for(Integer j = 0; j < columnas; j++){
            if(asientos[i][j].getAsiento().equals(a)){
                asientos[i][j].cambiarEstado(e);
                return;
            }
        }
        System.out.println("El asiento seleccionado no existe.\n");
    }

    //Devuelve el estado de un asiento en particular, en caso de no existir devuelve 100
    public synchronized Integer getEstadoAsiento(String a){
        Integer buscado = 100;
        for(Integer i = 0; i < filas ; i++) for(Integer j = 0; j < columnas; j++){
            if(asientos[i][j].getAsiento().equals(a)){
                buscado = asientos[i][j].getEstadoNumerico();
                break;
            }
        }
        if(buscado == 100){
            System.out.println("No se encontro el asiento "+a);
        }
        return buscado;
    }

    public Integer getFilas(){  return filas;   }
    public Integer getColumnas(){   return columnas;  }

    
    public synchronized Asiento getAsientoAleatorio(){
        Random r = new Random();
        Integer a = r.nextInt(1+filas);
        Integer b = r.nextInt(1+columnas);
        return asientos[a][b];
     }

    public synchronized boolean estaLleno(){
        boolean lleno = true;
        for(Integer i = 0; i < filas ; i++) for(Integer j = 0; j < columnas; j++){
            if(asientos[i][j].getEstadoNumerico() == Asiento.LIBRE){
                lleno = false;
                break;
            }
        }
        return lleno;
    }    
    // public static void main(String[] args) {
    //     Avion a = new Avion();
    //     System.out.println("\n\n\n\n\n");
    //     a.printAsientos();
    //     a.printEstadoAsientos();
    //     a.cambiarEstado("30F",Asiento.OCUPADO);
    //     a.cambiarEstado("30E",5);
    //     a.printEstadoAsientos();
  
    // }
}

