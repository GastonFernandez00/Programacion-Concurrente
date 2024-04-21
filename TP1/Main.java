public class Main {
    public static void main(String[] args) {

//-------------------CREO OBJETOS-------------------
       
        Listas lista = new Listas();
        Reservacion r = new Reservacion(lista);
        Pago p = new Pago(lista);
        Cancelacion c = new Cancelacion(lista);
        Verificacion v = new Verificacion(lista);
       

//-------------------CREO HILOS-------------------
        Thread[] hilosReserva = new Thread[3]; // 3 hilos para el proceso de reserva
        Thread[] hilosPago = new Thread[2]; // 2 hilos para el proceso de pago
        Thread[] hilosCancelacionValidacion = new Thread[3]; // 3 hilos para el proceso de cancelación/validación
        Thread[] hilosVerificacion = new Thread[2]; // 2 hilos para el proceso de verificación
       

//-------------------INICIO HILOS-------------------
        for (int i = 0; i < 3; i++) {
            hilosReserva[i] = new Thread(r);
            hilosReserva[i].start();
        }

        for (int i = 0; i < 2; i++) {
            hilosPago[i] = new Thread(p);
            hilosPago[i].start();
        }

        for (int i = 0; i < 3; i++) {
            hilosCancelacionValidacion[i] = new Thread(c);
            hilosCancelacionValidacion[i].start();
        }

        for (int i = 0; i < 2; i++) {
            hilosVerificacion[i] = new Thread(v);
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

            r.imprimirMatriz();

        //Imprimo estado de los asientos

            r.imprimir();

        //Imprimo la cantidad de reservas confirmadas y canceladas

            lista.escribirLogLista();


        // Imprimo la ocupación final del vuelo y el tiempo total del programa

            lista.imprimirOcupacionFinal();

            lista.printListas();
    }
}
