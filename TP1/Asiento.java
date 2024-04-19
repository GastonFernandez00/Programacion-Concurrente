import java.util.Scanner;

public class Asiento  {

// ------------------------------- VARIABLES ---------------------------------------------
    
    // Definiciones
    public static final Integer LIBRE = 0;
    public static final Integer OCUPADO = 1;
    public static final Integer DESCARTADO = -1;

    // Posicion del Asiento
    private String asiento = "-1";

    /*  Los estados del asiento
     *  0 == Libre
     *  1 == Ocupado
     *  -1 == Descartado  */
    private Integer estado = -999;
// ------------------------------- FUNCIONES ---------------------------------------------
    
    // Constructor que crea un asiento vacio
    private Asiento(){}
    
    /* Constructor donde se le asigna una posicion la momento de crearse.
     * El estado siempre es "LIBRE" */
    public Asiento(String posicion){asiento = posicion;estado = LIBRE;}

    /* Constructor donde se le asigna una posicion y el estado
     al momento de crearse.*/
    public Asiento(String posicion,Integer e){asiento = posicion;estado = e;}

    // Imprime los estados posibles de un asiento
    public void estadosPosibles(){
        System.out.println("LIBRE = 0\nOCUPADO = 1\nDESCARTADO = -1\n"); }

    // Convierte el valor numerico de un estado, a su significado en String
    private String estadosToString(Integer e){
        String state = "";
        switch (e) {
            case -1: state = "DESCARTADO"; break;
            case 0: state = "LIBRE"; break;
            case 1: state = "OCUPADO"; break;
            default: break;
        }
        return state;
    }

    public void cambiarEstado(Integer E){
        // Se pregunta si 'E' pertenece a alguno de los estados posibles
        if(E != LIBRE && E != OCUPADO && E != DESCARTADO){
            // En caso de que no:
            System.out.println("\nEstado Inexistente. ¿Reingresar? S|N");

            // Se crea un objeto Scanner para tomar valores
            Scanner myObj = new Scanner(System.in);
            char opcion = (myObj.next()).charAt(0);
            
            // Se consulta si se quiere reingresar el cambio de estado
            if(opcion == 'S' || opcion == 's'){
                System.out.println("Que valor estado se queria colocar?");
                
                // Imprime los estado posibles a elegir
                estadosPosibles();
                Integer nuevoEstado = -100;
                int pasadas = 0;
                do {

                    if(pasadas > 0){System.out.println("Estado Inexistente, Reingresar.\n");}
                    nuevoEstado =(Integer)myObj.nextInt();
                    pasadas++;

                } while (nuevoEstado != LIBRE && nuevoEstado != OCUPADO && 
                         nuevoEstado != DESCARTADO);
                
                myObj = null;
                this.estado = nuevoEstado;
            }
            else{ myObj = null; return; }
        }

        else if(this.estado == E){ 
            System.out.println("\nEl asiento "+asiento+" ya se encuentra '"+estadosToString(estado)+"'");
            return;
        }
        else{
            this.estado = E; 
        }
    }

    public String getEstado(){return estadosToString(estado);}
    public Integer getEstadoNumerico(){return estado;}
    public String getAsiento(){return asiento;}

    // public static void main(String[] args) {
    //     System.out.println("\n\n\n--------------\n\n\n");
    //     Asiento a = new Asiento("XX");
    //     System.out.println(a.getEstado()+"\n");
    //     a.cambiarEstado(LIBRE);
    //     a.cambiarEstado(DESCARTADO);
    //     System.out.println(a.getEstado()+"\n");
        
    // }
}
