package ReservationSystem;

public class Reserve {

    public static final Integer PENDING = 0;
    public static final Integer PAYED = 1;
    public static final Integer CANCELLED = 2;
    public static final Integer VERIFIED = -1;

    private final String seatID;
    private boolean checked;
    private Integer status;
    private ReservationSystem.Plane plane;

    public Reserve(String seatID, ReservationSystem.Plane plane){
        this.seatID = seatID;
        this.checked = false;
        this.plane = plane;
    }

    public void setChecked(){
        this.checked = true;
    }

    public boolean getChecked(){
        return checked;
    }

    public String getSeatID(){
        return seatID;
    }

    public void setStatus(Integer status){
        this.status = status;
    }

    public void setSeatStatus(String seatID, Integer status){
        plane.seatStatusChange(seatID, status);
    }
}
