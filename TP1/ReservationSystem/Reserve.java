package ReservationSystem;

public class Reserve {

    private final String seatID;
    private boolean checked;
    private ReservationSystem.Plane plane;

    /* Reserve Constructor, receives the Id of the seat that's reserving, and the Plane */
    public Reserve(String seatID, ReservationSystem.Plane plane){
        this.seatID = seatID;
        this.checked = false;
        this.plane = plane;
    }

    /* Sets the 'checked' status as true */
    public void setChecked(){
        this.checked = true;
    }

    /* Returns the 'checked' status */
    public boolean getChecked(){
        return checked;
    }

    /* Returns the seat ID */
    public String getSeatID(){
        return seatID;
    }

    /* Changes the status of the seat with the ID 'seatID' */
    public void setSeatStatus(String seatID, Integer status){
        plane.seatStatusChange(seatID, status);
    }
}
