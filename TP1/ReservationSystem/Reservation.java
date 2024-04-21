package ReservationSystem;

public class Reservation implements Runnable {
    private ReservationSystem.Lists lists;
    private ReservationSystem.Plane plane;

    public Reservation(ReservationSystem.Lists lists, ReservationSystem.Plane plane) {
        this.lists = lists;
        this.plane = plane;
    }

    public void run() {
        int i = 0;
        while (!plane.seatPool.isEmpty()) {
            Reserve reserve = new Reserve(plane.getRandomSeat(), plane);
            plane.seatStatusChange(reserve.getSeatID(), Seat.TAKEN);
            lists.addPendingReserve(reserve);
            i++;
            System.out.println(Thread.currentThread().getName() + " reservó el asiento " + reserve.getSeatID());
            try {
                Thread.sleep(40);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + " Reservation Finished");
    }

}
