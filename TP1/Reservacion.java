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

    public Reservacion(){
        pendientes = new ArrayList<>();
        matriz = new Avion();
    }

    public void run(){
        while (!matriz.estaLleno()) {
            // Generar un asiento aleatorio
            Asiento a = matriz.getAsientoAleatorio();
            Integer estado = a.getEstadoNumerico();

            // Verificar si el asiento está disponible
            if (estado == Asiento.LIBRE) {
                Reservas reserva = new Reservas();
                // Marcar el asiento como reservado
                matriz.cambiarEstado(a.getAsiento(), Asiento.OCUPADO);
                // Registrar la reserva pendiente
                reserva.setEstado(Reservas.PENDIENTE);
                reserva.setPosAsieto(a.getAsiento());
                addPendientes(reserva);
                System.out.println(Thread.currentThread().getName() + " reservó el asiento " + a.getAsiento());
                //break; // Salir del bucle una vez reservado el asiento
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

    public void imprimir(){
        matriz.printEstadoAsientos();
    }

    /*public static void main(String[] args) {
        Reservacion r = new Reservacion();
        
        Thread t1 = new Thread(r);
        Thread t2 = new Thread(r);
        Thread t3 = new Thread(r);
        
        t1.start();
        t2.start();
        t3.start();

        try {
            t1.join();
        }catch (InterruptedException e) {
                e.printStackTrace();
            }

        try {
            t2.join();
        }catch (InterruptedException e) {
                e.printStackTrace();
            }
        try {
            t3.join();
        }catch (InterruptedException e) {
                e.printStackTrace();
            }
        r.imprimir();
        
       
    }*/
}
