package ReservationSystem;

public class Reservation implements Runnable {
    private ReservationSystem.Lists lists;
    private ReservationSystem.Plane plane;

    /*
     * Reservation class constructor
     * Receives an object 'List' to access its methods, and the plane
     */
    public Reservation(ReservationSystem.Lists lists, ReservationSystem.Plane plane) {
        this.lists = lists;
        this.plane = plane;
    }

    public void run() {
        
        // While there are seats available, reserve a seat
        while (!plane.seatPool.isEmpty()) {
            // Create a new reservation for a random seat
            Reserve reserve = new Reserve(plane.getRandomSeat(), plane);
            // Changes the status of said seat to 'TAKEN'
            plane.seatStatusChange(reserve.getSeatID(), Seat.TAKEN);
            // Adds the reservation to the pending list
            lists.addPendingReserve(reserve);
           
            // Prints the seat that was reserved
            System.out.println(Thread.currentThread().getName() + " reservó el asiento " + reserve.getSeatID());
            // Sleeps for 40ms
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // Prints that the reservation process (the thread) has finished
        System.out.println(Thread.currentThread().getName() + " Reservation Finished");
    }

}
