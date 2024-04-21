package ReservationSystem;


import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class Lists {
    private List<Reserve> canceladas, confirmadas, pendientes, verificadas;
    private final Object pendingKey, confirmedKey, cancelledKey, verifiedKey;

    public Lists(){
        canceladas = new ArrayList<>();
        confirmadas = new ArrayList<>();
        pendientes = new ArrayList<>();
        verificadas = new ArrayList<>();

        pendingKey = new Object();
        confirmedKey = new Object();
        cancelledKey = new Object();
        verifiedKey = new Object();
    }

    public void printListas(){
        System.out.println("canceladas: " + canceladas.size());
        System.out.println("confirmadas: " + confirmadas.size());
        System.out.println("Pendientes: " + pendientes.size());
        System.out.println("Verificadas: " + verificadas.size());
    }

    //---PENDING LIST METHODS---
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

    public void addPendingReserve(Reserve r){
        synchronized(pendingKey){
            pendientes.add(r);
        }
    }

    public void removePendingReserve(Reserve r){
        synchronized(pendingKey){
            pendientes.remove(r);
        }
    }

    public boolean isEmptyPending(){
        synchronized(pendingKey){
            return pendientes.isEmpty();
        }
    }

    //---CONFIRMED LIST METHODS---
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
    public Reserve getRandomCheckedReserve() {
        synchronized(confirmedKey){
            for (Iterator<Reserve> iterator = confirmadas.iterator(); iterator.hasNext();) {
                Reserve reserve = iterator.next();
                if (reserve.getChecked()) {
                    iterator.remove(); // Remover la reserva seleccionada de la lista
                    return reserve;
                }
            }
            // Si no se encuentra ninguna reserva confirmada y checkeada
            return null;
        }
    }

    public void addConfirmedReserve(Reserve r){
        synchronized(confirmedKey){
            confirmadas.add(r);
        }
    }

    public void removeConfirmedReserve(Reserve r){
        synchronized(confirmedKey){
            confirmadas.remove(r);
        }
    }

    public boolean isEmptyConfirmed(){
        synchronized(confirmedKey){
            return confirmadas.isEmpty();
        }
    }

    public boolean getCheckedConfirmadas(Reserve r){
        synchronized(confirmedKey){
            return r.getChecked();
        }
    }

    public void setCheckedConfirmadas( Reserve r){
        synchronized(confirmedKey){
            r.setChecked();
        }
    }

    public boolean uncheckedExist(){
        synchronized(confirmedKey){
            for(Reserve r: confirmadas){
                if(!r.getChecked()){
                    return true;
                }
            }
            return false;
        }
    }

    //---CANCELLED LIST METHODS---
    public Reserve getRandomCancelledReserve() {
        synchronized(cancelledKey){
            if (canceladas.isEmpty()) {
                return null;
            }
            Random random = new Random();
            int index = random.nextInt(canceladas.size());
            return canceladas.remove(index);
        }
    }

    public void addCancelledReserve(Reserve r){
        synchronized(cancelledKey){
            canceladas.add(r);
        }
    }

    public void removeCancelledReserve(Reserve r){
        synchronized(cancelledKey){
            canceladas.remove(r);
        }
    }

    public boolean isEmptyCancelled(){
        synchronized(cancelledKey){
            return canceladas.isEmpty();
        }
    }

    public void printCancelledLength(){
        synchronized(cancelledKey){
            System.out.println("Canceladas: " + canceladas.size());
        }
    }

    //---VERIFIED LIST METHODS---
    public void addVerifiedReserve(Reserve r){
        synchronized(verifiedKey){
            verificadas.add(r);
        }
    }

    public void printVerifiedLength(){
        synchronized(verifiedKey){
            System.out.println("Verificadas: " + verificadas.size());
        }
    }
}
