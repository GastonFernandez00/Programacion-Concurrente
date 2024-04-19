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
        matriz = new Avion();
    }

    public void run(){
        while (true) {
            // Generar un asiento aleatorio
            Asiento a = matriz.getAsientoAleatorio();
            Integer estado = a.getEstadoNumerico();

            // Verificar si el asiento está disponible
            if (estado == Asiento.LIBRE) {
                    // Marcar el asiento como reservado
                    matriz.cambiarEstado(a.getAsiento(), Asiento.OCUPADO);
                    // Registrar la reserva pendiente
                    reserva.setEstado(Reservas.PENDIENTE);
                    reserva.setPosAsieto(a.getAsiento());
                    addPendientes(reserva);
                    System.out.println(Thread.currentThread().getName() + " reservó el asiento " + asiento.getAsiento());
                    break; // Salir del bucle una vez reservado el asiento
            }
        }
    }

    public synchronized Reservas obtenerReservaPendientesAleatoria() {
        if (pendientes.isEmpty()) {
              return null;
        }
        Random random = new Random();
        int indicependientes = random.nextInt(pendientes.size());
        return pendientes.get(indicependientes);
    }
    
    public synchronized void addPendientes(Reservas r){
        pendientes.add(r);
    }

    public synchronized void removePendientes(Reservas r){
        pendientes.remove(r);
    }

    public synchronized boolean isEmptyPendientes(){
        return pendientes.isEmpty();   
    }
}
