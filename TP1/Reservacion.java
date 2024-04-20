/* La clase Reservacion simula el proceso de reservar un asiento del avión chequeando si el asiento
 * se encuentra LIBRE 
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Reservacion implements Runnable {
    private Avion matriz;
    private Listas pendientes;
    private List<String> asientos = new ArrayList<String>();

    public Reservacion(Listas pendientes){
        this.pendientes = pendientes;
        matriz = new Avion();
      
        for(int i = 0; i < matriz.getFilas(); i++){
            for(int j = 0; j < matriz.getColumnas(); j++){
                asientos.add(matriz.getAsiento(i, j));
            }
        }
    }
    
    public void run(){
        while (!matriz.estaLleno() /*&& asientos.size() > 0*/) {
            // Generar un asiento aleatorio

            // Random r = new Random();
            // int tamanio = asientos.size();
            // int x = r.nextInt(0,asientos.size());
            // Asiento a = matriz.getAsiento(asientos.get(x));
            // asientos.remove(x);

            Asiento a = matriz.getAsientoAleatorio();
            Integer estado = a.getEstadoNumerico();

            // Verificar si el asiento está disponible
            if (estado == Asiento.LIBRE) {
                Reservas reserva = new Reservas(a);
                // Marcar el asiento como reservado
                matriz.cambiarEstado(a.getAsiento(), Asiento.OCUPADO);
                // Registrar la reserva pendiente
                reserva.setEstado(Reservas.PENDIENTE);
                //reserva.setPosAsiento(a.getAsiento());
                pendientes.addPendientes(reserva);
                System.out.println(Thread.currentThread().getName() + " reservó el asiento " + a.getAsiento());
                //break; // Salir del bucle una vez reservado el asiento
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void imprimir(){
        matriz.printEstadoAsientos();
    }

    public void imprimirMatriz(){
        matriz.printAsientos();
    }

/*
    public static void main(String[] args) {
        Listas pendientes = new Listas();
        Reservacion r = new Reservacion(pendientes);
        
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
        
       
    }
    */
}
