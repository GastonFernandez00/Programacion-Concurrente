import java.util.Scanner;

public class Asiento  {
    private static final Integer LIBRE = 0;
    private static final Integer RESERVADO = 1;
    private static final Integer VERIFICADO = 2;
    private static final Integer CANCELADO = -1;

// ------------------------------- VARIABLES ---------------------------------------------
    // Posición del Asiento
    private String asiento = "-1";

    /*  Los estados del asiento
     *  0 == Libre
     *  1 == Reservado
     *  2 == Verificado
     *  -1 == Cancelado  */
    private Integer estado = -999;
// ------------------------------- FUNCIONES ---------------------------------------------
    private Asiento(){}
    
    /*  Al crearse el asiento, se le debe asignar la posición del 
     * la matriz, para que tome ese valor
     */
    public Asiento(String posicion){asiento = posicion;estado = LIBRE;}

    public Asiento(String posicion,Integer e){asiento = posicion;estado = e;}

    // Imprime los estados posibles de un asiento
    private void estadosPosibles(){
        System.out.println("LIBRE = 0\nRESERVADO = 1\nVERIFICADO = 2\nCANCELADO = -1\n"); }

    // Convierte el valor numerico de un estado, a su significado en String
    protected String estadosToString(Integer e){
        String state = "";
        switch (e) {
            case -1: state = "CANCELADO"; break;
            case 0: state = "LIBRE"; break;
            case 1: state = "RESERVADO"; break;
            case 2: state = "VERIFICADO"; break;
            default: break;
        }
        return state;
    }

    public void cambiarEstado(Integer E){
        // Se pregunta si 'E' pertenece a alguno de los estados posibles
        if(E != LIBRE && E != RESERVADO && E != VERIFICADO && E != CANCELADO){
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

                } while (nuevoEstado != LIBRE && nuevoEstado != RESERVADO && 
                         nuevoEstado != VERIFICADO && nuevoEstado != CANCELADO);
                
                myObj = null;
                // cambiarEstado(nuevoEstado);
                this.estado = nuevoEstado;
            }
            else{ myObj = null; return; }
        }

        if(this.estado == E){ 
            System.out.println("\nEl asiento "+asiento+" ya se encuentra '"+estadosToString(estado)+"'");
            return;
        }
        else{
            this.estado = E; 
        }
    }

    public String getEstado(){return estadosToString(estado);}
    public String getAsiento(){return asiento;}

    // public static void main(String[] args) {
    //     System.out.println("\n\n\n--------------\n\n\n");
    //     Asiento a = new Asiento("XX");
    //     System.out.println(a.getEstado()+"\n");
    //     a.cambiarEstado(LIBRE);
    //     a.cambiarEstado(CANCELADO);
    //     System.out.println(a.getEstado()+"\n");
        
        
    

    // }




}
