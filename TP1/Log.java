

public class Log {
    private int reservasCanceladas;
    private int reservasAprobadas;
    private long tiempoInicioOriginal;
    private long tiempoInicio;

    public Log() {
        
        tiempoInicio = System.currentTimeMillis();
        tiempoInicioOriginal = tiempoInicio;
        reservasCanceladas = 0;
        reservasAprobadas = 0;
        
        System.out.println("Inicio del programa\n");
      
    }

    public synchronized void registrarCancelacion() {
        reservasCanceladas++;
        escribirLog();
    }

    public synchronized void registrarAprobacion() {
        reservasAprobadas++;
        escribirLog();
    }

    public void escribirLog() {
        long tiempoActual = System.currentTimeMillis();
        if (tiempoActual - tiempoInicio >= 200) {
           
            System.out.println("Reservas canceladas: " + reservasCanceladas + "\n");
            System.out.println("Reservas aprobadas: " + reservasAprobadas + "\n");
            System.out.println("\n");
            tiempoInicio = tiempoActual;
           
        }
    }
   
    //llamar esta funcion al final del main
    public void imprimirOcupacionFinal(int ocupacionFinal) {
       
        System.out.println("Ocupación final del vuelo: " + ocupacionFinal + " asientos ocupados\n");
        long tiempoTotal = System.currentTimeMillis() - tiempoInicioOriginal;
        System.out.println("Tiempo total del programa: " + tiempoTotal + " milisegundos\n");
       
    }
}
