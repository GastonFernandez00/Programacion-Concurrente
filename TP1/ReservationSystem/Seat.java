package ReservationSystem;

public class Seat {
    public static final Integer AVAILABLE = 0;
    public static final Integer TAKEN = 1;
    public static final Integer DISCARDED = 2;

    private String id = "";
    private Integer status = 0;

    /*Seat constructor*/
    public Seat(String id){
        this.id = id;
        status = 0;
    }                                //starts as available

    public void setStatus(Integer status){
        this.status = status;
    }

    public String getSeat(){
        return id;
    }

    public Integer getStatus(){
        return status;
    }
}
