package ReservationSystem;

import java.util.Random;

public class Cancellation implements Runnable{
    Lists lists;
    Plane plane;
    Log log;

    /*
     * Cancellation constructor, receives a list and a plane
     */
    public Cancellation(Lists lists, Plane plane) {
        this.lists = lists;
        this.plane = plane;
        log = lists.getLog();
    }

    public void run(){
        int i = 0; //Counter
        while (i<10) {

            //Asks if there are confirmed reservations
            if(!lists.isEmptyConfirmed()){
                i=0;

                //Takes a random confirmed reservation
                Reserve reserve = lists.getRandomConfirmedReserve();

                // If the reservation is not null and not checked
                if(reserve!=null && !reserve.getChecked()){

                    if(verifyCancellation()){ // Asks if the reservation gets cancelled

                        lists.addCancelledReserve(reserve); // Adds the reservation to the cancelled list
                        System.out.println(Thread.currentThread().getName() + " canceló el asiento " + reserve.getSeatID());
                        log.registerCancellation(); // Registers the cancellation in the log
                    }
                    else{ // The reservation gets reconfirmed
                        lists.setCheckedConfirmadas(reserve); // Sets the reservation as checked
                        lists.addConfirmedReserve(reserve); // Adds the reservation to the confirmed list
                        System.out.println(Thread.currentThread().getName() + " reconfirmó el asiento " + reserve.getSeatID());
                    }
                }
            }
            i++;
            try { // Sleeps for 40ms
                Thread.sleep(40);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + " Cancellation Finished");
    }

    private boolean verifyCancellation() { // Asks if the reservation gets cancelled
        Random random = new Random();
        return random.nextInt(100) > 90;
    }

}
