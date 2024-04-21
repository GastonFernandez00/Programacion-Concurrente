package ReservationSystem;

public class Seat {

    /*
     * Possible statuses for a seat
     */
    public static final Integer AVAILABLE = 0;
    public static final Integer TAKEN = 1;
    public static final Integer DISCARDED = 2;

    private String id = "";
    private Integer status = 0;

    /*Seat constructor, receives an Id*/
    public Seat(String id){
        this.id = id;
        status = 0;  //starts as available
    }                               

    /*Changes status of this seat*/
    public void setStatus(Integer status){
        this.status = status;
    }

    /*Returns seat ID*/
    public String getSeat(){
        return id;
    }

    /*Returns seat status*/
    public Integer getStatus(){
        return status;
    }
}
