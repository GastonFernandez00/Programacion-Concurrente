package ReservationSystem;

import java.util.Random;

public class Cancellation implements Runnable{
    Lists lists;
    Plane plane;
    Log log;

    public Cancellation(Lists lists, Plane plane) {
        this.lists = lists;
        this.plane = plane;
    }

    public void run(){
        int i = 0;
        while (i<500) {
            if(!lists.isEmptyConfirmed()){
                i=0;
                Reserve reserve = lists.getRandomConfirmedReserve();
                if(reserve!=null && !reserve.getChecked()){
                    if(verifyCancellation()){
                        lists.removeConfirmedReserve(reserve);
                        lists.addCancelledReserve(reserve);
                        System.out.println(Thread.currentThread().getName() + " canceló el asiento " + reserve.getSeatID());
                    }else{
                        lists.setCheckedConfirmadas(reserve);
                        lists.addConfirmedReserve(reserve);
                        System.out.println(Thread.currentThread().getName() + " reconfirmó el asiento " + reserve.getSeatID());
                    }
                }
            }
            i++;
            try {
                Thread.sleep(40);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean verifyCancellation() {
        Random random = new Random();
        return random.nextInt(100) > 90;
    }

}
