package ReservationSystem;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class Plane{

    private final Integer rows = 31;
    private final Integer columns = 6;
    public Seat[][] seats;
    public List<int[]> seatPool;

    /*  Plane class constructor
    *  Creates a matrix of seats with the specified rows and columns
    */
    public Plane() {
        seats = new Seat[rows][columns];
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < columns; j++) {
                Seat a = new Seat(Integer.toString(i) + (char) (j + 65));
                seats[i][j] = a;
            }
        seatPool = createSeatPool();
    }

    /*
     * Creates a list of all the positions in the plane
     */
    public List<int[]> createSeatPool(){
        List<int[]> seatPool = new ArrayList<>();
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < columns; j++) {
                seatPool.add(new int[]{i,j});
            }
        return seatPool;
    }

    /* Changes the status of a given seat */
    public synchronized void seatStatusChange(String id,Integer newStatus){
        for(int i = 0; i < rows ; i++) for(int j = 0; j < columns; j++){
            if(seats[i][j].getSeat().equals(id)){
                seats[i][j].setStatus(newStatus);
                return;
            }
        }
    }

    /*Prints seats as a matrix*/
    public void printSeats(){
        System.out.println("\n\n");
        for(int i = 0; i < rows ; i++){
            for(int j = 0; j < columns; j++){
                if(i<10){
                    System.out.print(seats[i][j].getSeat()+"   ");
                }else{
                    System.out.print(seats[i][j].getSeat()+"  ");
                }
            }
            System.out.println("");
        }
    }

    /* Returns a random seat from the pool */
    public synchronized String getRandomSeat(){
        Random r = new Random();
        if(!seatPool.isEmpty()) {
            int x = r.nextInt(seatPool.size());
            int[] seat = seatPool.remove(x);
            return seats[seat[0]][seat[1]].getSeat();
        }
        return null;
    }

    /* Returns a seat by ID */
    public Seat getAsiento(String id){
        for(int i = 0; i < rows ; i++) for(int j = 0; j < columns; j++){
            if(id.equals(seats[i][j].getSeat())){
                return seats[i][j];
            }
        }
        return null;
    }

    /* Returns the amount of rows on the plane's matrix */
    public int getRow(){
        return rows;}

    /* Returns the amount of columns on the plane's matrix */
    public int getColumn(){
        return columns;
    }

    /* Prints the status of all the seats */
    public void printSeatsStatus(){
        System.out.println("\n\n");
        for(int i = 0; i < rows ; i++) for(int j = 0; j < columns; j++){

            System.out.println(seats[i][j].getSeat()+": "+statusToText(seats[i][j].getStatus()));
        }
    }

    /* Returns the status of a seat as a string */
    public String statusToText(Integer status){
        String x = "";
        switch (status){
            case 0:
                x = "Available";
                break;
            case 1:
                x = "Taken";
                break;
            case 2:
                x = "Discarded";
                break;
        }
        return x;
    
    }


}