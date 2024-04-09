public class Avion {
    private static final Integer LIBRE = 0;
    private static final Integer RESERVADO = 1;
    private static final Integer VERIFICADO = 2;
    private static final Integer CANCELADO = -1;


    // Tamaño de filas y columnas predefinido
    private Integer filas = 31;
    private Integer columnas = 6;

    // Conjunto de Asientos
    private Asiento asientos[][];
     
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

    public Avion(){
        asientos = new Asiento[filas][columnas];
        for(Integer i = 0; i < filas; i++)
        for(Integer j = 0; j < columnas; j++){
            Asiento a = new Asiento(i.toString()+(char)(j+65));
            asientos[i][j] = a;
        } 
    }


    public void printAsientos(){
        System.out.println("\n\n");
        for(Integer i = 0; i < filas ; i++){
            for(Integer j = 0; j < columnas; j++){
                
                System.out.print(asientos[i][j].getAsiento()+"  ");
            }
            System.out.println("");
        }
    }

    public void printEstadoAsientos(){
        System.out.println("\n\n");
        for(Integer i = 0; i < filas ; i++) for(Integer j = 0; j < columnas; j++){

            System.out.println(asientos[i][j].getAsiento()+": "+asientos[i][j].getEstado());
        }
    }

    public void cambiarEstado(String a,Integer e){
        for(Integer i = 0; i < filas ; i++) for(Integer j = 0; j < columnas; j++){
            if(asientos[i][j].getAsiento().equals(a)){
                asientos[i][j].cambiarEstado(e);
            }
        }
    }


    
    public static void main(String[] args) {
        Avion a = new Avion();
        System.out.println("\n\n\n\n\n");
        a.printAsientos();
        a.printEstadoAsientos();
        a.cambiarEstado("30F",VERIFICADO);
        a.cambiarEstado("30E",5);
        a.printEstadoAsientos();
  
    }
}