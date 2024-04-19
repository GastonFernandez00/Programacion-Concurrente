import java.util.Random;

public class Cancelacion implements Runnable {

    public Cancelacion(){}

    @Override
    public void run() {

        while (!Thread.currentThread().isInterrupted()) {
            cancellationAttempt(confirmadas, canceladas, avion);

            try {
                Thread.sleep(100); // Esperar un tiempo antes de realizar la próxima verificación
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private void cancellationAttempt(List<Reservas> confirmadas, List<Reservas> canceladas, Avion avion){
        if (confirmadas.isEmpty()) {
            return;                                                 //If empty does nothing
        }
        Random random = new Random();
        int index = random.nextInt(confirmadas.size());             //Picks a random index for the list
        if (!confirmadas.getChecked()){                             //If already checked does nothing
            return;
        }
        String id = confirmadas.get(index).getPosAsiento();         //Gets seat ID
        if(random.nextInt(100)>90){                          //Cancels reservation for 10%
            Reservas tempReservation = confirmadas.get(index);
            confirmadas.remove(index);
            canceladas.add(tempReservation);
        }
        confirmadas.setChecked();                                   //Sets Reservation as Checked
        avion.cambiarEstado(id, 2);                              //Discards Seat
    }
}
