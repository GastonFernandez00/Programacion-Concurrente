package ReservationSystem;

public class Main {
    public static void main(String[] args) {

        //------------------- CREATE OBJECTS -------------------
        Plane plane = new Plane();
        ReservationSystem.Lists lists = new ReservationSystem.Lists();
        Reservation r = new Reservation(lists, plane);
        Payment p = new Payment(lists, plane);
        ReservationSystem.Cancellation c = new ReservationSystem.Cancellation(lists, plane);
        Verification v = new Verification(lists, plane);

        //------------------- CREATE THREADS -------------------
        Thread[] hilosReserva = new Thread[3]; // 3 Threads for the reservation process
        Thread[] hilosPago = new Thread[2]; // 2 Threads for the payment process
        Thread[] hilosCancelacionValidacion = new Thread[3]; // 3 Threads for the cancellation and validation process
        Thread[] hilosVerificacion = new Thread[2]; // 2 Threads for the verification process

        //------------------- STARTING THE THREADS -------------------
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

        //------------------- WAIT FOR EACH THREAD TO FINISH -------------------
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

        //------------------- ENDING -------------------

        // Print the seats as a matrix

        plane.printSeats();

        // Print the status of the seats

        plane.printSeatsStatus();

        // Prints the amount of cancelled and confirmed reservations every 200ms

        lists.getwriteLog();

        // Prints the final amount of taken seats and the total time of the program

        lists.getPrintFinalTakenSeats();
    }
}
