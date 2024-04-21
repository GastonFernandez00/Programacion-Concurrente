package ReservationSystem;

public class Log {
    
    private int reservasCanceladas;
    private int reservasAprobadas;
    private long tiempoInicioOriginal; // Time for the start of the program
    private long tiempoInicio; // Time from the last log write

    /*
     * Log constructor 
     * Initializes the variables
     */
    public Log() {

        tiempoInicio = System.currentTimeMillis();
        tiempoInicioOriginal = tiempoInicio;
        reservasCanceladas = 0;
        reservasAprobadas = 0;

        System.out.println("Inicio del programa\n");

    }

    // Registers a cancellation, and if possible, writes the log 
    public synchronized void registerCancellation() {
        reservasCanceladas++;
        writeLog();
    }

    // Registers an approval, and if possible, writes the log
    public synchronized void registerApproved() {
        reservasAprobadas++;
        writeLog();
    }

    // Writes the log if the time since the last log write is greater than 200ms
    public void writeLog() {
        long tiempoActual = System.currentTimeMillis();
        if (tiempoActual - tiempoInicio >= 200) {

            System.out.println("\n");
            System.out.println("Reservas canceladas: " + reservasCanceladas + "\n");
            System.out.println("Reservas aprobadas: " + reservasAprobadas + "\n");
            System.out.println("\n");
            tiempoInicio = tiempoActual; // Updates the time of the last log write

        }
    }

    // Prints the final amount of taken seats and the total time of the program
    public void printFinalTakenSeats(int ocupacionFinal) {

        System.out.println("Ocupación final del vuelo: " + ocupacionFinal + " asientos ocupados\n");
        long tiempoTotal = System.currentTimeMillis() - tiempoInicioOriginal;
        System.out.println("Tiempo total del programa: " + tiempoTotal + " milisegundos\n");

    }
}
