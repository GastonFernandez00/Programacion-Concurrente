package ReservationSystem;

public class Verification implements Runnable{
    ReservationSystem.Lists lists;
    ReservationSystem.Plane plane;
    ReservationSystem.Log log;

    public Verification(ReservationSystem.Lists lists, ReservationSystem.Plane plane) {
        this.lists = lists;
        this.plane = plane;
    }

    public void run(){
        int i = 0;
        while (i<20) {
            if(!lists.isEmptyConfirmed()){
                ReservationSystem.Reserve reserve = lists.getRandomCheckedReserve();
                if(reserve!=null){
                    i=0;
                    if(lists.getCheckedConfirmadas(reserve)){
                        lists.removeConfirmedReserve(reserve);
                        lists.addVerifiedReserve(reserve);
                        System.out.println(Thread.currentThread().getName() + " verificó el asiento " + reserve.getSeatID());
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
}
