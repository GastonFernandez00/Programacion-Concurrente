package ReservationSystem;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class Lists {
    private List<Reserve> canceladas, confirmadas, pendientes, verificadas;
    private final Object pendingKey, confirmedKey, cancelledKey, verifiedKey;
    private Log log;

    // Initialize the lists, the log, and the keys for synchronization
    public Lists(){
        canceladas = new ArrayList<>();
        confirmadas = new ArrayList<>();
        pendientes = new ArrayList<>();
        verificadas = new ArrayList<>();
        log = new Log();

        pendingKey = new Object();
        confirmedKey = new Object();
        cancelledKey = new Object();
        verifiedKey = new Object();
        
    }

//---LOG METHODS---

    public Log getLog(){
        return log;
    }

    // Prints the amount of cancelled and approved reservations every 200ms
      public void getwriteLog(){
        log.writeLog();
    }

    // Prints the amount of 'taken' seats
    public void getPrintFinalTakenSeats(){
        log.printFinalTakenSeats(verificadas.size());
    }

//---PENDING LIST METHODS---

    /*  Returns a random pending reservation.
        The random reservation is removed from the list before being returned.    
    
        If there are no pending reservations, returns null
    */
    public Reserve getRandomPendingReserve() {
        synchronized(pendingKey){
            if (pendientes.isEmpty()) {
                return null;
            }
            Random random = new Random();
            int index = random.nextInt(pendientes.size());
            return pendientes.remove(index);
        }
    }

    // Adds a reservation to the pending list
    public void addPendingReserve(Reserve r){
        synchronized(pendingKey){
            pendientes.add(r);
        }
    }

    // Removes a reservation from the pending list
    public void removePendingReserve(Reserve r){
        synchronized(pendingKey){
            pendientes.remove(r);
        }
    }

    // Returns true if the pending list is empty
    public boolean isEmptyPending(){
        synchronized(pendingKey){
            return pendientes.isEmpty();
        }
    }

//---CONFIRMED LIST METHODS---

    /* 
     * Returns a random confirmed reservation.
     * The random reservation is removed from the list before being returned.
     * 
     * If there are no confirmed reservations, returns null
     */
    public Reserve getRandomConfirmedReserve() {
        synchronized(confirmedKey){
            if (confirmadas.isEmpty()) {
                return null;
            }
            Random random = new Random();
            int index = random.nextInt(confirmadas.size());
            if(!confirmadas.get(index).getChecked()){
                return confirmadas.remove(index);
            }
            return null;
        }
    }
    
    /* 
     * Returns a random checked reservation.
     * The random reservation is removed from the list before being returned.
     * 
     * If there are no confirmed reservations, returns null
     */
    public Reserve getRandomCheckedReserve() {
        synchronized(confirmedKey){
            for (Iterator<Reserve> iterator = confirmadas.iterator(); iterator.hasNext();) {
                Reserve reserve = iterator.next();
                if (reserve.getChecked()) { // If the reservation is checked
                    iterator.remove(); // Remove the reservation from the list
                    return reserve;
                }
            }
            return null;
        }
    }

    // Adds a reservation to the confirmed list
    public void addConfirmedReserve(Reserve r){
        synchronized(confirmedKey){
            confirmadas.add(r);
        }
    }

    // Removes a reservation from the confirmed list
    public void removeConfirmedReserve(Reserve r){
        synchronized(confirmedKey){
            confirmadas.remove(r);
        }
    }

    // Returns true if the confirmed list is empty
    public boolean isEmptyConfirmed(){
        synchronized(confirmedKey){
            return confirmadas.isEmpty();
        }
    }

    // Returns true if a reservation is checked
    public boolean getCheckedConfirmadas(Reserve r){
        synchronized(confirmedKey){
            return r.getChecked();
        }
    }

    // Sets a reservation as checked
    public void setCheckedConfirmadas( Reserve r){
        synchronized(confirmedKey){
            r.setChecked();
        }
    }
    
//---CANCELLED LIST METHODS---

    // Adds a reservation to the cancelled list
    public void addCancelledReserve(Reserve r){
        synchronized(cancelledKey){
            canceladas.add(r);
        }
    }

//---VERIFIED LIST METHODS---

    // Adds a reservation to the verified list
    public void addVerifiedReserve(Reserve r){
        synchronized(verifiedKey){
            verificadas.add(r);
        }
    }
}
