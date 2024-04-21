package ReservationSystem;

public class Verification implements Runnable{
    ReservationSystem.Lists lists;
    ReservationSystem.Plane plane;
    Log log;

    /*
     * Verification constructor, receives a list and a plane
     */
    public Verification(ReservationSystem.Lists lists, ReservationSystem.Plane plane) {
        this.lists = lists;
        this.plane = plane;
        log = lists.getLog();
    }

    public void run(){
        int i = 0; //Counter
        //If i >= 20, the thread will stop. 
        while (i<20) {
            // If there are confirmed reservations
            if(!lists.isEmptyConfirmed()){ 
                
                // Takes a random checked reservation 
                ReservationSystem.Reserve reserve = lists.getRandomCheckedReserve(); 
                if(reserve!=null){
                    i=0; // Restarts the counter

                    // If the reservation is checked
                    if(lists.getCheckedConfirmadas(reserve)){
                        // Adds the reservation to the verified list
                        lists.addVerifiedReserve(reserve);
                        System.out.println(Thread.currentThread().getName() + " verificó el asiento " + reserve.getSeatID()); 
                        log.registerApproved(); // Registers the verification in the log
                    }
                }
            }
            i++;
            try { // Sleeps for 40ms
                Thread.sleep(150);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + " Verification Finished");
    }
}
