package ReservationSystem;

import java.util.Random;

public class Payment implements Runnable {
    ReservationSystem.Lists lists;
    Plane plane;
    Log log;

    /* Payment constructor, receives a list and a plane */
    public Payment(ReservationSystem.Lists lists, Plane plane) {
        this.lists = lists;
        this.plane = plane;
        log = lists.getLog();
    }

    public void run() {
        int i = 0; // Used as a counter to avoid infinite loops
        
        /* While free seats exist, and there are pending reservations */
        while ((!plane.seatPool.isEmpty() && i<500)||!lists.isEmptyPending()) {
            // Asks again if there are pending reservations
            if(!lists.isEmptyPending()){
                i=0; //Restarts the counter
                
                //Takes a random pending reservation
                Reserve reserve = lists.getRandomPendingReserve();
                if(reserve!=null){ 
                    if(verifyPayment()){//Asks if the reserve gets paid or cancelled

                        lists.addConfirmedReserve(reserve); //Adds the reservation to the confirmed list
                        System.out.println(Thread.currentThread().getName() + " pagó el asiento " + reserve.getSeatID());
                    }
                    else{ //The reservation gets cancelled
                       
                        // Adds the reservation to the cancelled list
                        lists.addCancelledReserve(reserve);

                        // Changes the status of the seat to 'DISCARDED'
                        plane.seatStatusChange(reserve.getSeatID(),Seat.DISCARDED);
                        System.out.println(Thread.currentThread().getName() + " no pagó el asiento " + reserve.getSeatID());
                        log.registerCancellation(); // Registers the cancellation in the log
                    }
                }
            }else{
                i++;
            }
            try { // Sleeps for 40ms
                Thread.sleep(40);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + " Payment Finished");
    }

    private boolean verifyPayment() {//Asks if the reserve gets paid or cancelled
        Random random = new Random();
        return random.nextInt(100) < 90;
    }
}