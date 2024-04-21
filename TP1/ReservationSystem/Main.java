package ReservationSystem;

public class Main {
    public static void main(String[] args) {
        Plane plane = new Plane();
        ReservationSystem.Lists lists = new ReservationSystem.Lists();
        Reservation r = new Reservation(lists, plane);
        Payment p = new Payment(lists, plane);
        ReservationSystem.Cancellation c = new ReservationSystem.Cancellation(lists, plane);
        Verification v = new Verification(lists, plane);

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
        for (Thread hilor : hilosReserva) {
            try {
                hilor.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for (Thread hilop : hilosPago) {
            try {
                hilop.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for (Thread hiloc : hilosCancelacionValidacion) {
            try {
                hiloc.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for (Thread hilov : hilosVerificacion) {
            try {
                hilov.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

//-------------------PARTE FINAL-------------------

        //Imprimo asientos
        /*for(int i = 0; i < plane.seatPool.size(); i++) {
            System.out.println(Arrays.toString(plane.seatPool.get(i)));
        }
        int[] a = plane.seatPool.remove(5);
        for(int i = 0; i < plane.seatPool.size(); i++) {
            System.out.println(Arrays.toString(plane.seatPool.get(i)));
        }
        System.out.println(Arrays.toString(a));
*/

        //plane.printSeats();

        //Imprimo estado de los asientos

        //plane.printSeatsStatus();

        //Imprimo la cantidad de reservas confirmadas y canceladas

        //lists.escribirLogLista();


        // Imprimo la ocupación final del vuelo y el tiempo total del programa

        //lists.imprimirOcupacionFinal();

        // lista.printListas();
        lists.printVerifiedLength();
        lists.printCancelledLength();

    }
}
