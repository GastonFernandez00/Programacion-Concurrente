package ReservationSystem;

import java.util.ArrayList;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Log {
    
    private int reservasCanceladas;
    private int reservasAprobadas;
    private long tiempoInicioOriginal; // Time for the start of the program
    private long tiempoInicio; // Time from the last log write
    private List<Integer[]> times;
    /*
     * Log constructor 
     * Initializes the variables
     */
    public Log() {

        tiempoInicio = System.currentTimeMillis();
        tiempoInicioOriginal = tiempoInicio;
        times = new ArrayList<>();
        reservasCanceladas = 0;
        reservasAprobadas = 0;
        createLog();
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
            Integer[] valores = new Integer[]{(int)(tiempoActual - tiempoInicio), reservasCanceladas, reservasAprobadas};
            times.add(valores);
            System.out.println("\n");
            System.out.println("Reservas canceladas: " + reservasCanceladas + "\n");
            System.out.println("Reservas aprobadas: " + reservasAprobadas + "\n");
            System.out.println("\n");
            tiempoInicio = tiempoActual; // Updates the time of the last log write

        }
    }

    // Prints the final amount of taken seats and the total time of the program
    public void printFinalTakenSeats(int ocupacionFinal) {

        System.out.println("\nOcupación final del vuelo: " + ocupacionFinal + " asientos ocupados\n");
        long tiempoTotal = System.currentTimeMillis() - tiempoInicioOriginal;
        System.out.println("Tiempo total del programa: " + tiempoTotal + " milisegundos\n");

        try {
            FileWriter fileWriter = new FileWriter("log.txt", true);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.println("Ocupacion final del vuelo: " + ocupacionFinal + " asientos ocupados");
            printWriter.println("Reservas Canceladas: "+reservasCanceladas);
            printWriter.println("Reservas Aprobadas: "+reservasAprobadas);
            printWriter.println("Tiempos entre logs - Canceladas - Aprobadas");
            for (Integer[] l : times) { printWriter.println(l[0]+"      "+l[1]+"        "+l[2]);};
            printWriter.println("\nTiempo total de ejecucion: "+tiempoTotal+"[ms]");
            printWriter.println("------------------------------------");
            printWriter.close();

        }catch (IOException e) {
            System.err.println("Error al escribir en el archivo de log: " + e.getMessage());
        }

    }
    
private void createLog(){
        try {
            FileWriter fileWriter = new FileWriter("log.txt", true); // El segundo parámetro true indica que se añadirá al archivo existente si está presente
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.close();
            System.out.println("Archivo de log creado exitosamente: " + "log");
        } catch (IOException e) {
            System.err.println("Error al crear el archivo de log: " + e.getMessage());
        }
    }

}
