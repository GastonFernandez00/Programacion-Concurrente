/* La clase Reservacion simula el proceso de reservar un asiento del avión chequeando si el asiento
 * se encuentra LIBRE 
 */

public class Reservacion implements Runnable {
    private Avion matriz;
    private Listas pendientes;

    public Reservacion(Listas pendientes){
        this.pendientes = pendientes;
        matriz = new Avion();
    }

    public void run(){
        while (!matriz.estaLleno()) {
            // Generar un asiento aleatorio
            Asiento a = matriz.getAsientoAleatorio();
            Integer estado = a.getEstadoNumerico();

            // Verificar si el asiento está disponible
            if (estado == Asiento.LIBRE){
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
            try{
                Thread.sleep(100);
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + " Terminó su ejecución");   
    }

    public void imprimir(){
        matriz.printEstadoAsientos();
    }

    public void imprimirMatriz(){
        matriz.printAsientos();
    }


    // public static void main(String[] args) {
    //     Listas pendientes = new Listas();
    //     Reservacion r = new Reservacion(pendientes);
        
    //     Thread t1 = new Thread(r);
    //     Thread t2 = new Thread(r);
    //     Thread t3 = new Thread(r);
        
    //     t1.start();
    //     t2.start();
    //     t3.start();

    //     try {
    //         t1.join();
    //     }catch (InterruptedException e) {
    //             e.printStackTrace();
    //         }

    //     try {
    //         t2.join();
    //     }catch (InterruptedException e) {
    //             e.printStackTrace();
    //         }
    //     try {
    //         t3.join();
    //     }catch (InterruptedException e) {
    //             e.printStackTrace();
    //         }
    //     r.imprimir();
        
       
    // }
    
}
