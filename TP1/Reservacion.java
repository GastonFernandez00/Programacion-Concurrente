/* La clase Reservacion simula el proceso de reservar un asiento del avión chequeando si el asiento
 * se encuentra LIBRE 
 */

import java.util.Random;
import java.util.List;
import java.util.ArrayList;

public class Reservacion implements Runnable {
    private Reservas reserva;
    private Avion matriz;
    private List<Reservas> pendientes;

    public Reservacion(Reservas reserva){
        this.reserva = reserva;
        pendientes = new ArrayList<>();
    }

    public void run(){

        Asiento[][] asientos= matriz.getMatriz();
        Random random = new Random();

        while (true) {
            // Generar un asiento aleatorio
            int fila = random.nextInt(asientos.length);
            int columna = random.nextInt(asientos[0].length);
            Asiento asiento = asientos[fila][columna];

            // Verificar si el asiento está disponible
            if (asiento.getEstado() == "LIBRE") {
                    // Marcar el asiento como reservado
                    asiento.cambiarEstado(Asiento.RESERVADO);
                    // Registrar la reserva pendiente
                    reserva.setEstado(Reservas.PENDIENTE);
                    reserva.setPosAsieto(asiento.getAsiento());
                    setPendientes(reserva);
                    System.out.println(Thread.currentThread().getName() + " reservó el asiento " + asiento.getAsiento());
                    break; // Salir del bucle una vez reservado el asiento
            }
            
        }
    }

    public synchronized void setPendientes(Reservas reserva){
        pendientes.add(reserva);
    }

    public List<Reservas> getPendientes(){
        return pendientes;
    }
}
