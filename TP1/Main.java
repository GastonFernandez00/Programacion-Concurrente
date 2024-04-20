public class Main {
    public static void main(String[] args) {

//-------------------CREO OBJETOS-------------------
        Avion avion = new Avion();
        Log log = new Log();
        Reservacion procesoReservacion = new Reservacion();
        Pago procesoPago = new Pago();
        Verificacion procesoVerificacion = new Verificacion();


//-------------------CREO HILOS-------------------
        Thread[] hilosReserva = new Thread[3]; // 3 hilos para el proceso de reserva
        Thread[] hilosPago = new Thread[2]; // 2 hilos para el proceso de pago
        Thread[] hilosCancelacionValidacion = new Thread[3]; // 3 hilos para el proceso de cancelación/validación
        Thread[] hilosVerificacion = new Thread[2]; // 2 hilos para el proceso de verificación
       

//-------------------INICIO HILOS-------------------
        for (int i = 0; i < 3; i++) {
            hilosReserva[i] = new Thread(procesoReservacion);
            hilosReserva[i].start();
        }

        for (int i = 0; i < 2; i++) {
            hilosPago[i] = new Thread(procesoPago);
            hilosPago[i].start();
        }

        for (int i = 0; i < 3; i++) {
            hilosCancelacionValidacion[i] = new Thread();
            hilosCancelacionValidacion[i].start();
        }

        for (int i = 0; i < 2; i++) {
            hilosVerificacion[i] = new Thread();
            hilosVerificacion[i].start();
        }

//-------------------ESPERO A QUE TODOS TERMINEN PARA TERMINAR EL PROGRAMA-------------------
        for (Thread hilo : hilosReserva) {
            try {
                hilo.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for (Thread hilo : hilosPago) {
            try {
                hilo.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for (Thread hilo : hilosCancelacionValidacion) {
            try {
                hilo.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for (Thread hilo : hilosVerificacion) {
            try {
                hilo.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

//-------------------PARTE FINAL-------------------

        //Imprimo asientos

            avion.printAsientos();

        //Imprimo estado de los asientos

            avion.printEstadoAsientos();

        //Imprimo la cantidad de reservas confirmadas y canceladas

            log.escribirLog();

        //Obtengo la cantidad de Confirmadas y verificadas para la ocupacion final 
         int confirmadas = procesoPago.cantConfirmadas();
         int verificadas = procesoVerificacion.cantVerificadas();

        // Imprimo la ocupación final del vuelo y el tiempo total del programa

        log.imprimirOcupacionFinal(confirmadas + verificadas);
    }
}
