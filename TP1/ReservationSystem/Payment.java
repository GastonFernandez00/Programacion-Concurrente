package ReservationSystem;

import java.util.Random;

public class Payment implements Runnable {
    ReservationSystem.Lists lists;
    Plane plane;
    Log log;

    public Payment(ReservationSystem.Lists lists, Plane plane) {
        this.lists = lists;
        this.plane = plane;
        log = lists.getLog();
    }

    public void run() {
        int i = 0;
        while ((!plane.seatPool.isEmpty() && i<500)||!lists.isEmptyPending()) {
            if(!lists.isEmptyPending()){
                i=0;
                Reserve reserve = lists.getRandomPendingReserve();
                if(reserve!=null){
                    if(verifyPayment()){
                        lists.removePendingReserve(reserve);
                        lists.addConfirmedReserve(reserve);
                        System.out.println(Thread.currentThread().getName() + " pagó el asiento " + reserve.getSeatID());
                    }else{
                        lists.removePendingReserve(reserve);
                        lists.addCancelledReserve(reserve);

                        plane.seatStatusChange(reserve.getSeatID(),Seat.DISCARDED);
                        System.out.println(Thread.currentThread().getName() + " no pagó el asiento " + reserve.getSeatID());
                        log.registerCancellation();
                    }
                }
            }else {
                i++;
            }
            try {
                Thread.sleep(40);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + " Payment Finished");
    }

    private boolean verifyPayment() {
        Random random = new Random();
        return random.nextInt(100) < 90;
    }
}