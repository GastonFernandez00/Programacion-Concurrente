import java.util.Random;
//import java.util.AbstractMap;
public class Avion {

// ------------------------------- VARIABLES ---------------------------------------------

    // Tamaño de filas y columnas predefinido
    private Integer filas = 31;
    private Integer columnas = 6;

    // Conjunto de Asientos
    private Asiento asientos[][];
    //public Asiento asientosAUX[][];
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

            //Asiento aAUX = new Asiento(i.toString()+(char)(j+65));
           // asientosAUX[i][j] = aAUX;

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
    public String getAsiento(int i, int j){return asientos[i][j].getAsiento(); }

    
    public synchronized Asiento getAsientoAleatorio(){
        Random r = new Random();
        Integer a = r.nextInt(filas);
        Integer b = r.nextInt(columnas);
        //Asiento aAUX = asientosAUX[a][b] ;
        return asientos[a][b];

     }

     public synchronized Asiento getAsiento(String a){
        Asiento x = null;
        for(Integer i = 0; i < filas ; i++) for(Integer j = 0; j < columnas; j++){
            if(a.equals(asientos[i][j].getAsiento())){
                return x = asientos[i][j];
            }
        }
        return x;
        
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
    
    /* private void aleatorio(){
    AbstractMap<filas, columnas> coords = new AbstractMap<filas, columnas>();
    for(int i = 0; i < filas; i++){
            for(int j = 0; j < columnas; j++){
                coords.put​(j, i);
            }
        }
    }   */ 
}

